package com.example.basejavaandroid.customview;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import com.bumptech.glide.Glide;
import com.example.basejavaandroid.databinding.CongratulationReviewDialogBinding;

interface OnClickConfirmListener {
    void onClick();
}
public class CongratulationDialog extends Dialog {

    private CongratulationReviewDialogBinding binding;
    private OnClickConfirmListener onClickConfirmListener;

    public CongratulationDialog(@NonNull Context context) {
        super(context);
        init();
    }

    public CongratulationDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    protected CongratulationDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }


    public void setContent(@StringRes int title, @StringRes int content, @DrawableRes int image) {
        if (binding == null) {
            return;
        }
        binding.tvTitle.setText(title);
        binding.tvContent.setText(content);
        Glide.with(binding.img).load(image).into(binding.img);
    }


    public void setContent(@StringRes int title, CharSequence content, @DrawableRes int image) {
        if (binding == null) {
            return;
        }
        binding.tvTitle.setText(title);
        binding.tvContent.setText(content);
        Glide.with(binding.img).load(image).into(binding.img);
    }

    public void setContent(String title, CharSequence content, String imageUrl) {
        if (binding == null) {
            return;
        }
        binding.tvTitle.setText(title);
        binding.tvContent.setText(content);
       // Glide.with(binding.img).load(imageUrl).into(binding.img);
    }

    public void setContent(@StringRes int title, CharSequence content, byte[] bytes) {
        if (binding == null) {
            return;
        }
        binding.tvTitle.setText(title);
        binding.tvContent.setText(content);
        Glide.with(binding.img).load(bytes).into(binding.img);
    }

    public void setConfirmListener(@StringRes int confirmText, OnClickConfirmListener onClickConfirmListener) {
        binding.btnOKRouteDialog.setText(confirmText);
        this.onClickConfirmListener = onClickConfirmListener;
    }

    private void init() {
        binding = CongratulationReviewDialogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        Window dialogWindow = getWindow();
        if (dialogWindow != null) {
            dialogWindow.setBackgroundDrawable(null);
            dialogWindow.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        }

        binding.btnDialogClose.setOnClickListener(v -> {
            dismiss();
        });

        binding.btnOKRouteDialog.setOnClickListener(v -> {
            dismiss();
            if (onClickConfirmListener != null) {
                onClickConfirmListener.onClick();
            }
        });
    }

}
