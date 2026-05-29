package com.ssafy.history.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "User submitted historical hotplace")
public class HotplaceDto {
    private Long hotplaceId;
    private Long userId;
    private String userNickname;
    private Long linkedPlaceId;
    private String linkedPlaceTitle;
    private String title;
    private String content;
    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Long getHotplaceId() {
        return hotplaceId;
    }

    public void setHotplaceId(Long hotplaceId) {
        this.hotplaceId = hotplaceId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public Long getLinkedPlaceId() {
        return linkedPlaceId;
    }

    public void setLinkedPlaceId(Long linkedPlaceId) {
        this.linkedPlaceId = linkedPlaceId;
    }

    public String getLinkedPlaceTitle() {
        return linkedPlaceTitle;
    }

    public void setLinkedPlaceTitle(String linkedPlaceTitle) {
        this.linkedPlaceTitle = linkedPlaceTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
