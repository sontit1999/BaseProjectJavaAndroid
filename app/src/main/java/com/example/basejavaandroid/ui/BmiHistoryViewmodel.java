package com.example.basejavaandroid.ui;

import androidx.lifecycle.MutableLiveData;

import com.example.basejavaandroid.adapter.BmiHistoryAdapter;
import com.example.basejavaandroid.base.BaseViewmodel;
import com.example.basejavaandroid.model.BmiHistoryRes;

import java.util.ArrayList;
import java.util.List;

public class BmiHistoryViewmodel extends BaseViewmodel {
    private final MutableLiveData<List<BmiHistoryRes>> arrHistory = new MutableLiveData<>();
    public BmiHistoryAdapter bmiHistoryAdapter = new BmiHistoryAdapter();

    public MutableLiveData<List<BmiHistoryRes>> getArrHistory() {
        List<BmiHistoryRes> list = new ArrayList<>();
        list.add(new BmiHistoryRes(40, 180, 30f, 1, "2021-05-19 08:54:15", "2021-05-19 08:54:15", 5));
        list.add(new BmiHistoryRes(50, 155, 18.5f, 1, "2021-05-19 08:54:15", "2021-05-19 08:54:15", 5));
        list.add(new BmiHistoryRes(45, 178, 25f, 1, "2021-05-19 08:54:15", "2021-05-19 08:54:15", 5));
        list.add(new BmiHistoryRes(60, 157, 27.7f, 1, "2021-05-19 08:54:15", "2021-05-19 08:54:15", 5));
        list.add(new BmiHistoryRes(70, 165, 31.5f, 1, "2021-05-19 08:54:15", "2021-05-19 08:54:15", 5));
        list.add(new BmiHistoryRes(65, 135, 40.8f, 1, "2021-05-19 08:54:15", "2021-05-19 08:54:15", 5));
        list.add(new BmiHistoryRes(56, 187, 6.7f, 1, "2021-05-19 08:54:15", "2021-05-19 08:54:15", 5));
        arrHistory.postValue(list);
        return arrHistory;
    }
}
