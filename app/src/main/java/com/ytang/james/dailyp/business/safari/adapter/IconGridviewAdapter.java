package com.ytang.james.dailyp.business.safari.adapter;

import android.view.ViewGroup;

import com.ytang.james.dailyp.base.adapter.BaseAdapter;
import com.ytang.james.dailyp.base.mvp.BasePresenter;
import com.ytang.james.dailyp.base.mvp.BaseView;
import com.ytang.james.dailyp.business.safari.mvp.model.IconModel;
import com.ytang.james.dailyp.business.safari.mvp.presenter.IconVewPresenter;
import com.ytang.james.dailyp.business.safari.mvp.view.IconView;

/**
 * Created by James on 16/10/18.
 */
public class IconGridviewAdapter extends BaseAdapter<IconModel> {

    @Override
    protected BaseView newView(ViewGroup viewGroup, int var) {
        return IconView.newInstance(viewGroup);
    }

    @Override
    protected BasePresenter newPresenter(int var) {
        return new IconVewPresenter();
    }
}
