package com.keymane.domain;

public abstract class BaseExampleObject {

    private Long offset;

    private Integer limit;

    protected Long longParameter;

    protected Boolean booleanParameter;

    protected String stringParameter;

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getLongParameter() {
        return longParameter;
    }

    public void setLongParameter(Long longParameter) {
        this.longParameter = longParameter;
    }

    public Boolean getBooleanParameter() {
        return booleanParameter;
    }

    public void setBooleanParameter(Boolean booleanParameter) {
        this.booleanParameter = booleanParameter;
    }

    public String getStringParameter() {
        return stringParameter;
    }

    public void setStringParameter(String stringParameter) {
        this.stringParameter = stringParameter;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}

