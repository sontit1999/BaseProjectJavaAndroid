package com.example.basejavaandroid.ui.lifestyle.fragment;

import android.graphics.Color;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basejavaandroid.Film;
import com.example.basejavaandroid.FilmCallback;
import com.example.basejavaandroid.R;
import com.example.basejavaandroid.base.BaseFragment;
import com.example.basejavaandroid.databinding.FragEventCommonBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EventCommonFragment extends BaseFragment<FragEventCommonBinding, EventCommonViewmodel> {
    public static final int TYPE_COMMON = 1;
    public static final int TYPE_BOOKMARK = 2;
    int typeFrag = 0;

    public EventCommonFragment(int type) {
        typeFrag = type;
    }

    @Override
    protected Class<EventCommonViewmodel> getViewModel() {
        return EventCommonViewmodel.class;
    }

    @Override
    protected void onViewCreated() {
        if (typeFrag == TYPE_COMMON) {
            binding.container.setBackgroundColor(Color.YELLOW);
        }
        if (typeFrag == TYPE_BOOKMARK) {
            binding.container.setBackgroundColor(Color.GREEN);
        }
        initRecyclerView();
        viewmodel.getArrFilm().observe(this, new Observer<List<Film>>() {
            @Override
            public void onChanged(List<Film> films) {
                viewmodel.filmAdapter.addMore(films);
                viewmodel.filmAdapter.setCallback(new FilmCallback() {
                    @Override
                    public void onClickFilm(Film film) {
                        Toast.makeText(getActivity(), "click " + film.getName(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onLongClick(Film film) {

                    }
                });
            }
        });
    }

    private void initRecyclerView() {
        binding.rvFilms.setHasFixedSize(true);
        binding.rvFilms.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        binding.rvFilms.setAdapter(viewmodel.filmAdapter);
        binding.rvFilms.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager = (LinearLayoutManager) binding.rvFilms.getLayoutManager();
                if (layoutManager.findLastVisibleItemPosition() + 2 >= binding.rvFilms.getAdapter().getItemCount()) {
                    viewmodel.isLoading.setValue(true);
                    viewmodel.loadMoreFilm();
                }

            }
        });

    }

    @Override
    protected void setBindingViewmodel() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_event_common;
    }
}
