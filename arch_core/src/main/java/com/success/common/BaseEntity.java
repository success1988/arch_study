package com.success.common;


import com.alibaba.fastjson.annotation.JSONField;

public class BaseEntity {
    @JSONField(
            serialize = false
    )
    private int page;
    @JSONField(
            serialize = false
    )
    private int rows = 0;
    @JSONField(
            serialize = false
    )
    private int startRecord;
    @JSONField(
            serialize = false
    )
    private int recordCount;
    @JSONField(
            serialize = false
    )
    private int pageCount;
    @JSONField(
            serialize = false
    )
    private String descField;
    @JSONField(
            serialize = false
    )
    private String ascField;

    public BaseEntity() {
    }

    public int getPage() {
        if (this.page == 0) {
            this.page = 1;
        }

        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return this.rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getStartRecord() {
        if (this.getPage() > 0) {
            this.startRecord = (this.getPage() - 1) * this.getRows();
        } else {
            this.startRecord = 0;
        }

        return this.startRecord;
    }

    public void setStartRecord(int startRecord) {
        this.startRecord = startRecord;
    }

    public int getRecordCount() {
        return this.recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public int getPageCount() {
        if (this.recordCount > 0 && this.getRows() > 0) {
            if (this.recordCount % this.getRows() > 0) {
                this.pageCount = this.recordCount / this.getRows() + 1;
            } else {
                this.pageCount = this.recordCount / this.getRows();
            }
        } else {
            this.pageCount = 1;
        }

        return this.pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getDescField() {
        return this.descField;
    }

    public void setDescField(String descField) {
        this.descField = descField;
    }

    public String getAscField() {
        return this.ascField;
    }

    public void setAscField(String ascField) {
        this.ascField = ascField;
    }
}