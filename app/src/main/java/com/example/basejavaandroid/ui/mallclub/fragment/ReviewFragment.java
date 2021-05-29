package com.example.basejavaandroid.ui.mallclub.fragment;

import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basejavaandroid.R;
import com.example.basejavaandroid.base.BaseFragment;
import com.example.basejavaandroid.databinding.FragReviewBinding;
import com.example.basejavaandroid.ui.mallclub.HomeFanClubActivity;

public class ReviewFragment extends BaseFragment<FragReviewBinding, ReviewViewModel> {
    ReviewAdapter reviewAdapter;
    HomeFanClubActivity activity;

    @Override
    protected Class<ReviewViewModel> getViewModel() {
        return ReviewViewModel.class;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onViewCreated() {
        activity = (HomeFanClubActivity) getActivity();
        initRVreview();
        viewmodel.arrReview.observe(this, reviews -> {
            reviewAdapter.setList(reviews);
        });
        viewmodel.arrMYReview.observe(this, reviews -> {
            reviewAdapter.setList(reviews);
        });
        viewmodel.getReview("");
        initEvent();
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

                if (activity.getTabReview() == HomeFanClubActivity.TabReview.REVIEW) {
                    viewmodel.getReview(s.toString());
                } else {
                    viewmodel.getMYReview(s.toString());
                }
            }
        });
    }

    private void initRVreview() {
        reviewAdapter = new ReviewAdapter(getContext());
        binding.rvReview.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        binding.rvReview.setAdapter(reviewAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getData(HomeFanClubActivity.TabReview tab) {
        if (tab == HomeFanClubActivity.TabReview.REVIEW) {
            viewmodel.getReview(binding.edtKeyword.getText().toString());
        } else {
            viewmodel.getMYReview(binding.edtKeyword.getText().toString());
        }
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
