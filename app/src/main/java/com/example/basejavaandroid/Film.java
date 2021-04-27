package com.example.basejavaandroid;

import java.io.Serializable;

public class Film implements Serializable {
    private int Id;
    private String name;
    private String linkImage;
    private float duration;
    private String description;
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
    public Film(int id,String name, String linkImage, float duration, String description) {
        this.Id = id;
        this.name = name;
        this.linkImage = linkImage;
        this.duration = duration;
        this.description = description;
    }

    public Film() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
