package com.example.basejavaandroid.ui.lifestyle.keepstate;

import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basejavaandroid.R;
import com.example.basejavaandroid.adapter.NumberAdapter;
import com.example.basejavaandroid.base.BaseActivity;
import com.example.basejavaandroid.databinding.ActivityKeepStateTabBinding;
import com.example.basejavaandroid.model.Review;
import com.example.basejavaandroid.ui.mallclub.fragment.ReviewAdapter;

import org.jetbrains.annotations.NotNull;

public class KeepStateTabActivity extends BaseActivity<ActivityKeepStateTabBinding, KeepStateViewmodel> implements NumberAdapter.numberCallBack {
    boolean isMyReview = false;
    ReviewAdapter reviewAdapter;

    @Override
    protected void getData() {
        viewmodel.actionState.observe(this, new Observer<KeepStateViewmodel.ActionReview>() {
            @Override
            public void onChanged(KeepStateViewmodel.ActionReview actionReview) {
                if (actionReview instanceof KeepStateViewmodel.ActionReview.ActionAddMyReview) {
                    reviewAdapter.notifyDataSetChanged();
                }
                if (actionReview instanceof KeepStateViewmodel.ActionReview.ActionAddMyReview) {
                    reviewAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    protected void initEvent() {
        binding.tvReview.setOnClickListener(v -> {
            isMyReview = false;
            binding.tvReview.setTextColor(Color.RED);
            binding.tvMyReview.setTextColor(Color.GRAY);
            reviewAdapter.setList(viewmodel.listReview);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    binding.rvReview.smoothScrollToPosition(viewmodel.currentPosReview);
                    Log.d("sondz", "scroll to pos" + viewmodel.currentPosReview + " in tab review");
                }
            }, 2000);

        });

        binding.tvMyReview.setOnClickListener(v -> {
            isMyReview = true;
            binding.tvMyReview.setTextColor(Color.RED);
            binding.tvReview.setTextColor(Color.GRAY);
            reviewAdapter.setList(viewmodel.listMyReview);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    binding.rvReview.smoothScrollToPosition(viewmodel.currentPosMyReview);
                    Log.d("sondz", "scroll to pos" + viewmodel.currentPosMyReview + " in tab Myreview");
                }
            }, 2000);

        });
        binding.rvReview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int position;
                    position = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                    if (isMyReview) {
                        Log.d("sondz", "current Pos myReview = " + position);
                        viewmodel.currentPosMyReview = position;
                    } else {
                        Log.d("sondz", "current Pos Review = " + position);
                        viewmodel.currentPosReview = position;
                    }
                }

            }
        });
    }

    @Override
    protected void setBindingViewmodel() {
        initReclerview();
    }

    private void initReclerview() {
        reviewAdapter = new ReviewAdapter(KeepStateTabActivity.this, viewmodel.listReview);
        binding.rvReview.setAdapter(reviewAdapter);
        reviewAdapter.setCallBack(new ReviewAdapter.ReviewCallBack() {
            @Override
            public void onclickItem(int pos) {
                if (isMyReview) {
                    Toast.makeText(KeepStateTabActivity.this, "Click " + viewmodel.listMyReview.get(pos).getNameTenant(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(KeepStateTabActivity.this, "Click " + viewmodel.listReview.get(pos).getNameTenant(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onClickLike(int pos) {
                Review review;
                if (isMyReview) {
                    review = viewmodel.listMyReview.get(pos);
                    review.setLike(!review.isLike());
                    viewmodel.listMyReview.set(pos, review);
                } else {
                    review = viewmodel.listReview.get(pos);
                    review.setLike(!review.isLike());
                    viewmodel.listReview.set(pos, review);
                }
                reviewAdapter.notifyItemChanged(pos);
            }
        });
    }

    @Override
    protected Class<KeepStateViewmodel> getViewModel() {
        return KeepStateViewmodel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_keep_state_tab;
    }

    @Override
    public void onClickNumber(int pos) {
        if (isMyReview) {
            Toast.makeText(this, "Myreview Tab Click number : " + viewmodel.listMyReview.get(pos).getContent(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Review Tab Click number : " + viewmodel.listReview.get(pos).getContent(), Toast.LENGTH_SHORT).show();
        }
    }
}