package com.tutorials.petersonsproject.network.hierarchy;

import com.squareup.moshi.Json;

public class D2lSessionValResponse {

    @Json(name = "Name")
    private String name;
    @Json(name = "Domain")
    private String domain;


    @Json(name = "Value")
    private String value;
    @Json(name = "Expires")
    private Long expires;

    public String getName() {
        return name;
    }
    public String getDomain() {
        return domain;
    }


    public Long getExpires() {
        return expires;
    }

    public String getValue() {
        return value;
    }

}
