package com.example.basejavaandroid.model;

public class Idol {
    private String nameIdol;
    private String linkThumb;
    private String linkIdol;

    public Idol(String nameIdol, String linkThumb, String linkIdol) {
        this.nameIdol = nameIdol;
        this.linkThumb = linkThumb;
        this.linkIdol = linkIdol;
    }

    public String getNameIdol() {
        return nameIdol;
    }

    public void setNameIdol(String nameIdol) {
        this.nameIdol = nameIdol;
    }

    public String getLinkThumb() {
        return linkThumb;
    }

    public void setLinkThumb(String linkThumb) {
        this.linkThumb = linkThumb;
    }

    public String getLinkIdol() {
        return linkIdol;
    }

    public void setLinkIdol(String linkIdol) {
        this.linkIdol = linkIdol;
    }
}
