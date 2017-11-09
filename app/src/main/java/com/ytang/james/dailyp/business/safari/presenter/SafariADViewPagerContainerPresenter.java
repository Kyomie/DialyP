package com.ytang.james.dailyp.business.safari.presenter;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.ytang.james.dailyp.R;
import com.ytang.james.dailyp.base.mvp.BasePresenter;
import com.ytang.james.dailyp.business.safari.adapter.ADViewPagerAdapter;
import com.ytang.james.dailyp.business.safari.mvp.model.ADHttpModel;
import com.ytang.james.dailyp.business.safari.view.SafariADViewPagerContainer;

/**
 * Created by James on 16/10/20.
 */
public class SafariADViewPagerContainerPresenter
    extends BasePresenter<SafariADViewPagerContainer, ADHttpModel> {

  private SafariADViewPagerContainer mView;
  private int curIndex = 0;

  @Override
  public void bind(SafariADViewPagerContainer view, ADHttpModel model) {

    if (model == null || model.getData() == null || model.getData().size() == 0) {
      view.setVisibility(View.GONE);
      return;
    }

    mView = view;

    ADViewPagerAdapter adapter = new ADViewPagerAdapter();
    adapter.setData(model.getData());
    view.getViewPager().setAdapter(adapter);
    view.getViewPager().setOnPageChangeListener(new ADViewPagerChangeListener());

    setOvalLayout(model.getData().size());

    if (model.getData().size() == 1) {
      view.getLlDot().setVisibility(View.GONE);
    } else {
      view.getLlDot().setVisibility(View.VISIBLE);
    }

  }

  /**
   * 设置圆点
   */
  public void setOvalLayout(int pageNumber) {
    LayoutInflater inflater = LayoutInflater.from(mView.getViewPager().getContext());
    for (int i = 0; i < pageNumber; i++) {
      mView.getLlDot().addView(inflater.inflate(R.layout.dot, null));
    }
    // 默认显示第一页
    mView.getLlDot().getChildAt(0).findViewById(R.id.v_dot)
        .setBackgroundResource(R.drawable.dot_select);
  }

  /**
   * ViewPager的监听器
   * 当ViewPager中页面的状态发生改变时调用
   */
  private class ADViewPagerChangeListener implements ViewPager.OnPageChangeListener {
    private boolean isScrolled;

    @Override
    public void onPageScrollStateChanged(int arg0) {
      switch (arg0) {
        case 1:// 手势滑动
          isScrolled = false;
          break;
        case 2:// 界面切换
          isScrolled = true;
          break;
        case 0:// 滑动结束
          if (mView.getViewPager().getCurrentItem() == mView.getViewPager().getAdapter().getCount() - 1
              && !isScrolled) {// 当前为最后一张，此时从右向左滑，则切换到第一张
            mView.getViewPager().setCurrentItem(0, false);
          } else if (mView.getViewPager().getCurrentItem() == 0 && !isScrolled) {// 当前为第一张，此时从左向右滑，则切换到最后一张
            mView.getViewPager().setCurrentItem(mView.getViewPager().getAdapter().getCount() - 1,
                false);
          }
          break;
      }
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {}

    @Override
    public void onPageSelected(int position) {
      // 取消圆点选中
      mView.getLlDot().getChildAt(curIndex)
          .findViewById(R.id.v_dot)
          .setBackgroundResource(R.drawable.dot_normal);
      // 圆点选中
      mView.getLlDot().getChildAt(position)
          .findViewById(R.id.v_dot)
          .setBackgroundResource(R.drawable.dot_select);
      curIndex = position;
    }
  }


}
