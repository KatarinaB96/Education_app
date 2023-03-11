package com.tutorials.petersonsproject.network.hierarchy;

import androidx.annotation.Nullable;

import com.squareup.moshi.Json;

import java.util.List;

import moe.banana.jsonapi2.JsonApi;
import moe.banana.jsonapi2.Resource;

@JsonApi(type = "course-hierarchies")
public class CourseHierarchiesResponse extends Resource {

    @Nullable
    @Json(name = "hierarchy")
    private List<ModuleResponse> hierarchy;

    @Nullable
    public List<ModuleResponse> getHierarchy() {
        return hierarchy;
    }


}
