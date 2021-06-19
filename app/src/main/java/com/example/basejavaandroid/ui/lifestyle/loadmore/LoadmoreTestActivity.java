package com.example.basejavaandroid.ui.lifestyle.loadmore;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.basejavaandroid.R;
import com.example.basejavaandroid.base.BaseActivity;
import com.example.basejavaandroid.databinding.ActivityLoadmoreBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class LoadmoreTestActivity extends BaseActivity<ActivityLoadmoreBinding, LoadMoreViewModel> {
    @Override
    protected void getData() {

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
}
