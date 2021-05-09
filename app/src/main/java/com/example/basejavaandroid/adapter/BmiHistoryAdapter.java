package com.example.basejavaandroid.adapter;

import com.example.basejavaandroid.BR;
import com.example.basejavaandroid.R;
import com.example.basejavaandroid.base.BaseAdapter;
import com.example.basejavaandroid.base.BaseCBAdapter;
import com.example.basejavaandroid.databinding.ItemRowHistoryBinding;
import com.example.basejavaandroid.model.BmiHistory;

public class BmiHistoryAdapter  extends BaseAdapter<BmiHistory, ItemRowHistoryBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.item_row_history;
    }

    @Override
    protected int getIdVariable() {
        return BR.bmihistory;
    }

    @Override
    protected int getIdVariableOnclick() {
        return 0;
    }

    @Override
    protected BaseCBAdapter getOnclick() {
        return null;
    }
}
