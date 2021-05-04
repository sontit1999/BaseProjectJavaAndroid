package com.example.basejavaandroid;

import com.example.basejavaandroid.base.BaseFragment;
import com.example.basejavaandroid.databinding.FragDemoBinding;

public class FragDemo extends BaseFragment<FragDemoBinding,DemoViewmodel> {
    @Override
    protected Class<DemoViewmodel> getViewModel() {
        return DemoViewmodel.class;
    }

    @Override
    protected void onViewCreated() {

    }

    @Override
    protected void setBindingViewmodel() {
        binding.setViewmodel(viewmodel);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_demo;
    }
}
