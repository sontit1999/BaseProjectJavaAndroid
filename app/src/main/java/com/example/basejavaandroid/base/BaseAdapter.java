package com.example.basejavaandroid.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T , B extends ViewDataBinding> extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {
    protected List<T> dataList = new ArrayList<>();
    protected abstract int getLayoutId();
    protected abstract int getIdVariable();
    protected abstract int getIdVariableOnclick();
    protected abstract BaseCBAdapter getOnclick();

    public void setList(List<T> arrayList){
        this.dataList = arrayList;
        notifyDataSetChanged();
    }
    public void removeItem(T item){
        int index = dataList.indexOf(item);
        dataList.remove(index);
        notifyItemRemoved(index);
    }
    public void updateItem(int pos,T item){
        dataList.set(pos,item);
        notifyItemChanged(pos);
    }
    public void addItems(T item){
        dataList.add(item);
        notifyItemInserted(dataList.size()-1);
    }
    public ArrayList<T> getList(){
        return (ArrayList<T>) dataList;
    }
    public void addMore(List<T> arrayList){
        int oldCount = this.dataList.size();
        this.dataList.addAll(arrayList);
        notifyItemRangeInserted(oldCount,this.dataList.size());
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        B binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), getLayoutId(), parent, false);
        return new ViewHolder(binding);
    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public void onBindViewHolder(BaseAdapter.ViewHolder holder, int position) {
        holder.setVariable(getIdVariable(),dataList.get(position));
        holder.setVariable(getIdVariable(),dataList.get(position));
        if(getOnclick()!=null){
            holder.setClickAdapter(getIdVariableOnclick(),getOnclick());
        }
    }
    class ViewHolder<T,VB extends ViewDataBinding> extends RecyclerView.ViewHolder{
        private VB binding;
        public ViewHolder(@NonNull VB binding) {
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
