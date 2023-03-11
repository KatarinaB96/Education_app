package com.tutorials.petersonsproject.network.user;

import androidx.annotation.Nullable;

import com.squareup.moshi.Json;

public class AttributesResponse {

    @Nullable
    @Json(name = "email")
    private String email;

    @Json(name = "firstName")
    private String firstName;

    @Json(name = "lastName")
    private String lastName;

    @Json(name = "lmsUserId")
    private Long lmsUserId;


    public String getUserEmail() {
        if (email == null) {
            return "";
        }
        return email;
    }

    public String getUserFirstName() {
        return firstName;
    }

    public String getUserLastName() {
        return lastName;
    }

}
