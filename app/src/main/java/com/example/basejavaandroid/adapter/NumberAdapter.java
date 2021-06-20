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

public class NumberAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    public static final int TYPE_NUMBER = 1;
    numberCallBack callBack;
    public static final int TYPE_LOADING = 2;
    ArrayList<Number> listNum;

    public NumberAdapter(Context context, ArrayList<Number> list) {
        this.context = context;
        this.listNum = list;
        callBack = (numberCallBack) context;
    }

    public void setList(ArrayList<Number> list) {
        this.listNum = list;
        notifyDataSetChanged();
    }


    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_LOADING) {
            return new LoadingHolder(LayoutInflater.from(context).inflate(R.layout.item_loading, parent, false));
        } else {
            return new ItemHolder(LayoutInflater.from(context).inflate(R.layout.item_number, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemHolder) {
            ((ItemHolder) holder).binData(listNum.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return listNum.get(position) == null ? TYPE_LOADING : TYPE_NUMBER;
    }

    @Override
    public int getItemCount() {
        return listNum.size();
    }

    public interface numberCallBack {
        void onClickNumber(int pos);
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        TextView tvNumber;

        public ItemHolder(@NonNull @NotNull View itemView) {
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

    class LoadingHolder extends RecyclerView.ViewHolder {
        public LoadingHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
