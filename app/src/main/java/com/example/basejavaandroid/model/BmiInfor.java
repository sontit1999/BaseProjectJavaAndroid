package com.example.basejavaandroid.model;

import java.io.Serializable;
import java.text.DecimalFormat;

public class BmiInfor implements Serializable {
    private float w;
    private float h;
    private int posGendle;
    private final float scoreBMI;

    public float getScoreBMI() {
        float hMet = h/100;
        return Float.parseFloat(new DecimalFormat("#.#").format(w / (hMet * hMet)).replace(',', '.'));
    }
    public BmiInfor(float w, float h, int posGendle) {
        this.w = w;
        this.h = h;
        this.posGendle = posGendle;
        scoreBMI = 0;
    }

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }

    public float getH() {
        return h;
    }

    public void setH(float h) {
        this.h = h;
    }

    public int getPosGendle() {
        return posGendle;
    }

    public void setPosGendle(int posGendle) {
        this.posGendle = posGendle;
    }
}
