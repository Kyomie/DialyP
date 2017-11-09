package com.ytang.james.dailyp.pulltorefresh.library;

import android.view.View;

/**
 * Created by James on 16/10/20.
 *
 * Interface that allows PullToRefreshBase to hijack the call to
 * AdapterView.setEmptyView()
 */
public interface EmptyViewMethodAccessor {

    /**
     * Calls upto AdapterView.setEmptyView()
     *
     * @param View
     *          to set as Empty View
     */
    public void setEmptyViewInternal(View emptyView);

    /**
     * Should call PullToRefreshBase.setEmptyView() which will then
     * automatically call through to setEmptyViewInternal()
     *
     * @param View
     *          to set as Empty View
     */
    public void setEmptyView(View emptyView);

}
