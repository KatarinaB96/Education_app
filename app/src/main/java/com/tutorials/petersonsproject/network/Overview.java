package com.tutorials.petersonsproject.network;

import com.squareup.moshi.Json;

public final class Overview {

    @Json(name = "app_details_image_light")
    public String image;

    @Json(name = "app_details_description")
    public String description;

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }
}