package com.ytang.james.dailyp.business.safari.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ytang.james.dailyp.R;
import com.ytang.james.dailyp.base.mvp.BaseView;
import com.ytang.james.dailyp.base.utils.ViewUtils;
import com.ytang.james.dailyp.business.safari.adapter.ADViewPagerAdapter;
import com.ytang.james.dailyp.business.safari.mvp.model.ADHttpModel;
import com.ytang.james.dailyp.common.view.LoopViewPager;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by James on 16/10/20.
 *
 * 广告资源位轮播控件
 */
public class SafariADViewPagerContainer extends LinearLayout implements BaseView {

  private WeakReferenceHandler mHandler;
  private static final int UPDATE_MSG = 0;
  private static final int UPDATE_TIME = 3000;

  private LoopViewPager viewPager;
  private LinearLayout llDot;

  private Subscription mSubscription;
  private boolean autoLoop = false;
  // 轮播间隔
  private int delay = UPDATE_TIME;

  public void setDelay(int delay) {
    this.delay = delay;
  }

  private ViewPager.OnPageChangeListener mOnPageChangeListener =
      new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
          if (llDot.getVisibility() == VISIBLE) {
            for (int i = 0; i < viewPager.getAdapter().getCount(); i++) {
              if (i == position) {
                // 圆点选中
                llDot.getChildAt(position)
                    .findViewById(R.id.v_dot)
                    .setBackgroundResource(R.drawable.dot_select);
              } else {
                // 取消圆点选中
                llDot.getChildAt(getPrePosition(position))
                    .findViewById(R.id.v_dot)
                    .setBackgroundResource(R.drawable.dot_normal);
                llDot.getChildAt(getNextPosition(position)).findViewById(R.id.v_dot)
                    .setBackgroundResource(R.drawable.dot_normal);
              }
            }
          }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
          cancelLoop();
          if (state == ViewPager.SCROLL_STATE_IDLE) {
            autoLoop();
          }
        }
      };


  public SafariADViewPagerContainer(Context context) {
    super(context);
  }

  public SafariADViewPagerContainer(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public SafariADViewPagerContainer(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  public static SafariADViewPagerContainer newInstance(ViewGroup parent) {
    return (SafariADViewPagerContainer) ViewUtils.newInstance(parent,
        R.layout.safari_ad_view_pager_container);
  }

  public static SafariADViewPagerContainer newInstance(Context context) {
    return (SafariADViewPagerContainer) ViewUtils.newInstance(context,
        R.layout.safari_ad_view_pager_container);
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
    viewPager = (LoopViewPager) findViewById(R.id.view_pager);
    llDot = (LinearLayout) findViewById(R.id.ll_dot);
    mHandler = new WeakReferenceHandler(this);
  }

  public LoopViewPager getViewPager() {
    return viewPager;
  }

  public LinearLayout getLlDot() {
    return llDot;
  }

  public void setData(ADHttpModel model) {
    if (model == null || model.getData() == null || model.getData().size() == 0) {
      return;
    }

    if (model.getData().size() == 1) {
      llDot.setVisibility(GONE);
    } else {
      llDot.setVisibility(VISIBLE);
    }

    ADViewPagerAdapter adapter = new ADViewPagerAdapter();
    adapter.clearData();
    adapter.setData(model.getData());
    viewPager.setAdapter(adapter);
    viewPager.setOnPageChangeListener(mOnPageChangeListener);

    setOvalLayout(model.getData().size());

    // RxJava方式轮播
    cancelSubscription();
    startLoop();

    /*
     * if (mHandler != null) {
     * cancelLoop();
     * }
     * autoLoop();
     */

  }

  /**
   * 设置圆点
   */
  private void setOvalLayout(int pageNumber) {
    LayoutInflater inflater = LayoutInflater.from(viewPager.getContext());
    llDot.removeAllViews();
    for (int i = 0; i < pageNumber; i++) {
      llDot.addView(inflater.inflate(R.layout.dot, null));
    }
    // 默认显示第一页
    llDot.getChildAt(0).findViewById(R.id.v_dot)
        .setBackgroundResource(R.drawable.dot_select);
  }

  private int getPrePosition(int curPos) {
    return --curPos < 0 ? (viewPager.getAdapter().getCount() - 1) : curPos;
  }

  private int getNextPosition(int curPos) {
    return ++curPos == viewPager.getAdapter().getCount() ? 0 : curPos;
  }

  private void moveToNext() {
    int pos = viewPager.getCurrentItem();
    viewPager.setCurrentItem(++pos);
    autoLoop();
  }

  public void autoLoop() {
    Message message = mHandler.obtainMessage(UPDATE_MSG);
    mHandler.sendMessageDelayed(message, UPDATE_TIME);
  }

  public void cancelLoop() {
    mHandler.removeMessages(UPDATE_MSG);
  }

  private static class WeakReferenceHandler extends Handler {
    private WeakReference<SafariADViewPagerContainer> mRefContainer;

    public WeakReferenceHandler(SafariADViewPagerContainer container) {
      mRefContainer = new WeakReference<>(container);
    }

    @Override
    public void handleMessage(Message msg) {
      SafariADViewPagerContainer container = mRefContainer.get();
      if (container != null) {
        container.moveToNext();
      }
    }
  }

  /**
   * 开始轮播
   */
  private void startLoop() {
    // 开始轮播
    autoLoop = true;
    mSubscription = Observable.interval(delay, TimeUnit.MILLISECONDS)
        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<Long>() {
          @Override
          public void call(Long aLong) {
            if (viewPager != null && autoLoop)
              viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
          }
        });
  }

  private void endLoop() {
    autoLoop = false;
  }

  /**
   * 取消轮播订阅
   *
   * 界面销毁时，需调用以避免发生内存泄漏
   */
  public void cancelSubscription() {
    if (mSubscription != null) {
      mSubscription.unsubscribe();
      mSubscription = null;
    }
  }


}
