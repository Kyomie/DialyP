package com.ytang.james.dailyp.business.safari.adapter;

import android.view.ViewGroup;

import com.ytang.james.dailyp.base.adapter.BasePagerAdapter;
import com.ytang.james.dailyp.base.mvp.BasePresenter;
import com.ytang.james.dailyp.base.mvp.BaseView;
import com.ytang.james.dailyp.business.safari.mvp.model.ADModel;
import com.ytang.james.dailyp.business.safari.mvp.presenter.ADViewPresenter;
import com.ytang.james.dailyp.business.safari.mvp.view.ADView;

/**
 * Created by James on 16/10/20.
 */
public class ADViewPagerAdapter extends BasePagerAdapter<ADModel> {
    @Override
    protected BaseView newView(ViewGroup viewGroup, int var) {
        return ADView.newInstance(viewGroup);
    }

    @Override
    protected BasePresenter newPresenter(int var) {
        return new ADViewPresenter();
    }
}
