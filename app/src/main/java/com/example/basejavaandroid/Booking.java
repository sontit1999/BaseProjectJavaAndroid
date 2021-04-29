package com.example.basejavaandroid;

public class Booking {
    private String nameRap;
    private String nameScreen;
    private String date;
    private String rangeTime;

    public Booking(String nameRap, String nameScreen, String date, String rangeTime) {
        this.nameRap = nameRap;
        this.nameScreen = nameScreen;
        this.date = date;
        this.rangeTime = rangeTime;
    }

    public String getNameRap() {
        return nameRap;
    }

    public void setNameRap(String nameRap) {
        this.nameRap = nameRap;
    }

    public String getNameScreen() {
        return nameScreen;
    }

    public void setNameScreen(String nameScreen) {
        this.nameScreen = nameScreen;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRangeTime() {
        return rangeTime;
    }

    public void setRangeTime(String rangeTime) {
        this.rangeTime = rangeTime;
    }
}
