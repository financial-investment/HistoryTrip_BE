package com.ssafy.history.region.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Region row from regions")
public class RegionDto {
    private Long regionId;
    private String sidoCode;
    private String sidoName;
    private String gugunCode;
    private String gugunName;

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getSidoCode() {
        return sidoCode;
    }

    public void setSidoCode(String sidoCode) {
        this.sidoCode = sidoCode;
    }

    public String getSidoName() {
        return sidoName;
    }

    public void setSidoName(String sidoName) {
        this.sidoName = sidoName;
    }

    public String getGugunCode() {
        return gugunCode;
    }

    public void setGugunCode(String gugunCode) {
        this.gugunCode = gugunCode;
    }

    public String getGugunName() {
        return gugunName;
    }

    public void setGugunName(String gugunName) {
        this.gugunName = gugunName;
    }
}
