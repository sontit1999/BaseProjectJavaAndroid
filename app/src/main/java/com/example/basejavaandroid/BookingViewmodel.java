package com.example.basejavaandroid;

import android.os.CountDownTimer;

import androidx.lifecycle.MutableLiveData;

import com.example.basejavaandroid.base.BaseViewmodel;

import java.util.ArrayList;
import java.util.List;

public class BookingViewmodel extends BaseViewmodel {
    public MutableLiveData<String> nameRap = new MutableLiveData<>();
    public MutableLiveData<List<Seat>> arrSeat = new MutableLiveData<>();
    public SeatAdapter seatAdapter = new SeatAdapter();

    public MutableLiveData<List<Seat>> getArrSeat() {
        List<Seat> list = new ArrayList<>();
        list.add(new Seat("A",false,false));
        list.add(new Seat("A1",false));
        list.add(new Seat("A2",false));
        list.add(new Seat("A3",false));
        list.add(new Seat("A4",false));
        list.add(new Seat("A5",false));
        list.add(new Seat("A6",false));
        list.add(new Seat("A7",false));
        list.add(new Seat("A8",false));
        list.add(new Seat("A9",false));
        list.add(new Seat("A10",false));
        list.add(new Seat("B",false,false));
        list.add(new Seat("B1",false));
        list.add(new Seat("B2",false));
        list.add(new Seat("B3",false));
        list.add(new Seat("B4",false));
        list.add(new Seat("B5",false));
        list.add(new Seat("B6",false));
        list.add(new Seat("B7",false));
        list.add(new Seat("B8",false));
        list.add(new Seat("B9",false));
        list.add(new Seat("B10",false));
        list.add(new Seat("C",false,false));
        list.add(new Seat("C1",false));
        list.add(new Seat("C2",false));
        list.add(new Seat("C3",false));
        list.add(new Seat("C4",false));
        list.add(new Seat("C5",false));
        list.add(new Seat("C6",false));
        list.add(new Seat("C7",false));
        list.add(new Seat("C8",false));
        list.add(new Seat("C9",false));
        list.add(new Seat("C10",false));
        list.add(new Seat("D",false,false));
        list.add(new Seat("D1",false));
        list.add(new Seat("D2",false));
        list.add(new Seat("D3",false));
        list.add(new Seat("D4",false));
        list.add(new Seat("D5",false));
        list.add(new Seat("D6",false));
        list.add(new Seat("D7",false));
        list.add(new Seat("D8",false));
        list.add(new Seat("D9",false));
        list.add(new Seat("D10",false));
        list.add(new Seat("E",false,false));
        list.add(new Seat("E1",false));
        list.add(new Seat("E2",false));
        list.add(new Seat("E3",false));
        list.add(new Seat("E4",false));
        list.add(new Seat("E5",false));
        list.add(new Seat("E6",false));
        list.add(new Seat("E7",false));
        list.add(new Seat("E8",false));
        list.add(new Seat("E9",false));
        list.add(new Seat("E10",false));
        arrSeat.postValue(list);
        return arrSeat;
    }

    public MutableLiveData<String> getNameRap() {
        nameRap.postValue("BHD start default");
        return nameRap;
    }

    private MutableLiveData<Booking> booking = new MutableLiveData<>();

    public MutableLiveData<Booking> getBooking() {
        Booking bk = new Booking("BHD star discovery","Screen 4","26 tháng 4,2021","18:30~20:45");
        booking.postValue(bk);
        return booking;
    }
}
