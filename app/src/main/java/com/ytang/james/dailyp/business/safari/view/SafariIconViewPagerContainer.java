package com.ytang.james.dailyp.business.safari.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ytang.james.dailyp.R;
import com.ytang.james.dailyp.base.adapter.WrapContentHeightViewPager;
import com.ytang.james.dailyp.base.mvp.BaseView;
import com.ytang.james.dailyp.base.utils.ViewUtils;

/**
 * Created by James on 16/10/19.
 */
public class SafariIconViewPagerContainer extends LinearLayout implements BaseView {

  private WrapContentHeightViewPager viewPager;
  private LinearLayout llDot;

  public SafariIconViewPagerContainer(Context context) {
    super(context);
  }

  public SafariIconViewPagerContainer(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public SafariIconViewPagerContainer(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  public static SafariIconViewPagerContainer newInstance(ViewGroup parent) {
    return (SafariIconViewPagerContainer) ViewUtils.newInstance(parent,
        R.layout.safari_icon_view_pager_container);
  }

  public static SafariIconViewPagerContainer newInstance(Context context) {
    return (SafariIconViewPagerContainer) ViewUtils.newInstance(context,
        R.layout.safari_icon_view_pager_container);
  }

  @Override
  public View getView() {
    return this;
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    initViews();
  }

  private void initViews() {
    viewPager = (WrapContentHeightViewPager) findViewById(R.id.view_pager);
    llDot = (LinearLayout) findViewById(R.id.ll_dot);
  }

  public WrapContentHeightViewPager getViewPager() {
    return viewPager;
  }

  public LinearLayout getLlDot() {
    return llDot;
  }
}
