package com.example.basejavaandroid.ui.mrcong;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basejavaandroid.R;
import com.example.basejavaandroid.adapter.IdolAdapter;
import com.example.basejavaandroid.base.BaseActivity;
import com.example.basejavaandroid.databinding.ActivityMrCongBinding;

import org.jetbrains.annotations.NotNull;

public class MrCongActivity extends BaseActivity<ActivityMrCongBinding,MrCongViewModel> {
    IdolAdapter idolAdapter;
    @Override
    protected void getData() {
        viewmodel.actionState.observe(this, actionIdol -> {
            if(actionIdol instanceof MrCongViewModel.ActionIdol.ActionDataChange){
                idolAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void initEvent() {

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
                 Toast.makeText(MrCongActivity.this, viewmodel.arrIdol.get(position).getLinkIdol(), Toast.LENGTH_SHORT).show();
             }
         });
         binding.rvIdol.setAdapter(idolAdapter);
         binding.rvIdol.addOnScrollListener(new RecyclerView.OnScrollListener() {
             @Override
             public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                 super.onScrollStateChanged(recyclerView, newState);
                 viewmodel.isLoading = true;
                 viewmodel.getMoreIdol();
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
}