package com.ankitmahala07.virtualthreadredis.model;

public class ZSetEntry {

    private String value;
    private long score;

    public ZSetEntry(String value, long score) {
        this.value = value;
        this.score = score;
    }

    public String getValue() {
        return value;
    }

    public long getScore() {
        return score;
    }
}

