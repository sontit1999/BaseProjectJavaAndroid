package com.example.basejavaandroid.ui;

import androidx.lifecycle.MutableLiveData;

import com.example.basejavaandroid.adapter.BmiHistoryAdapter;
import com.example.basejavaandroid.base.BaseViewmodel;
import com.example.basejavaandroid.model.BmiHistory;

import java.util.ArrayList;
import java.util.List;

public class BmiHistoryViewmodel  extends BaseViewmodel {
   private MutableLiveData<List<BmiHistory>> arrHistory = new MutableLiveData<>();
   public BmiHistoryAdapter bmiHistoryAdapter = new BmiHistoryAdapter();
    public MutableLiveData<List<BmiHistory>> getArrHistory() {
        List<BmiHistory> list = new ArrayList<>();
        list.add(new BmiHistory(20.5f,100,1.65f,"11:20 9/5"));
        list.add(new BmiHistory(15f,80,1.70f,"10:31 10/5"));
        list.add(new BmiHistory(35f,85,1.50f,"09:28 11/5"));
        list.add(new BmiHistory(30f,30,1.40f,"07:14 12/5"));
        list.add(new BmiHistory(5.5f,75,1.85f,"06:45 13/5"));
        list.add(new BmiHistory(50.0f,50,1.75f,"03:40 14/5"));
        list.add(new BmiHistory(17.5f,75,1.35f,"02:20 15/5"));
        list.add(new BmiHistory(5.5f,75,1.85f,"06:45 17/5"));
        list.add(new BmiHistory(50.0f,50,1.75f,"03:40 18/5"));
        list.add(new BmiHistory(17.5f,75,1.35f,"02:20 19/5"));

        arrHistory.postValue(list);
        return arrHistory;
    }
}
