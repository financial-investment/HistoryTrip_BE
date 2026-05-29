package com.ssafy.history.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DB table row count summary")
public class TableCountDto {
    private String tableName;
    private long rowCount;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public long getRowCount() {
        return rowCount;
    }

    public void setRowCount(long rowCount) {
        this.rowCount = rowCount;
    }
}
