package com.example.basejavaandroid.ui.lifestyle;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basejavaandroid.R;
import com.example.basejavaandroid.base.BaseActivity;
import com.example.basejavaandroid.databinding.ActivityEventLongbienBinding;
import com.example.basejavaandroid.model.Event;

import java.util.ArrayList;

public class EventLongBienActivity extends BaseActivity<ActivityEventLongbienBinding, EventLongBienViewModel> {
    @Override
    protected void getData() {
        viewmodel.arrEvent.observe(this, new Observer<ArrayList<Event>>() {
            @Override
            public void onChanged(ArrayList<Event> events) {
                viewmodel.eventLongBienAdapter.setListEvent(events);
            }
        });
    }

    @Override
    protected void initEvent() {
        initRecyclerView();
        viewmodel.loadEvent();
    }

    private void initRecyclerView() {
        binding.rvEvent.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        binding.rvEvent.setAdapter(viewmodel.eventLongBienAdapter);
    }

    @Override
    protected void setBindingViewmodel() {

    }

    @Override
    protected Class<EventLongBienViewModel> getViewModel() {
        return EventLongBienViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_event_longbien;
    }
}
