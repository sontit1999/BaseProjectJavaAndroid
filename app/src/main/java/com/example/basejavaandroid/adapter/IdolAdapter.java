package com.example.basejavaandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.basejavaandroid.databinding.ItemIdolBinding;
import com.example.basejavaandroid.model.Idol;

import java.util.ArrayList;

public class IdolAdapter extends RecyclerView.Adapter<IdolAdapter.IdolHolder> {
    IdolCallback callback;
    Context context;
    ArrayList<Idol> arrIdol;
    private LayoutInflater layoutInflater;

    public void setCallback(IdolCallback callback) {
        this.callback = callback;
    }

    public IdolAdapter(Context context, ArrayList<Idol> arrIdol) {
        this.context = context;
        this.arrIdol = arrIdol;
    }

    @NonNull
    @Override
    public IdolHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        return new IdolHolder(ItemIdolBinding.inflate(layoutInflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull IdolAdapter.IdolHolder holder, int position) {
        holder.binData(arrIdol.get(position));
    }

    @Override
    public int getItemCount() {
        return arrIdol.size();
    }

    class IdolHolder extends RecyclerView.ViewHolder{
        ItemIdolBinding binding;
        public IdolHolder(@NonNull ItemIdolBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
            binding.containerIdol.setOnClickListener(v -> {
                if(callback!=null){
                    callback.itemClicked(getAdapterPosition());
                }
            });
        }
        public void binData(Idol idol){
            Glide.with(binding.ivIdol).load(idol.getLinkThumb()).into(binding.ivIdol);
            binding.tvNameIdol.setText(idol.getNameIdol());
        }
    }
    public interface IdolCallback {
        void itemClicked(int position);
    }
}
