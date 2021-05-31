package com.example.basejavaandroid.ui.lifestyle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.basejavaandroid.R;
import com.example.basejavaandroid.model.Event;

import java.util.ArrayList;

public class EventLongBienAdapter extends RecyclerView.Adapter<EventLongBienAdapter.EventViewHoder> {
    ArrayList<Event> listEvent = new ArrayList<>();

    public ArrayList<Event> getListEvent() {
        return listEvent;
    }

    public void setListEvent(ArrayList<Event> listEvent) {
        this.listEvent = listEvent;
        notifyDataSetChanged();
    }

    public void addEvent(ArrayList<Event> events) {
        if (listEvent != null) {
            int currentSize = listEvent.size();
            listEvent.addAll(events);
            notifyItemRangeInserted(currentSize, events.size());
        }
    }

    @Override
    public EventViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EventViewHoder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event_bookmark, parent, false));
    }

    @Override
    public void onBindViewHolder(EventLongBienAdapter.EventViewHoder holder, int position) {
        Event event = listEvent.get(position);
        holder.binData(event);
    }

    @Override
    public int getItemCount() {
        return listEvent.size();
    }

    public class EventViewHoder extends RecyclerView.ViewHolder {
        TextView tvNameEvent, tvTime, tvLocation, tvShortContent;
        ImageView ivEvent;

        public EventViewHoder(View itemView) {
            super(itemView);
            tvNameEvent = (TextView) itemView.findViewById(R.id.tvNameEvent);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            tvLocation = (TextView) itemView.findViewById(R.id.tvLocation);
            tvShortContent = (TextView) itemView.findViewById(R.id.tvShortContent);
            ivEvent = (ImageView) itemView.findViewById(R.id.ivEvent);
        }

        public void binData(Event event) {
            tvNameEvent.setText(event.getTitle());
            tvTime.setText(event.getTime());
            tvLocation.setText(event.getAddress());
            tvShortContent.setText(event.getShortContent());
            Glide.with(ivEvent).load(event.getImageUrl()).into(ivEvent);
        }
    }
}
