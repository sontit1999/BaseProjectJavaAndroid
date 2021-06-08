package com.example.basejavaandroid.ui.mallclub.addreview;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basejavaandroid.R;
import com.example.basejavaandroid.base.BaseActivity;
import com.example.basejavaandroid.databinding.ActivityAddReivewBinding;

import java.util.ArrayList;

public class AddReivewActivity extends BaseActivity<ActivityAddReivewBinding, AddReviewViewmodel> {
    ArrayList<Uri> uri = new ArrayList<>();
    HorizontalRecyclerView adapter;

    @Override
    protected void getData() {
        adapter = new HorizontalRecyclerView(uri);
        binding.rvPhoto.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        binding.rvPhoto.setAdapter(adapter);

    }

    @Override
    protected void initEvent() {

        binding.btnChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddReivewActivity.this, "click choose image", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(Intent.createChooser(intent, "Pictures: "), 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                if (data.getClipData() != null) {
                    int count = data.getClipData().getItemCount();
                    for (int i = 0; i < count; i++) {
                        uri.add(data.getClipData().getItemAt(i).getUri());
                    }
                    adapter.notifyDataSetChanged();
                }
            } else if (data.getData() != null) {
                String imagePath = data.getData().getPath();
            }
        }
    }

    @Override
    protected void setBindingViewmodel() {

    }

    @Override
    protected Class<AddReviewViewmodel> getViewModel() {
        return AddReviewViewmodel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_reivew;
    }
}