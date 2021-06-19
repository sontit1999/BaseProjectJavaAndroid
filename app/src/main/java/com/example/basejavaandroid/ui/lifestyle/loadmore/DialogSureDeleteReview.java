package com.example.basejavaandroid.ui.lifestyle.loadmore;

import android.app.Dialog;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.basejavaandroid.R;
import com.example.basejavaandroid.base.BaseDialogFragment;
import com.example.basejavaandroid.databinding.DialogDeleteReviewBinding;

public class DialogSureDeleteReview extends BaseDialogFragment<DialogDeleteReviewBinding> {
    @Override
    public int getLayoutID() {
        return R.layout.dialog_delete_review;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        dialog.setCancelable(false);
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
            //getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

    }

    @Override
    public void ViewCreated() {
        binding.btnCancer.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "Hủy", Toast.LENGTH_SHORT).show();
            dismiss();
        });
        binding.btnOK.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "OK", Toast.LENGTH_SHORT).show();
            dismiss();
        });
    }
}
