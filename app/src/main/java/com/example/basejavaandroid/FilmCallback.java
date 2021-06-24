package com.example.basejavaandroid;

import com.example.basejavaandroid.base.BaseCBAdapter;

public interface FilmCallback extends BaseCBAdapter {
    void onClickFilm(Film film);
    void onLongClick(Film film);

    void onClickFilmPos(int pos);

    void onViewMore();
}
