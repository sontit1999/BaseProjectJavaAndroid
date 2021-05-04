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
        if(dataList!=null && dataList.size()>0){
            int index = dataList.indexOf(item);
            dataList.remove(index);
            notifyItemRemoved(index);
        }
    }
    public void insertItemTop(T item) {
        int position = 0;
        if (dataList != null) {
            dataList.add(position, item);
            notifyItemInserted(position);
        }
    }
    public void insertItemBottom(T item) {
        if (dataList != null) {
            int position = dataList.size();
            if (item != null) {
                dataList.add(position, item);
                notifyItemInserted(position);
            }
        }
    }
    public void updateItem(int pos,T item){
        if (item != null) {
            List<T> list = getList();

            int itemPosition = list.indexOf(item);

            if (itemPosition >= 0 && itemPosition < list.size()) {
                list.set(itemPosition, item);
            } else {
                list.add(item);
            }
            notifyItemChanged(pos);
        }

    }
    public void addItems(T item){
        dataList.add(item);
        notifyItemInserted(dataList.size()-1);
    }
    public void addItemsAtPostition(int pos,T item){
        if (dataList != null) {
            if (item != null) {
                dataList.add(pos, item);
                notifyItemInserted(pos);
            }
        }
    }
    public void clear() {
        if (dataList != null) {
            if (dataList.size() > 0) {
                dataList.clear();
            }
            notifyDataSetChanged();
        }
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
