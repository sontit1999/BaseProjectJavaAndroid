package com.example.basejavaandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basejavaandroid.R;
import com.example.basejavaandroid.model.Number;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.ViewHoder> {
    Context context;
    ArrayList<Number> list;
    numberCallBack callBack;

    public NumberAdapter(Context context, ArrayList<Number> list) {
        this.context = context;
        this.list = list;
        callBack = (numberCallBack) context;
    }

    public void setList(ArrayList<Number> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHoder(LayoutInflater.from(context).inflate(R.layout.item_number, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NumberAdapter.ViewHoder holder, int position) {
        Number number = list.get(position);
        holder.binData(number);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface numberCallBack {
        void onClickNumber(int pos);
    }

    class ViewHoder extends RecyclerView.ViewHolder {
        TextView tvNumber;

        public ViewHoder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            tvNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (callBack != null) {
                        callBack.onClickNumber(getAdapterPosition());
                    }
                }
            });
        }

        public void binData(Number num) {
            tvNumber.setText(num.getNumber() + "");
        }
    }
}
