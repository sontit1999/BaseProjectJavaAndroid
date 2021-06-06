package com.example.basejavaandroid.adapter;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.basejavaandroid.Film;
import com.example.basejavaandroid.FilmCallback;
import com.example.basejavaandroid.R;
import com.example.basejavaandroid.databinding.ItemFilmBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class FilmsAdapter extends RecyclerView.Adapter<FilmsAdapter.MyHolder> {
    FilmCallback callback;
    ArrayList<Film> listFilm;
    LayoutInflater layoutInflater;

    public FilmsAdapter(ArrayList<Film> listFilm) {
        this.listFilm = listFilm;
        notifyDataSetChanged();
    }

    public void setCallback(FilmCallback callback) {
        this.callback = callback;
    }

    @NonNull
    @NotNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        return new MyHolder(DataBindingUtil.inflate(layoutInflater, R.layout.item_film, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FilmsAdapter.MyHolder holder, int position) {
        Film film = listFilm.get(position);
        holder.binData(film);
    }

    @Override
    public int getItemCount() {
        return listFilm.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ItemFilmBinding binding;
        CountDownTimer countDownTimer;

        public MyHolder(@NonNull ItemFilmBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (callback != null) {
                        callback.onClickFilmPos(getAdapterPosition());
                    }
                }
            });
        }

        public void binData(Film film) {
            // tính toán và set data
            binding.tvDescibe.setText(film.getDescription());
            binding.tvTime.setText(film.getDuration() + "h" + "-" + film.getDateStart());
            binding.tvName.setText(film.getName());
            Glide.with(binding.ivFilm).load(film.getLinkImage()).into(binding.ivFilm);
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
            long timeRemainInSecond = film.getTimeLeft() - System.currentTimeMillis();
            countDownTimer = new CountDownTimer(timeRemainInSecond, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                    long days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished);
                    millisUntilFinished -= TimeUnit.DAYS.toMillis(days);
                    long hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished);
                    millisUntilFinished -= TimeUnit.HOURS.toMillis(hours);
                    long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                    millisUntilFinished -= TimeUnit.MINUTES.toMillis(minutes);
                    long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);

                    StringBuilder sb = new StringBuilder(64);
                    sb.append(days);
                    sb.append(":");
                    sb.append(hours);
                    sb.append(":");
                    sb.append(minutes);
                    sb.append(":");
                    sb.append(seconds);
                    binding.tvTimeLeft.setText(sb.toString());
                }

                @Override
                public void onFinish() {

                }
            }.start();
        }
    }
}
