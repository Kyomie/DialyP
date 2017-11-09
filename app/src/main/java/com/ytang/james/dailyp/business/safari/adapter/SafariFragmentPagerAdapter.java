package com.ytang.james.dailyp.business.safari.adapter;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

import com.ytang.james.dailyp.R;
import com.ytang.james.dailyp.base.GlobalConfig;
import com.ytang.james.dailyp.business.safari.view.SafariTabContainer;
import com.ytang.james.dailyp.common.view.tab.PagerSlidingTabStrip;
import com.ytang.james.dailyp.test.fragment.AFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James on 16/10/21.
 */
public class SafariFragmentPagerAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.TabProvider{

    private List<String> mFragmentNames;
    private List<PagerSlidingTabStrip.Tab> mTabs;
    private SparseArray<Fragment> mFragments;
    private int mTabCount = 4;

    public SafariFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        initFragments();
        initTabs();
    }

    private void initFragments() {
        mFragmentNames = new ArrayList<>();
        mFragmentNames.add(AFragment.class.getName());
        mFragmentNames.add(AFragment.class.getName());
        mFragmentNames.add(AFragment.class.getName());
        mFragmentNames.add(AFragment.class.getName());
    }

    private void initTabs() {
        mTabs = new ArrayList<>();
        Resources res = GlobalConfig.getAppContext().getResources();
        String[] texts = res.getStringArray(R.array.home_tab_titles);
        TypedArray typedArray = res.obtainTypedArray(R.array.home_tab_icons);

        for (int i = 0; i < texts.length; i++) {
            SafariTabContainer view = SafariTabContainer.newInstance(GlobalConfig.getAppContext());
            view.getText().setText(texts[i]);
            Drawable d = res.getDrawable(typedArray.getResourceId(i, -1));
            view.getText().setCompoundDrawablesWithIntrinsicBounds(null, d, null, null);
            PagerSlidingTabStrip.Tab tab = new PagerSlidingTabStrip.Tab(texts[i], view);
            mTabs.add(tab);
        }
        typedArray.recycle();
        mTabCount = mTabs.size();
        mFragments = new SparseArray<>(mTabCount);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = mFragments.get(position);
        if (null == fragment) {
            fragment = Fragment.instantiate(GlobalConfig.getAppContext(), mFragmentNames.get(position));
            mFragments.put(position, fragment);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return mTabCount;
    }

    @Override
    public PagerSlidingTabStrip.Tab getTab(int position) {
        if (position > mTabs.size()) {
            return null;
        }

        return mTabs.get(position);
    }

    @Override
    public PagerSlidingTabStrip.Tab getTab(String id) {
        return null;
    }

    @Override
    public int getTabPositionById(String id) {
        return 0;
    }

    @Override
    public String getTabIdByPosition(int position) {
        return null;
    }
}
