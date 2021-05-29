package com.example.basejavaandroid.model;

public class BmiHistoryRes {
    float weight;
    int height;
    float score;
    int id;
    String createAt;
    String updateAt;
    int isActive;

    public BmiHistoryRes(float weight, int height, float score, int id, String createAt, String updateAt, int isActive) {
        this.weight = weight;
        this.height = height;
        this.score = score;
        this.id = id;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.isActive = isActive;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public String getWeightHeight() {
        return weight + "kg," + height + "cm";
    }
}
