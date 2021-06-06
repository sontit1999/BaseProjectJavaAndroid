package com.example.basejavaandroid.ui.mallclub.fragment;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basejavaandroid.R;
import com.example.basejavaandroid.base.BaseFragment;
import com.example.basejavaandroid.databinding.FragReviewBinding;

import org.jetbrains.annotations.NotNull;

public class ReviewFragment extends BaseFragment<FragReviewBinding, ReviewViewModel> {
    ReviewAdapter reviewAdapter;
    boolean isLoading = false;
    boolean isMyReview;

    public ReviewFragment(boolean isMyReview) {
        this.isMyReview = isMyReview;
    }

    @Override
    protected Class<ReviewViewModel> getViewModel() {
        return ReviewViewModel.class;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewmodel.isMyReview = isMyReview;
        if (isMyReview) {
            viewmodel.getMyReview("");
        } else {
            viewmodel.getReview("");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onViewCreated() {
        initRVreview();
        initEvent();
        viewmodel.actionState.observe(this, new Observer<ReviewViewModel.ActionReview>() {
            @Override
            public void onChanged(ReviewViewModel.ActionReview actionReview) {
                if (actionReview instanceof ReviewViewModel.ActionReview.ActionNotificationReviewUpdate) {
                    reviewAdapter.notifyItemChanged(((ReviewViewModel.ActionReview.ActionNotificationReviewUpdate) actionReview).pos);
                }
                if (actionReview instanceof ReviewViewModel.ActionReview.ActionNotificationDataChange) {
                    reviewAdapter.notifyDataSetChanged();
                }
                if (actionReview instanceof ReviewViewModel.ActionReview.ActionShowLoading) {
                    if (viewmodel.listReview != null && viewmodel.listReview.size() > 0) {
                        if (((ReviewViewModel.ActionReview.ActionShowLoading) actionReview).isShow) {
                            viewmodel.listReview.add(null);
                            reviewAdapter.notifyItemInserted(viewmodel.listReview.size() - 1);
                        } else {
                            viewmodel.listReview.remove(viewmodel.listReview.size() - 1);
                            reviewAdapter.notifyItemRemoved(viewmodel.listReview.size() - 1);
                        }
                    }

                }
            }
        });
    }

    private void initEvent() {
        binding.edtKeyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void afterTextChanged(Editable s) {
                if (isMyReview) {
                    viewmodel.getMyReview(s.toString());
                } else {
                    viewmodel.getReview(s.toString());
                }
            }
        });
    }

    private void initRVreview() {
        reviewAdapter = new ReviewAdapter(getContext());
        reviewAdapter.setList(viewmodel.listReview);
        binding.rvReview.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        binding.rvReview.setAdapter(reviewAdapter);
        reviewAdapter.setCallBack(new ReviewAdapter.ReviewCallBack() {
            @Override
            public void onclickItem(int pos) {
                Toast.makeText(getActivity(), "Xem detail comment!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClickLike(int pos) {
                viewmodel.onCLickLike(pos);
            }
        });
        binding.rvReview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (!viewmodel.isLoading && linearLayoutManager.findLastVisibleItemPosition() == reviewAdapter.getItemCount() - 1) {
                    viewmodel.isLoading = true;
                    viewmodel.getMoreReview();
                }
            }
        });
    }


    @Override
    protected void setBindingViewmodel() {
        binding.setViewmodel(viewmodel);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_review;
    }
}
