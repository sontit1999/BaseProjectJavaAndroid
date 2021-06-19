package com.example.basejavaandroid.ui.lifestyle.loadmore;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.basejavaandroid.R;

public class DeleteReviewDialog extends Dialog {
    ActionDialog callback;
    TextView btnCancel;
    Button btnOK;
    ImageButton ibCLose;

    public DeleteReviewDialog(@NonNull Context context) {
        super(context);
        initDialog();
    }

    public DeleteReviewDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        initDialog();
    }

    protected DeleteReviewDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initDialog();
    }

    public ActionDialog getCallback() {
        return callback;
    }

    public void setCallback(ActionDialog callback) {
        this.callback = callback;
    }

    private void initDialog() {

        setContentView(R.layout.dialog_delete_review);
        setCancelable(false);
        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getWindow().setLayout(width, height);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        btnCancel = findViewById(R.id.btnCancer);
        btnOK = findViewById(R.id.btnOK);
        ibCLose = findViewById(R.id.ibClose);

        btnOK.setOnClickListener(v -> {
            if (callback != null) {
                callback.onClickOk();
            }
        });
        btnCancel.setOnClickListener(v -> {
            if (callback != null) {
                callback.onClickCancel();
            }
        });
        ibCLose.setOnClickListener(v -> dismiss());
    }

    public interface ActionDialog {
        void onClickCancel();

        void onClickOk();
    }
}
