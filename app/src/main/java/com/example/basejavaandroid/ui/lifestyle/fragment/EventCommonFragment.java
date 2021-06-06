package com.example.basejavaandroid.ui.lifestyle.fragment;

import android.graphics.Color;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basejavaandroid.Film;
import com.example.basejavaandroid.FilmCallback;
import com.example.basejavaandroid.R;
import com.example.basejavaandroid.adapter.FilmsAdapter;
import com.example.basejavaandroid.base.BaseFragment;
import com.example.basejavaandroid.databinding.FragEventCommonBinding;

import org.jetbrains.annotations.NotNull;

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
        viewmodel.arrFilm.observe(this, films -> {
            int currentSize = viewmodel.listFilm.size();
            viewmodel.listFilm.addAll(films);
            filmsAdapter.notifyItemRangeInserted(currentSize, films.size());
        });
    }

    private void initRecyclerView() {
        filmsAdapter = new FilmsAdapter(viewmodel.listFilm);
        filmsAdapter.setCallback(new FilmCallback() {
            @Override
            public void onClickFilm(Film film) {

            }

            @Override
            public void onLongClick(Film film) {

            }

            @Override
            public void onClickFilmPos(int pos) {
                Toast.makeText(getActivity(), "Click film:" + viewmodel.listFilm.get(pos), Toast.LENGTH_SHORT).show();
            }
        });
        binding.rvFilms.setHasFixedSize(true);
        binding.rvFilms.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        binding.rvFilms.setAdapter(filmsAdapter);
        binding.rvFilms.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                /*LinearLayoutManager layoutManager = (LinearLayoutManager) binding.rvFilms.getLayoutManager();
                if (layoutManager.findLastVisibleItemPosition() + 2 >= binding.rvFilms.getAdapter().getItemCount()) {
                    viewmodel.isLoading.setValue(true);
                    viewmodel.loadMoreFilm();
                }*/

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
