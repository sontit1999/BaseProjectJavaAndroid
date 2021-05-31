package com.example.basejavaandroid.model;

public class Event {
    private int Id;
    private String imageUrl;
    private String title;
    private String time;
    private String address;
    private String shortContent;
    private boolean isSave;

    public Event(int id, String imageUrl, String title, String time, String address, String shortContent, boolean isSave) {
        Id = id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.time = time;
        this.address = address;
        this.shortContent = shortContent;
        this.isSave = isSave;
    }

    public Event() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getShortContent() {
        return shortContent;
    }

    public void setShortContent(String shortContent) {
        this.shortContent = shortContent;
    }

    public boolean isSave() {
        return isSave;
    }

    public void setSave(boolean save) {
        isSave = save;
    }
}
