package com.ytang.james.dailyp.pulltorefresh.library.draglayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.ytang.james.dailyp.R;
import com.ytang.james.dailyp.base.utils.ViewUtils;
import com.ytang.james.dailyp.pulltorefresh.library.PullToRefreshBase;

/**
 * Created by James on 16/10/21.
 */
public class PullToRefreshDragFullViewLayout extends PullToRefreshBase<DragFullViewLayout> {

    private int mLayoutId;

    public PullToRefreshDragFullViewLayout(Context context) {
        this(context, null);
    }

    public PullToRefreshDragFullViewLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected DragFullViewLayout createRefreshableView(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RefreshDragFullViewLayout);
        mLayoutId = a.getResourceId(R.styleable.RefreshDragFullViewLayout_drag_layout, 0);
        a.recycle();
        DragFullViewLayout dragFullViewLayout = (DragFullViewLayout) ViewUtils.newInstance(context, mLayoutId);
        return dragFullViewLayout;
    }

    @Override
    protected boolean isReadyForPullDown() {
        return !mRefreshableView.isDragged() && !mRefreshableView.needPullDownChild()
                && mRefreshableView.isDragToTop();
    }

    @Override
    protected boolean isReadyForPullUp() {
        return false;
    }
}

