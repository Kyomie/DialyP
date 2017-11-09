package com.ytang.james.dailyp.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * Created by James on 16/10/19.
 * 
 * viewpager存在一个bug，即设置其height为wrap_content，还是会占满全屏。WrapContentHeightViewPager｛
 *
 * @link WrapContentHeightViewPager ｝为解决此问题,取儿子中高度最高的高度为viewpager的高度。
 *
 *       当viewpager中存在gridview时，可能存在多行，此类始终只能测试单行的最高高度，故需使用该类配合。
 * 
 */
public class CommonGridView extends GridView {

  public CommonGridView(Context context) {
    super(context);
  }

  public CommonGridView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  public CommonGridView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public boolean onTouchEvent(MotionEvent ev) {
    return super.onTouchEvent(ev);
  }

  @Override
  public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int expandSpec = MeasureSpec.makeMeasureSpec(
        Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
    super.onMeasure(widthMeasureSpec, expandSpec);
  }
}
