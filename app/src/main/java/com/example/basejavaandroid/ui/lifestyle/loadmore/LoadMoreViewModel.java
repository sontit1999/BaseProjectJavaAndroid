package com.example.basejavaandroid.ui.lifestyle.loadmore;

import android.os.CountDownTimer;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.basejavaandroid.base.BaseViewmodel;
import com.example.basejavaandroid.model.Number;

import java.util.ArrayList;

public class LoadMoreViewModel extends BaseViewmodel {
    public ArrayList<Number> mListNumber = new ArrayList<>();
    CountDownTimer countDownTimer;
    boolean isLoadMore = false;
    int currentPage = 1;
    int totalPage = 1;
    MutableLiveData<ActionLoadMore> actionState = new MutableLiveData<>();

    public LoadMoreViewModel() {
        getNumber();
    }

    public void getNumber() {
        // list number from API
        ArrayList<Number> list = new ArrayList<>();
        for (int i = 0; i <= currentPage * 30; i++) {
            list.add(new Number(i));
        }
        mListNumber.addAll(list);
        actionState.setValue(new ActionLoadMore.DataChange());
    }

    public void getMoreNumber() {

        if (countDownTimer != null) {
            countDownTimer = null;
        }
        countDownTimer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.d("sondz", millisUntilFinished / 1000 + "");
            }

            @Override
            public void onFinish() {

                ArrayList<Number> list = new ArrayList<>();
                for (int i = 50; i <= 60; i++) {
                    list.add(new Number(i));
                }


                actionState.setValue(new ActionLoadMore.DataLoadmoreSuccess(list.size()));
                mListNumber.addAll(list);
                actionState.setValue(new ActionLoadMore.DataChange());
                // actionState.setValue(new ActionLoadMore.DataInsert(list.size()));
                isLoadMore = false;
            }
        };
        countDownTimer.start();


    }

    static class ActionLoadMore {
        static class DataChange extends ActionLoadMore {

        }

        static class DataInsert extends ActionLoadMore {
            int count;

            public DataInsert(int count) {
                this.count = count;
            }
        }

        static class DataLoadmoreSuccess extends ActionLoadMore {
            int count;

            public DataLoadmoreSuccess(int count) {
                this.count = count;
            }
        }
    }
}
