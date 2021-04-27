package com.example.basejavaandroid;

import android.view.View;
import android.widget.Toast;

import com.example.basejavaandroid.base.BaseFragment;
import com.example.basejavaandroid.databinding.FragBookingBinding;

public class BookingFragment extends BaseFragment<FragBookingBinding,BookingViewmodel> {
    @Override
    protected Class<BookingViewmodel> getViewModel() {
        return BookingViewmodel.class;
    }

    @Override
    protected void onViewCreated() {
        binding.tvBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Click Booking Fragment", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void setBindingViewmodel() {
          binding.setViewmodel(viewmodel);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_booking;
    }
}
