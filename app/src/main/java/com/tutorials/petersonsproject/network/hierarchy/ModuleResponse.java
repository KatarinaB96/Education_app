package com.tutorials.petersonsproject.network.hierarchy;

import androidx.annotation.Nullable;

import com.squareup.moshi.Json;

import java.util.List;


public class ModuleResponse {

    @Json(name = "Id")
    private long id;

    @Json(name = "Title")
    private String title;

    @Nullable
    @Json(name = "content")
    private List<ContentHierarchyResponse> contents;

    public long getHierarchyId() {
        return id;
    }

    public String getHierarchyTitle() {
        return title;
    }

    @Nullable
    public List<ContentHierarchyResponse> getContentResponse() {
        return contents;
    }

}
