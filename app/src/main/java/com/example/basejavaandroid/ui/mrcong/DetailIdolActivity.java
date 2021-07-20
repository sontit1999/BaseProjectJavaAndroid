package com.example.basejavaandroid.ui.mrcong;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basejavaandroid.R;
import com.example.basejavaandroid.base.BaseActivity;
import com.example.basejavaandroid.customview.ImageReviewDialog;
import com.example.basejavaandroid.databinding.ActivityDetailIdolBinding;

import org.jetbrains.annotations.NotNull;

public class DetailIdolActivity extends BaseActivity<ActivityDetailIdolBinding, DetailIdolViewmodel> {
    public static final String PARAM_LINK_IDOL = "PARAM_LINK_IDOL";
    PhotoIdolAdapter photoIdolAdapter;
    ImageReviewDialog imageIdolDialog;

    @Override
    protected void getData() {
        handleGetphotoIdol(getIntent());
        viewmodel.actionState.observe(this, new Observer<DetailIdolViewmodel.ActionDetailIdol>() {
            @Override
            public void onChanged(DetailIdolViewmodel.ActionDetailIdol actionDetailIdol) {
                if (actionDetailIdol instanceof DetailIdolViewmodel.ActionDetailIdol.ActionDataChange) {
                    photoIdolAdapter.notifyDataSetChanged();
                    binding.pbloading.setVisibility(View.GONE);
                }
                if (actionDetailIdol instanceof DetailIdolViewmodel.ActionDetailIdol.ActionLoadMoreComplete) {
                    photoIdolAdapter.notifyItemRangeInserted(viewmodel.arrLinkPhoto.size() - ((DetailIdolViewmodel.ActionDetailIdol.ActionLoadMoreComplete) actionDetailIdol).count, viewmodel.arrLinkPhoto.size() - 1);
                    binding.pbloading.setVisibility(View.GONE);
                }
                if (actionDetailIdol instanceof DetailIdolViewmodel.ActionDetailIdol.ActionAllDataLoaded) {
                    Toast.makeText(DetailIdolActivity.this, "Hết ảnh rùi nha!", Toast.LENGTH_SHORT).show();
                    binding.pbloading.setVisibility(View.GONE);
                }
            }
        });
    }

    private void handleGetphotoIdol(Intent intent) {
        if (intent == null) return;
        viewmodel.init(intent.getStringExtra(PARAM_LINK_IDOL));
        Log.d("link", intent.getStringExtra(PARAM_LINK_IDOL));
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void setBindingViewmodel() {
        initRvIdol();
    }

    private void initRvIdol() {
        binding.rvphoto.setHasFixedSize(true);
        binding.rvphoto.setLayoutManager(new LinearLayoutManager(DetailIdolActivity.this, RecyclerView.VERTICAL, false));
        photoIdolAdapter = new PhotoIdolAdapter(this, viewmodel.arrLinkPhoto);
        photoIdolAdapter.setCallback(new PhotoIdolAdapter.IdolCallback() {
            @Override
            public void itemClicked(int position) {
                if (imageIdolDialog != null) {
                    imageIdolDialog = null;
                }
                imageIdolDialog = new ImageReviewDialog(viewmodel.arrLinkPhoto, position);
                imageIdolDialog.show(getSupportFragmentManager(), imageIdolDialog.getTag());
            }
        });
        binding.rvphoto.setAdapter(photoIdolAdapter);
        binding.rvphoto.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (!viewmodel.isLoading && layoutManager.findLastVisibleItemPosition() + 3 >= photoIdolAdapter.getItemCount()) {
                    super.onScrollStateChanged(recyclerView, newState);
                    viewmodel.isLoading = true;
                    binding.pbloading.setVisibility(View.VISIBLE);
                    viewmodel.getMorePhotoIdol();
                }
            }
        });
    }

    @Override
    protected Class<DetailIdolViewmodel> getViewModel() {
        return DetailIdolViewmodel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail_idol;
    }
}