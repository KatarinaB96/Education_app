package com.tutorials.petersonsproject.network.user;

import androidx.annotation.Nullable;

import com.squareup.moshi.Json;

import java.util.List;

import moe.banana.jsonapi2.JsonApi;
import moe.banana.jsonapi2.Resource;

@JsonApi(type = "users")
public final class UserResponse extends Resource {

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