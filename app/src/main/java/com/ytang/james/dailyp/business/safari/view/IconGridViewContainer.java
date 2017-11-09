package com.ytang.james.dailyp.business.safari.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ytang.james.dailyp.R;
import com.ytang.james.dailyp.base.mvp.BaseView;
import com.ytang.james.dailyp.base.utils.ViewUtils;
import com.ytang.james.dailyp.common.view.CommonGridView;

/**
 * Created by James on 16/10/18.
 */
public class IconGridViewContainer extends LinearLayout implements BaseView {

  private CommonGridView gridView;

  public IconGridViewContainer(Context ctx) {
    super(ctx);
  }

  public IconGridViewContainer(Context ctx, AttributeSet attrs) {
    super(ctx, attrs);
  }

  public IconGridViewContainer(Context ctx, AttributeSet attrs, int defStyleAttr) {
    super(ctx, attrs, defStyleAttr);
  }

  public static IconGridViewContainer newInstance(ViewGroup parent) {
    return (IconGridViewContainer) ViewUtils.newInstance(parent, R.layout.icon_grid_view_container);
  }

  public static IconGridViewContainer newInstance(Context ctx) {
    return (IconGridViewContainer) ViewUtils.newInstance(ctx, R.layout.icon_grid_view_container);
  }

  @Override
  public View getView() {
    return this;
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    ininViews();
  }

  private void ininViews() {
    gridView = (CommonGridView) findViewById(R.id.grid_view);
  }

  public CommonGridView getGridView() {
    return gridView;
  }
}
