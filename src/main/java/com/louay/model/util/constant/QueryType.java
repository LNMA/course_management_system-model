package com.louay.model.util.constant;

public enum QueryType {
    UPDATE("update"), SELECT("select");

    private String queryType;

    QueryType(String queryType) {
        this.queryType = queryType;
    }

    public String getQueryType() {
        return queryType;
    }
}
