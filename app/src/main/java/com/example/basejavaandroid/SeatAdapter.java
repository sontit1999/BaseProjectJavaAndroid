package com.example.basejavaandroid;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class SeatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_SEAT = 0;
    public static final int TYPE_ROWSEAT = 1;
    List<Seat> list = new ArrayList<>();
    Context context;
    SeatCallback callback;

    public void setContext(Context context) {
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getTotalMoney(){
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        List<Seat> arrSeatChoose = list.stream().filter(s->s.isSelected()).collect(Collectors.toList());
        int totalMoney = ( arrSeatChoose.size() * 60000 ) ;
        String money = currencyVN.format(totalMoney);
        return money;
    }
    public List<Seat> getList() {
        return list;
    }

    public SeatAdapter() {
    }

    public void setCallback(SeatCallback callback) {
        this.callback = callback;
    }
    public SeatAdapter(Context context) {
       this.context = context;
    }

    public SeatAdapter(List<Seat> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setList(List<Seat> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType==TYPE_SEAT){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seat,parent,false);
            return new SeatHoder(view);
        }else if(viewType==TYPE_ROWSEAT){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_namerowseat,parent,false);
            return new RowSeatHoder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Seat seat = list.get(position);
        if(seat.isSeat()){
            SeatHoder seatHoder = (SeatHoder) holder;
            if(seat.isSelected()){
                seatHoder.ivSeat.setColorFilter(context.getResources().getColor(android.R.color.holo_orange_light), PorterDuff.Mode.SRC_IN);
            }else{
                seatHoder.ivSeat.setColorFilter(context.getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);
            }
            seatHoder.ivSeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onSeatClick(seat);
                }
            });
        }else{
           RowSeatHoder rowSeatHoder = (RowSeatHoder) holder;
           rowSeatHoder.tvRowSeat.setText(seat.getNameSeat());
        }
    }

    @Override
    public int getItemViewType(int position) {
        Seat seat = list.get(position);
        if(seat.isSeat()){
            return TYPE_SEAT;
        }else {
            return TYPE_ROWSEAT;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SeatHoder extends RecyclerView.ViewHolder {
        ImageView ivSeat;
        public SeatHoder(@NonNull View itemView) {
            super(itemView);
            ivSeat = (ImageView) itemView.findViewById(R.id.ivSeat);
        }
    }
    public class RowSeatHoder extends RecyclerView.ViewHolder {
        TextView tvRowSeat;
        public RowSeatHoder(@NonNull View itemView) {
            super(itemView);
            tvRowSeat = (TextView) itemView.findViewById(R.id.tvNameRowSeat);
        }
    }
}
