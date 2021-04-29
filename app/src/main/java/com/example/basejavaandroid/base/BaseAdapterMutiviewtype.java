package com.example.basejavaandroid.base;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapterMutiviewtype<T ,V1 extends ViewDataBinding,V2 extends ViewDataBinding> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<T> dataList = new ArrayList<>();


    class ViewHolder1<T,VB extends ViewDataBinding> extends RecyclerView.ViewHolder{
        private VB binding;
        public ViewHolder1(@NonNull VB binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void setVariable(int id,T t){
            this.binding.setVariable(id,t);
        }
        public void setClickAdapter(int id,BaseCBAdapter t){
            this.binding.setVariable(id,t);
        }
    }
    class ViewHolder2<T,VB extends ViewDataBinding> extends RecyclerView.ViewHolder{
        private VB binding;
        public ViewHolder2(@NonNull VB binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void setVariable(int id,T t){
            this.binding.setVariable(id,t);
        }
        public void setClickAdapter(int id,BaseCBAdapter t){
            this.binding.setVariable(id,t);
        }
    }



}
