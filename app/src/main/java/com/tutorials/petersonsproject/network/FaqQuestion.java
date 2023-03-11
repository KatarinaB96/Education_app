package com.tutorials.petersonsproject.network;

import com.squareup.moshi.Json;

public final class FaqQuestion {

    @Json(name = "heading")
    private String heading;

    @Json(name = "body")
    private String body;

    public String getHeading() {
        return heading;
    }

    public String getBody() {
        return body;
    }
}