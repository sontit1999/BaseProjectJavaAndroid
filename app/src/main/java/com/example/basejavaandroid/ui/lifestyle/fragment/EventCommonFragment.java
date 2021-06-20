package com.example.basejavaandroid.ui.lifestyle.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.basejavaandroid.Film;
import com.example.basejavaandroid.FilmCallback;
import com.example.basejavaandroid.R;
import com.example.basejavaandroid.adapter.FilmsAdapter;
import com.example.basejavaandroid.base.BaseFragment;
import com.example.basejavaandroid.databinding.FragEventCommonBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class EventCommonFragment extends BaseFragment<FragEventCommonBinding, EventCommonViewmodel> {
    public static final int TYPE_COMMON = 1;
    public static final int TYPE_BOOKMARK = 2;
    int typeFrag;

    FilmsAdapter filmsAdapter;

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
        viewmodel.actionState.observe(this, new Observer<EventCommonViewmodel.ActionFilms>() {
            @Override
            public void onChanged(EventCommonViewmodel.ActionFilms actionFilms) {
                if (actionFilms instanceof EventCommonViewmodel.ActionFilms.DataChange) {
                    filmsAdapter.notifyDataSetChanged();
                }
                if (actionFilms instanceof EventCommonViewmodel.ActionFilms.DataLoadmoreSuccess) {
                    viewmodel.arrFilm.remove(viewmodel.arrFilm.size() - 1);
                    filmsAdapter.notifyItemRemoved(viewmodel.arrFilm.size());
                }
                if (actionFilms instanceof EventCommonViewmodel.ActionFilms.DataLoaded) {
                    viewmodel.arrFilm.remove(viewmodel.arrFilm.size() - 1);
                    filmsAdapter.notifyItemRemoved(viewmodel.arrFilm.size());
                    Toast.makeText(getActivity(), "HẾt data rùi !!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewmodel.isLoading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    binding.progressbar.setVisibility(View.VISIBLE);
                } else {
                    binding.progressbar.setVisibility(View.GONE);
                }
            }
        });

        binding.swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                binding.swiperefresh.setRefreshing(false);
                viewmodel.doLoadFilm();
            }
        });
    }

    private void initRecyclerView() {
        filmsAdapter = new FilmsAdapter((ArrayList<Film>) viewmodel.arrFilm);
        filmsAdapter.setCallback(new FilmCallback() {
            @Override
            public void onClickFilm(Film film) {

            }

            @Override
            public void onLongClick(Film film) {

            }

            @Override
            public void onClickFilmPos(int pos) {
                Toast.makeText(getActivity(), "Click film:" + viewmodel.arrFilm.get(pos), Toast.LENGTH_SHORT).show();
            }
        });
        binding.rvFilms.setHasFixedSize(true);
        binding.rvFilms.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        binding.rvFilms.setAdapter(filmsAdapter);
        binding.rvFilms.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager = (LinearLayoutManager) binding.rvFilms.getLayoutManager();
                if (!viewmodel.isloadMore && layoutManager.findLastVisibleItemPosition() == filmsAdapter.getItemCount() - 1) {
                    viewmodel.isloadMore = true;
                    viewmodel.arrFilm.add(null);
                    filmsAdapter.notifyItemInserted(viewmodel.arrFilm.size() - 1);
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
