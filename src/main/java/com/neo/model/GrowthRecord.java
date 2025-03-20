package com.neo.model;


public class GrowthRecord {
    private String date;
    private String record;

    public GrowthRecord(String date, String record) {
        this.date = date;
        this.record = record;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }
}
