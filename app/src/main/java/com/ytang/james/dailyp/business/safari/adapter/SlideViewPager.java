package com.ytang.james.dailyp.business.safari.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by James on 16/10/21.
 *
 * 提供禁止滑动的viewpager
 */
public class SlideViewPager extends ViewPager{

    private boolean mIsScrollEnabled = true;

    public SlideViewPager(Context context) {
        super(context);
    }

    public SlideViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public boolean isScrollEnabled() {
        return mIsScrollEnabled;
    }

    public void setScrollEnabled(boolean isEnabled) {
        this.mIsScrollEnabled = isEnabled;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!mIsScrollEnabled) {
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!mIsScrollEnabled) {
            return false;
        }
        return super.onTouchEvent(ev);
    }
}
