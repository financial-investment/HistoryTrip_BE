package com.ssafy.history.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "History concept tag")
public class HistoryTagDto {
    private Long tagId;
    private String tagName;
    private String tagType;

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }
}
