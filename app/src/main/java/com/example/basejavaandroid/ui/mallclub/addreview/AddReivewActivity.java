package com.example.basejavaandroid.ui.mallclub.addreview;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basejavaandroid.R;
import com.example.basejavaandroid.adapter.ImagePager;
import com.example.basejavaandroid.base.BaseActivity;
import com.example.basejavaandroid.databinding.ActivityAddReivewBinding;

import java.util.ArrayList;

public class AddReivewActivity extends BaseActivity<ActivityAddReivewBinding, AddReviewViewmodel> {
    ArrayList<String> listArr = new ArrayList<>();
    ArrayList<Uri> uri = new ArrayList<>();
    HorizontalRecyclerView adapter;
    ImagePager imagePager;
    @Override
    protected void getData() {

        adapter = new HorizontalRecyclerView(uri);
        binding.rvPhoto.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        binding.rvPhoto.setAdapter(adapter);

        listArr.add("https://i0.wp.com/1.bp.blogspot.com/-Se8YbE6CNPY/YMA1u5nAW1I/AAAAAAAEfn8/p3PilidrKtsr2_Y02Ws30LWYGSMsnGNQwCLcBGAsYHQ/s0/XIUREN-No.3112-Lin-Rui-Xi-MrCong.com-031.jpg?w=955&ssl=1");
        listArr.add("https://i2.wp.com/1.bp.blogspot.com/-Ywoj1onfE3c/YMS3opS3XPI/AAAAAAAEik0/_Ln1BcPgotMKCz3Mi7U4lrryqgWo8WFtgCLcBGAsYHQ/s0/XIUREN-No.3116-Wang-Yu-Chun-MrCong.com-054.jpg?w=955&ssl=1");
        listArr.add("https://i2.wp.com/1.bp.blogspot.com/-7Rz7i-FGJQw/YL4mwm9GyTI/AAAAAAAEdXA/g3p3VwzwmyUyJo18jVcY6ZJt8NlSf9MdQCLcBGAsYHQ/s0/XIUREN-No.3111-Mia-MrCong.com-093.jpg?w=955&ssl=1");
        listArr.add("https://i2.wp.com/1.bp.blogspot.com/-QuV3hEZ1TF4/YL4mczKi3xI/AAAAAAAEdRQ/wmhE2U4jQMMcWTz_7IYXo9wlIBBR2z0fwCLcBGAsYHQ/s0/XIUREN-No.3111-Mia-MrCong.com-001.jpg?w=955&ssl=1");
        listArr.add("https://i2.wp.com/1.bp.blogspot.com/-DHuSGtxbBgI/YLotm7NTahI/AAAAAAAEaJo/aK4ZbbG-ReEZ14O9jSetZFv_bdYsfGCywCLcBGAsYHQ/s0/XIUREN-No.3107-MrCong.com-034.jpg?w=955&ssl=1");
        listArr.add("https://i0.wp.com/1.bp.blogspot.com/-wcWDqQR6bG0/YLotImih-eI/AAAAAAAEaGk/eWV--StXLNM5RG8r52vW473n1YEBgtwVgCLcBGAsYHQ/s0/XIUREN-No.3106-MrCong.com-043.jpg?w=955&ssl=1");
        listArr.add("https://i2.wp.com/1.bp.blogspot.com/-BOXd2kDduE8/YLop1RUto3I/AAAAAAAEZ5Q/bpL3fDgxyQoK0G-w2LfSyUJWURD3IjITQCLcBGAsYHQ/s0/XIUREN-No.3104-Meng-Xin-Yue-MrCong.com-057.jpg?w=955&ssl=1");
        listArr.add("https://i2.wp.com/1.bp.blogspot.com/-pHzADxU9wEY/YLjfCamSDsI/AAAAAAAEZIY/1ftStBzh5R04FGvkn-G8o8HKkEdIasi9ACLcBGAsYHQ/s0/XIUREN-No.3100-Allen-MrCong.com-031.jpg?w=955&ssl=1");
        listArr.add("https://i0.wp.com/1.bp.blogspot.com/-86F1ny2_UFk/YLjemBqQBiI/AAAAAAAEZF4/KLWHf48okSUdMbzSkCl-gwUjzZTL25rhwCLcBGAsYHQ/s0/XIUREN-No.3099-Fish-MrCong.com-071.jpg?w=955&ssl=1");

        imagePager = new ImagePager(listArr, AddReivewActivity.this);
        binding.viewPager.setAdapter(imagePager);

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
                        Log.d("sondz", data.getClipData().getItemAt(i).getUri().toString());
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