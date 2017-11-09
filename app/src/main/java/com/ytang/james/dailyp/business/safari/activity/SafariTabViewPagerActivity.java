package com.ytang.james.dailyp.business.safari.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ytang.james.dailyp.R;
import com.ytang.james.dailyp.business.safari.adapter.SafariFragmentPagerAdapter;
import com.ytang.james.dailyp.business.safari.adapter.SlideViewPager;
import com.ytang.james.dailyp.common.view.tab.PagerSlidingTabStrip;

public class SafariTabViewPagerActivity extends AppCompatActivity {

    private final static int DEFAULT_INDEX = 0;

    private PagerSlidingTabStrip tabStrip;
    private SlideViewPager viewPager;

    private int index = DEFAULT_INDEX;

    public static void launch(Context context) {
        Intent intent = new Intent(context, SafariTabViewPagerActivity.class);
        if (!(context instanceof Activity)) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safari_tab_view_pager);
        initViews();
    }

    private void initViews(){
        tabStrip = (PagerSlidingTabStrip) findViewById(R.id.pager_tab_strip);
        viewPager = (SlideViewPager) findViewById(R.id.view_pager);
        viewPager.setScrollEnabled(false);
        SafariFragmentPagerAdapter adapter = new SafariFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        if(tabStrip != null){
            tabStrip.setViewPager(viewPager);
        }
        viewPager.setCurrentItem(index);
    }

}
