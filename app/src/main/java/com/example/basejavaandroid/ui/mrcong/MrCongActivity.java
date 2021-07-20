package com.example.basejavaandroid.ui.mrcong;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basejavaandroid.R;
import com.example.basejavaandroid.adapter.IdolAdapter;
import com.example.basejavaandroid.base.BaseActivity;
import com.example.basejavaandroid.databinding.ActivityMrCongBinding;

import org.jetbrains.annotations.NotNull;

public class MrCongActivity extends BaseActivity<ActivityMrCongBinding, MrCongViewModel> {
    IdolAdapter idolAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        permisseion();
    }

    @Override
    protected void getData() {
        viewmodel.actionState.observe(this, actionIdol -> {
            if (actionIdol instanceof MrCongViewModel.ActionIdol.ActionDataChange) {
                idolAdapter.notifyDataSetChanged();
                binding.pbloading.setVisibility(View.GONE);
            }
            if (actionIdol instanceof MrCongViewModel.ActionIdol.ActionAllDataLoaded) {
                Toast.makeText(this, "Hết data r nha ^^", Toast.LENGTH_SHORT).show();
                binding.pbloading.setVisibility(View.GONE);
            }
            if (actionIdol instanceof MrCongViewModel.ActionIdol.ActionLoadMoreComplete) {
                idolAdapter.notifyItemRangeInserted(viewmodel.arrIdol.size() - ((MrCongViewModel.ActionIdol.ActionLoadMoreComplete) actionIdol).count, viewmodel.arrIdol.size() - 1);
                binding.pbloading.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void initEvent() {
        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String page = binding.edtPage.getText().toString().trim();
                if (TextUtils.isEmpty(page)) {
                    Toast.makeText(MrCongActivity.this, "Nhập trang muốn xem !!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                viewmodel.arrIdol.clear();
                idolAdapter.notifyDataSetChanged();
                binding.pbloading.setVisibility(View.VISIBLE);
                viewmodel.getIdolSpecificPage(Integer.parseInt(page));
            }
        });
    }

    @Override
    protected void setBindingViewmodel() {
         initRvIdol();
    }

    private void initRvIdol() {
         binding.rvIdol.setHasFixedSize(true);
         binding.rvIdol.setLayoutManager(new GridLayoutManager(MrCongActivity.this,2));
         idolAdapter = new IdolAdapter(this,viewmodel.arrIdol);
         idolAdapter.setCallback(new IdolAdapter.IdolCallback() {
             @Override
             public void itemClicked(int position) {
                 Intent intent = new Intent(MrCongActivity.this, DetailIdolActivity.class);
                 intent.putExtra(DetailIdolActivity.PARAM_LINK_IDOL, viewmodel.arrIdol.get(position).getLinkIdol());
                 startActivity(intent);
             }
         });
         binding.rvIdol.setAdapter(idolAdapter);
         binding.rvIdol.addOnScrollListener(new RecyclerView.OnScrollListener() {
             @Override
             public void onScrolled(@NonNull @NotNull RecyclerView recyclerView, int dx, int dy) {
                 super.onScrolled(recyclerView, dx, dy);
                 if (!viewmodel.isLoading && !recyclerView.canScrollVertically(1)) {
                     viewmodel.isLoading = true;
                     binding.pbloading.setVisibility(View.VISIBLE);
                     viewmodel.getMoreIdol();
                 }
             }
         });
    }

    @Override
    protected Class<MrCongViewModel> getViewModel() {
        return MrCongViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mr_cong;
    }

    public void permisseion() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED) {

        }
        // Else ask for permission
        else {
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
    }
}