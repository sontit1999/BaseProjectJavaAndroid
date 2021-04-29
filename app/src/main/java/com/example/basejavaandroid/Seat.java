package com.example.basejavaandroid;

public class Seat {
    private String nameSeat;
    private boolean isSelected;
    private boolean isSeat;

    public boolean isSeat() {
        return isSeat;
    }

    public void setSeat(boolean seat) {
        isSeat = seat;
    }

    public Seat(String nameSeat, boolean isSelected) {
        this.nameSeat = nameSeat;
        this.isSelected = isSelected;
        this.isSeat = true;
    }

    public Seat(String nameSeat, boolean isSelected, boolean isSeat) {
        this.nameSeat = nameSeat;
        this.isSelected = isSelected;
        this.isSeat = isSeat;
    }

    public String getNameSeat() {
        return nameSeat;
    }

    public void setNameSeat(String nameSeat) {
        this.nameSeat = nameSeat;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
