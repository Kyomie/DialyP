package com.ytang.james.dailyp.pulltorefresh.library.draglayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

/**
 * Created by James on 16/10/21.
 */
public abstract class DragLayout extends ViewGroup{

    protected WeakReference<TouchInterceptor> mInterceptorRef;

    public DragLayout(Context context) {
        super(context);
    }

    public DragLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DragLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DragLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void registerTouchInterceptor(TouchInterceptor interceptor) {
        if (interceptor != null) {
            mInterceptorRef = new WeakReference<>(interceptor);
        } else {
            mInterceptorRef = null;
        }
    }


    public abstract boolean needPullDownChild();

    public abstract boolean isDragged();

}
