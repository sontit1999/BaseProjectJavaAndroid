package com.example.basejavaandroid;

import android.os.Build;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.basejavaandroid.base.BaseFragment;
import com.example.basejavaandroid.databinding.FragBookingBinding;

import java.util.List;
import java.util.stream.Collectors;

public class BookingFragment extends BaseFragment<FragBookingBinding,BookingViewmodel> {
    CountDownTimer countDownTimer;
    Film film;

    public void setFilm(Film film) {
        this.film = film;
    }

    @Override
    protected Class<BookingViewmodel> getViewModel() {
        return BookingViewmodel.class;
    }

    @Override
    protected void onViewCreated() {
        initRecyclerView();
        initEvent();
        if(film!=null){
            binding.tvNamefilm.setText(film.getName());
        }
        viewmodel.getBooking().observe(this, new Observer<Booking>() {
            @Override
            public void onChanged(Booking booking) {
                binding.setBookinginfo(booking);
            }
        });
        viewmodel.getArrSeat().observe(this, new Observer<List<Seat>>() {
            @Override
            public void onChanged(List<Seat> seats) {
               viewmodel.seatAdapter.setList(seats);
               viewmodel.seatAdapter.setCallback(new SeatCallback() {
                   @RequiresApi(api = Build.VERSION_CODES.N)
                   @Override
                   public void onSeatClick(Seat seat) {
                       if(seat.isSelected()){
                           Toast.makeText(getActivity(),"UnChoose seat " + seat.getNameSeat() , Toast.LENGTH_SHORT).show();
                       }else{
                           Toast.makeText(getActivity(), "Choose seat " + seat.getNameSeat(), Toast.LENGTH_SHORT).show();
                       }
                       int index =  viewmodel.seatAdapter.getList().indexOf(seat);
                       seat.setSelected(!seat.isSelected());
                       viewmodel.seatAdapter.getList().set(index,seat);
                       viewmodel.seatAdapter.notifyItemChanged(index);
                       binding.tvPay.setText(viewmodel.seatAdapter.getTotalMoney());
                   }
               });
            }
        });
    }

    private void initRecyclerView() {
        viewmodel.seatAdapter.setContext(getContext());
        binding.rvSeat.setHasFixedSize(true);
        binding.rvSeat.setLayoutManager(new GridLayoutManager(getContext(),11));
    }

    private void initEvent() {
        countDownTimer = new CountDownTimer(1*60*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int timeleftMilisecond = (int) (millisUntilFinished/1000);
                int minute = timeleftMilisecond/60;
                int second = timeleftMilisecond%60;
                String valueTimleft = minute + ":" + second;
                binding.tvTimeLeft.setText(valueTimleft);
            }

            @Override
            public void onFinish() {
                Toast.makeText(getActivity(), "Hết giờ rùi nha !!", Toast.LENGTH_SHORT).show();
            }
        };
        countDownTimer.start();
       binding.ivBack.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               getActivity().finish();
           }
       });
       binding.btnFinishPayment.setOnClickListener(new View.OnClickListener() {
           @RequiresApi(api = Build.VERSION_CODES.N)
           @Override
           public void onClick(View v) {
               /*Toast.makeText(getActivity(),"Đặt vé thành công rồi nha!",Toast.LENGTH_LONG).show();
               List<Seat> arrSeatChoose = viewmodel.arrSeat.getValue().stream().filter(s->s.isSelected()).collect(Collectors.toList());
               getActivity().finish();*/
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

    @Override
    public void onPause() {
        super.onPause();
        Log.d("sondz","on pause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("sondz","on stop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("sondz","on destroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("sondz","on detack");
    }
}
