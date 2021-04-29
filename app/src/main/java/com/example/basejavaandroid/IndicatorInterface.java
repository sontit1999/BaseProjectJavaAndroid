package com.example.basejavaandroid;

import androidx.viewpager.widget.ViewPager;

public interface IndicatorInterface {
    void setViewPager(ViewPager viewPager) throws PagesLessException;

    void setAnimateDuration(long duration);

    /**
     *
     * @param radius: radius in pixel
     */
    void setRadiusSelected(int radius);

    /**
     *
     * @param radius: radius in pixel
     */
    void setRadiusUnselected(int radius);

    /**
     *
     * @param distance: distance in pixel
     */
    void setDistanceDot(int distance);
}
