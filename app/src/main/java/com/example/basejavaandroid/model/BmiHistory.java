package com.example.basejavaandroid.model;

public class BmiHistory {
    private float score;
    private float weright;
    private float height;
    private String time;

    public BmiHistory(float score, float weright, float height, String time) {
        this.score = score;
        this.weright = weright;
        this.height = height;
        this.time = time;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public float getWeright() {
        return weright;
    }

    public void setWeright(float weright) {
        this.weright = weright;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
