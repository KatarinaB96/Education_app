package com.tutorials.petersonsproject.network.hierarchy;

import androidx.annotation.Nullable;

import com.squareup.moshi.Json;

import moe.banana.jsonapi2.JsonApi;
import moe.banana.jsonapi2.Resource;

@JsonApi(type = "session-cookies")
public class SessionCookieResponseApi extends Resource {

    @Nullable
    @Json(name = "id")
    private String id;


    @Json(name = "d2lSessionVal")
    private D2lSessionValResponse d2LSessionValResponse;

    @Json(name = "d2lSecureSessionVal")
    private D2lSecureSessionValResponse d2LSecureSessionValResponse;

    public String getId() {
        if (id == null) {
            return "";
        }
        return id;
    }

    public D2lSessionValResponse getD2lSessionVal() {
        return d2LSessionValResponse;
    }

    public D2lSecureSessionValResponse getD2lSecureSessionVal() {
        return d2LSecureSessionValResponse;
    }
}
