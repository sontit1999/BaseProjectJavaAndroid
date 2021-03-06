package com.example.basejavaandroid.adapter;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

public class FilmsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_LOADING = 1;
    public static final int TYPE_FILMS = 2;

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


    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        if (viewType == TYPE_FILMS) {
            return new MyHolder(DataBindingUtil.inflate(layoutInflater, R.layout.item_film, parent, false));
        } else {
            return new LoadingHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_viewmore, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHolder) {
            ((MyHolder) holder).binData(listFilm.get(position));
        }
        if (holder instanceof LoadingHolder) {

        }
    }

    @Override
    public int getItemCount() {
        return listFilm.size();
    }

    @Override
    public int getItemViewType(int position) {
        return listFilm.get(position) == null ? TYPE_LOADING : TYPE_FILMS;
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
            // t??nh to??n v?? set data
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

    class LoadingHolder extends RecyclerView.ViewHolder {
        TextView tvViewmore;
        public LoadingHolder(@NonNull View itemView) {
            super(itemView);
            tvViewmore = itemView.findViewById(R.id.tvViewMore);

            tvViewmore.setOnClickListener(v -> {
                if (callback != null) {
                    callback.onViewMore();
                }
            });
        }
    }
}
