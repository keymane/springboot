package com.keymane.web;

import java.util.List;

public class StandardResponseImpl extends BaseStandardResponse {

    private List<?> records;

    private Long totalRecordCount = 0l;

    public List<?> getRecords() {
        return records;
    }

    public void setRecords(List<?> records) {
        this.records = records;
    }

    public Long getTotalRecordCount() {
        return totalRecordCount;
    }

    public void setTotalRecordCount(Long totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
    }
}
