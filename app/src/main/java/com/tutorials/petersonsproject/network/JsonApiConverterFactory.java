package com.tutorials.petersonsproject.network;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Type;

import moe.banana.jsonapi2.Document;
import moe.banana.jsonapi2.Resource;
import moe.banana.jsonapi2.ResourceIdentifier;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;

import retrofit2.Converter;
import retrofit2.Retrofit;



@SuppressWarnings("unchecked")
public final class JsonApiConverterFactory extends Converter.Factory {

    public static JsonApiConverterFactory create() {
        return create(new Moshi.Builder().build());
    }

    public static JsonApiConverterFactory create(final Moshi moshi) {
        return new JsonApiConverterFactory(moshi, false);
    }

    private final Moshi moshi;

    private final boolean lenient;

    private JsonApiConverterFactory(final Moshi moshi, final boolean lenient) {
        if (moshi == null) {
            throw new NullPointerException("moshi == null");
        }
        this.moshi = moshi;
        this.lenient = lenient;
    }

    public JsonApiConverterFactory asLenient() {
        return new JsonApiConverterFactory(moshi, true);
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(final Type type, final Annotation[] annotations, final Retrofit retrofit) {
        Class<?> rawType = Types.getRawType(type);
        JsonAdapter<?> adapter;
        if (rawType.isArray() && ResourceIdentifier.class.isAssignableFrom(rawType.getComponentType())) {
            adapter = moshi.adapter(Types.newParameterizedType(Document.class, rawType.getComponentType()));
        } else if (ResourceIdentifier.class.isAssignableFrom(rawType)) {
            adapter = moshi.adapter(Types.newParameterizedType(Document.class, rawType));
        } else if (Document.class.isAssignableFrom(rawType)) {
            adapter = moshi.adapter(Types.newParameterizedType(Document.class, Resource.class));
        } else {
            return null;
        }
        if (lenient) {
            adapter = adapter.lenient();
        }
        return new MoshiResponseBodyConverter<>((JsonAdapter<Document<?>>) adapter, rawType);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(final Type type, final Annotation[] parameterAnnotations,
                                                          final Annotation[] methodAnnotations,
                                                          Retrofit retrofit) {
        Class<?> rawType = Types.getRawType(type);
        JsonAdapter<?> adapter;
        if (rawType.isArray() && ResourceIdentifier.class.isAssignableFrom(rawType.getComponentType())) {
            adapter = moshi.adapter(Types.newParameterizedType(Document.class, rawType.getComponentType()));
        } else if (ResourceIdentifier.class.isAssignableFrom(rawType)) {
            adapter = moshi.adapter(Types.newParameterizedType(Document.class, rawType));
        } else if (Document.class.isAssignableFrom(rawType)) {
            adapter = moshi.adapter(Types.newParameterizedType(Document.class, Resource.class));
        } else {
            return null;
        }
        if (lenient) {
            adapter = adapter.lenient();
        }
        return new MoshiRequestBodyConverter<>((JsonAdapter<Document<?>>) adapter, rawType);
    }

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

    private static class MoshiResponseBodyConverter<R> implements Converter<ResponseBody, R> {

        private final JsonAdapter<Document<?>> adapter;

        private final Class<R> rawType;

        MoshiResponseBodyConverter(final JsonAdapter<Document<?>> adapter, final Class<R> rawType) {
            this.adapter = adapter;
            this.rawType = rawType;
        }

        @Override
        public R convert(final ResponseBody value) throws IOException {
            try {
                Document<?> document = adapter.fromJson(value.source());
                if (Document.class.isAssignableFrom(rawType)) {
                    return (R) document;
                } else if (rawType.isArray()) {
                    Object a = Array.newInstance(rawType.getComponentType(), document.size());
                    for (int i = 0; i != Array.getLength(a); i++) {
                        Array.set(a, i, document.get(i));
                    }
                    return (R) a;
                } else {
                    return (R) document.get();
                }
            } finally {
                value.close();
            }
        }
    }

    private static class MoshiRequestBodyConverter<T> implements Converter<T, RequestBody> {

        private final JsonAdapter<Document<?>> adapter;

        private final Class<T> rawType;

        MoshiRequestBodyConverter(final JsonAdapter<Document<?>> adapter, final Class<T> rawType) {
            this.adapter = adapter;
            this.rawType = rawType;
        }

        @Override
        public RequestBody convert(final T value) throws IOException {
            Document document;
            if (Document.class.isAssignableFrom(rawType)) {
                document = (Document) value;
            } else if (rawType.isArray()) {
                document = Array.getLength(value) > 0
                        ? ((ResourceIdentifier) Array.get(value, 0)).getContext()
                        : new Document();
                if (document == null) {
                    document = new Document();
                    for (int i = 0; i != Array.getLength(value); i++) {
                        document.add((ResourceIdentifier) Array.get(value, i));
                    }
                }
                document.asList();
            } else {
                ResourceIdentifier data = ((ResourceIdentifier) value);
                document = data.getContext();
                if (document == null) {
                    document = new Document();
                    document.set(data);
                }
            }
            Buffer buffer = new Buffer();
            adapter.toJson(buffer, document);
            return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
        }
    }
}