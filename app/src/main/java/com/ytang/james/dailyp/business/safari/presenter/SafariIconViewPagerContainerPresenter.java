package com.ytang.james.dailyp.business.safari.presenter;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.ytang.james.dailyp.R;
import com.ytang.james.dailyp.base.mvp.BasePresenter;
import com.ytang.james.dailyp.business.safari.adapter.IconViewPagerAdapter;
import com.ytang.james.dailyp.business.safari.mvp.model.IconHttpModel;
import com.ytang.james.dailyp.business.safari.mvp.model.IconModel;
import com.ytang.james.dailyp.business.safari.view.SafariIconViewPagerContainer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James on 16/10/19.
 */
public class SafariIconViewPagerContainerPresenter
    extends BasePresenter<SafariIconViewPagerContainer, IconHttpModel> {

  private int curIndex = 0;

  @Override
  public void bind(SafariIconViewPagerContainer view, IconHttpModel model) {
    if (model == null || model.getData() == null || model.getData().size() == 0) {
      return;
    }

    int pageNumber = 1;
    // 根据button的个数来判断需要几页展示，小于等于10就用一页展示。
    if (model.getData().size() > 10) {
      pageNumber = model.getData().size() / 10 + (model.getData().size() % 10 > 0 ? 1 : 0);
    }

    List<IconHttpModel> pagerList = new ArrayList<>();
    for (int i = 0; i < pageNumber; i++) {
      IconHttpModel iconHttpModel = new IconHttpModel();
      List<IconModel> tmp = new ArrayList<>();
      for (int j = 0; j < model.getData().size(); j++) {
        if (j / 10 == i) {
          IconModel iconModel = new IconModel();
          iconModel.setId(model.getData().get(j).getId());
          iconModel.setResId(model.getData().get(j).getResId());
          iconModel.setName(model.getData().get(j).getName());
          tmp.add(iconModel);
        }

      }
      iconHttpModel.setData(tmp);
      pagerList.add(iconHttpModel);

    }

    setOvalLayout(view.getViewPager(), view.getLlDot(), pageNumber);

    if (pagerList.size() == 1) {
      view.getLlDot().setVisibility(View.GONE);
    } else {
      view.getLlDot().setVisibility(View.VISIBLE);
    }

    IconViewPagerAdapter adapter = new IconViewPagerAdapter();
    adapter.setData(pagerList);
    view.getViewPager().setAdapter(adapter);

  }

  /**
   * 设置圆点
   */
  public void setOvalLayout(ViewPager mViewPager, final LinearLayout mLlDot, int pageNumber) {
    LayoutInflater inflater = LayoutInflater.from(mViewPager.getContext());
    mLlDot.removeAllViews();
    for (int i = 0; i < pageNumber; i++) {
      mLlDot.addView(inflater.inflate(R.layout.dot, null));
    }
    // 默认显示第一页
    mLlDot.getChildAt(0).findViewById(R.id.v_dot)
        .setBackgroundResource(R.drawable.dot_select);
    mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      public void onPageSelected(int position) {
        // 取消圆点选中
        mLlDot.getChildAt(curIndex)
            .findViewById(R.id.v_dot)
            .setBackgroundResource(R.drawable.dot_normal);
        // 圆点选中
        mLlDot.getChildAt(position)
            .findViewById(R.id.v_dot)
            .setBackgroundResource(R.drawable.dot_select);
        curIndex = position;
      }

      public void onPageScrolled(int arg0, float arg1, int arg2) {}

      public void onPageScrollStateChanged(int arg0) {}
    });
  }

}
