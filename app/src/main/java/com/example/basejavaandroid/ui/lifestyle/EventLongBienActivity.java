package com.example.basejavaandroid.ui.lifestyle;

import com.example.basejavaandroid.R;
import com.example.basejavaandroid.base.BaseActivity;
import com.example.basejavaandroid.databinding.ActivityEventLongbienBinding;
import com.example.basejavaandroid.ui.lifestyle.fragment.EventCommonFragment;
import com.example.basejavaandroid.ui.lifestyle.fragment.ViewPagerAdapter;

public class EventLongBienActivity extends BaseActivity<ActivityEventLongbienBinding, EventLongBienViewModel> {
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void getData() {
        setupViewPagerAndTablayout();
    }

    private void setupViewPagerAndTablayout() {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new EventCommonFragment(EventCommonFragment.TYPE_COMMON), "Sự kiện chung");
        viewPagerAdapter.addFragment(new EventCommonFragment(EventCommonFragment.TYPE_BOOKMARK), "Sự kiện đã lưu");
        binding.viewpager.setAdapter(viewPagerAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewpager);
    }

    @Override
    protected void initEvent() {

    }

    private void initRecyclerView() {

    }

    @Override
    protected void setBindingViewmodel() {

    }

    @Override
    protected Class<EventLongBienViewModel> getViewModel() {
        return EventLongBienViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_event_longbien;
    }
}
