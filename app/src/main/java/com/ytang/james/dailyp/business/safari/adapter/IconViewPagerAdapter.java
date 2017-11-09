package com.ytang.james.dailyp.business.safari.adapter;

import android.view.ViewGroup;

import com.ytang.james.dailyp.base.adapter.BasePagerAdapter;
import com.ytang.james.dailyp.base.mvp.BasePresenter;
import com.ytang.james.dailyp.base.mvp.BaseView;
import com.ytang.james.dailyp.business.safari.mvp.model.IconHttpModel;
import com.ytang.james.dailyp.business.safari.mvp.presenter.IconGridViewPresenter;
import com.ytang.james.dailyp.business.safari.view.IconGridViewContainer;

/**
 * Created by James on 16/10/19.
 */
public class IconViewPagerAdapter extends BasePagerAdapter<IconHttpModel> {

    @Override
    protected BaseView newView(ViewGroup viewGroup, int var) {
        return IconGridViewContainer.newInstance(viewGroup);
    }

    @Override
    protected BasePresenter newPresenter(int var) {
        return new IconGridViewPresenter();
    }
}
