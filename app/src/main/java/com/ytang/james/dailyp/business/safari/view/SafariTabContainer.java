package com.ytang.james.dailyp.business.safari.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ytang.james.dailyp.R;
import com.ytang.james.dailyp.base.utils.ViewUtils;

/**
 * Created by James on 16/10/21.
 */
public class SafariTabContainer extends RelativeLayout {
  private TextView mText;
  private TextView mNum;
  private static final String DOT = "...";


  public SafariTabContainer(Context context) {
    super(context);
  }

  public SafariTabContainer(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public static SafariTabContainer newInstance(Context context) {
    return (SafariTabContainer) ViewUtils.newInstance(context, R.layout.safari_tab_container);
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    mText = (TextView) findViewById(R.id.tv_tab_safari);
    mNum = (TextView) findViewById(R.id.tv_tab_msg_safari);
  }


  public TextView getText() {
    return mText;
  }

  public void setMessageNum(int num) {
    if (num <= 0) {
      mNum.setVisibility(INVISIBLE);
    } else if (num < 100) {
      mNum.setVisibility(VISIBLE);
      mNum.setText(String.valueOf(num));
    } else {
      mNum.setVisibility(VISIBLE);
      mNum.setText(DOT);
    }
  }
}
