package com.example.basejavaandroid.model;

public class Review {
    private String imgTenant;
    private String nameTenant;
    private String nameAuthor;
    private String imgAuthor;
    private String timeReview;
    private String content;
    private int numberLike;
    private int numberStar;
    private boolean isLike;

    public Review(String imgTenant, String nameTenant, String nameAuthor, String imgAuthor, String timeReview, String content, int numberLike, int numberStar, boolean isLike) {
        this.imgTenant = imgTenant;
        this.nameTenant = nameTenant;
        this.nameAuthor = nameAuthor;
        this.imgAuthor = imgAuthor;
        this.timeReview = timeReview;
        this.content = content;
        this.numberLike = numberLike;
        this.numberStar = numberStar;
        this.isLike = isLike;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public String getImgTenant() {
        return imgTenant;
    }

    public void setImgTenant(String imgTenant) {
        this.imgTenant = imgTenant;
    }

    public String getNameTenant() {
        return nameTenant;
    }

    public void setNameTenant(String nameTenant) {
        this.nameTenant = nameTenant;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public void setNameAuthor(String nameAuthor) {
        this.nameAuthor = nameAuthor;
    }

    public String getImgAuthor() {
        return imgAuthor;
    }

    public void setImgAuthor(String imgAuthor) {
        this.imgAuthor = imgAuthor;
    }

    public String getTimeReview() {
        return timeReview;
    }

    public void setTimeReview(String timeReview) {
        this.timeReview = timeReview;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNumberLike() {
        return numberLike;
    }

    public void setNumberLike(int numberLike) {
        this.numberLike = numberLike;
    }

    public int getNumberStar() {
        return numberStar;
    }

    public void setNumberStar(int numberStar) {
        this.numberStar = numberStar;
    }
}
