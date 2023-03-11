package com.tutorials.petersonsproject.network.hierarchy;

import androidx.annotation.Nullable;

import com.squareup.moshi.Json;

import java.util.List;

public class ContentHierarchyResponse {

    @Json(name = "Id")
    private long id;

    @Nullable
    @Json(name = "TopicType")
    private Integer topicType;

    @Nullable
    @Json(name = "Url")
    private String url;

    @Nullable
    @Json(name = "Type")
    private Integer type;

    @Json(name = "Title")
    private String title;

    @Nullable
    @Json(name = "ToolItemId")
    private Integer toolItemId;

    @Nullable
    @Json(name = "GradeItemId")
    private Integer gradeItemId;

    @Nullable
    @Json(name = "content")
    private List<ContentHierarchyResponse> content;

    public ContentHierarchyResponse() {
    }


    public long getContentId() {
        return id;
    }


    public int getTopicType() {
        if (topicType == null) {
            return 0;
        }
        return topicType;
    }
    public int getType() {
        if (type == null) {
            return 0;
        }
        return type;
    }


    public int getToolItemId() {
        if (toolItemId == null) {
            return 0;
        }
        return toolItemId;
    }

    public int getGradeItemId() {
        if (gradeItemId == null) {
            return 0;
        }
        return gradeItemId;
    }

    @Nullable
    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }


    @Nullable
    public List<ContentHierarchyResponse> getHierarchyContent() {
        return content;
    }
}
