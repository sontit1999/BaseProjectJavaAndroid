package com.example.basejavaandroid.ui.lifestyle.loadmore;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basejavaandroid.R;
import com.example.basejavaandroid.adapter.NumberAdapter;
import com.example.basejavaandroid.base.BaseActivity;
import com.example.basejavaandroid.databinding.ActivityLoadmoreBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.jetbrains.annotations.NotNull;

public class LoadmoreTestActivity extends BaseActivity<ActivityLoadmoreBinding, LoadMoreViewModel> implements NumberAdapter.numberCallBack {
    NumberAdapter numberAdapter;

    @Override
    protected void getData() {
        numberAdapter = new NumberAdapter(this, viewmodel.mListNumber);
        binding.rvNumber.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        binding.rvNumber.setAdapter(numberAdapter);

        binding.rvNumber.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull @NotNull RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastVisible = layoutManager.findLastVisibleItemPosition();
                if (!viewmodel.isLoadMore && lastVisible == numberAdapter.getItemCount() - 1) {
                    viewmodel.isLoadMore = true;
                    viewmodel.mListNumber.add(null);
                    numberAdapter.notifyItemInserted(viewmodel.mListNumber.size() - 1);
                    viewmodel.getMoreNumber();
                }
            }
        });

        viewmodel.actionState.observe(this, new Observer<LoadMoreViewModel.ActionLoadMore>() {
            @Override
            public void onChanged(LoadMoreViewModel.ActionLoadMore actionLoadMore) {
                if (actionLoadMore instanceof LoadMoreViewModel.ActionLoadMore.DataChange) {
                    numberAdapter.notifyDataSetChanged();
                }
                if (actionLoadMore instanceof LoadMoreViewModel.ActionLoadMore.DataLoadmoreSuccess) {
                    viewmodel.mListNumber.remove(viewmodel.mListNumber.size() - 1);
                    numberAdapter.notifyItemRemoved(viewmodel.mListNumber.size());
                }
                if (actionLoadMore instanceof LoadMoreViewModel.ActionLoadMore.DataInsert) {
                    numberAdapter.notifyItemRangeInserted(viewmodel.mListNumber.size() - ((LoadMoreViewModel.ActionLoadMore.DataInsert) actionLoadMore).count, viewmodel.mListNumber.size() - 1);
                }
            }
        });

    }

    @Override
    protected void initEvent() {
        binding.button.setOnClickListener(v -> {
            DeleteReviewDialog deleteReviewDialog = new DeleteReviewDialog(this);
            deleteReviewDialog.setCallback(new DeleteReviewDialog.ActionDialog() {
                @Override
                public void onClickCancel() {
                    Toast.makeText(LoadmoreTestActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
                    deleteReviewDialog.dismiss();
                }

                @Override
                public void onClickOk() {
                    Toast.makeText(LoadmoreTestActivity.this, "OK", Toast.LENGTH_SHORT).show();
                    deleteReviewDialog.dismiss();
                }
            });
            deleteReviewDialog.show();

        });
        binding.buttonSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(LoadmoreTestActivity.this);
                bottomSheetDialog.setContentView(R.layout.action_review_bottomsheet_dialog);

                LinearLayout btnDelete = bottomSheetDialog.findViewById(R.id.layoutDelete);
                LinearLayout btnHidden = bottomSheetDialog.findViewById(R.id.layoutHidden);
                LinearLayout btnEdit = bottomSheetDialog.findViewById(R.id.layoutEdit);

                btnDelete.setOnClickListener(v1 -> {
                    bottomSheetDialog.dismiss();
                    Toast.makeText(LoadmoreTestActivity.this, "Delete review!", Toast.LENGTH_SHORT).show();
                });

                btnHidden.setOnClickListener(v13 -> {
                    bottomSheetDialog.dismiss();
                    Toast.makeText(LoadmoreTestActivity.this, "Hidden review", Toast.LENGTH_SHORT).show();
                });
                btnEdit.setOnClickListener(v12 -> {
                    bottomSheetDialog.dismiss();
                    Toast.makeText(LoadmoreTestActivity.this, "Edit reivew", Toast.LENGTH_SHORT).show();
                });

                bottomSheetDialog.show();
            }
        });
    }

    @Override
    protected void setBindingViewmodel() {

    }

    @Override
    protected Class<LoadMoreViewModel> getViewModel() {
        return LoadMoreViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_loadmore;
    }

    @Override
    public void onClickNumber(int pos) {
        Toast.makeText(this, "CLick " + viewmodel.mListNumber.get(pos).getNumber() + "", Toast.LENGTH_SHORT).show();
    }
}
