package com.example.basejavaandroid;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Film implements Serializable {
    private int Id;
    private String name;
    private String linkImage;
    private float duration;
    private String description;
    long timeLeft = 0;
    private String dateStart;

    public Film(int id, String name, String linkImage, float duration, String description, String dateStart) {
        this.Id = id;
        this.name = name;
        this.linkImage = linkImage;
        this.duration = duration;
        this.description = description;
        this.dateStart = dateStart;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public long getTimeLeft() {
        if (timeLeft == 0) {
            // convert string date to time second
            SimpleDateFormat formatter6 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
            try {
                return formatter6.parse(dateStart).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
                return 0;
            }
        }
        return timeLeft;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setTimeLeft(long timeLeft) {
        this.timeLeft = timeLeft;
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
