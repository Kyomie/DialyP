package com.ytang.james.dailyp.base.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by James on 16/10/19.
 *
 * viewpager存在一个bug，即设置其height为wrap_content，还是会占满全屏。该类为解决此问题。取儿子中高度最高的高度为viewpager的高度。
 *
 * 当viewpager中存在gridview时，可能存在多行，该类始终只能测试单行的最高高度，故需配合使用CommonGridView｛@link CommonViewPager ｝。
 *
 */
public class WrapContentHeightViewPager extends ViewPager {

  public WrapContentHeightViewPager(Context context) {
    super(context);
  }

  public WrapContentHeightViewPager(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

    int height = 0;
    // 下面遍历所有child的高度
    for (int i = 0; i < getChildCount(); i++) {
      View child = getChildAt(i);
      child.measure(widthMeasureSpec,
          MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
      int h = child.getMeasuredHeight();
      if (h > height) // 采用最大的view的高度。
        height = h;
    }

    heightMeasureSpec = MeasureSpec.makeMeasureSpec(height,
        MeasureSpec.EXACTLY);

    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }

}
