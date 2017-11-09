package com.ytang.james.dailyp.business.safari.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ytang.james.dailyp.R;
import com.ytang.james.dailyp.business.safari.mvp.model.ADHttpModel;
import com.ytang.james.dailyp.business.safari.mvp.model.ADModel;
import com.ytang.james.dailyp.business.safari.mvp.model.IconHttpModel;
import com.ytang.james.dailyp.business.safari.mvp.model.IconModel;
import com.ytang.james.dailyp.business.safari.presenter.SafariIconViewPagerContainerPresenter;
import com.ytang.james.dailyp.business.safari.view.SafariADViewPagerContainer;
import com.ytang.james.dailyp.business.safari.view.SafariIconViewPagerContainer;

import java.util.ArrayList;
import java.util.List;

public class SafariActivity extends AppCompatActivity {

    public static final String TAG = "SafariActivity";

    private String[] TITLES;
    private IconHttpModel model;
    private String[] DESCRIPT;
    private ADHttpModel adHttpModel;

    private SafariIconViewPagerContainer mContainer;
    private SafariADViewPagerContainer mADContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safari);
        Log.i(TAG, "onCreate()");
        initViews();
        initData();
        bindData();
    }

    private void initViews(){
        mContainer = (SafariIconViewPagerContainer) findViewById(R.id.container);
        mADContainer = (SafariADViewPagerContainer) findViewById(R.id.ad_container);
    }

    private void initData(){
        //icon功能球
        model = new IconHttpModel();
        List<IconModel> models = new ArrayList<>();
        TITLES = getResources().getStringArray(R.array.nav_icon_titles);
        int len = TITLES.length;
        for(int i = 0; i < len; i++){
            IconModel iconModel = new IconModel();
            iconModel.setId(i);
            iconModel.setName(TITLES[i]);
            //动态获取资源ID，第一个参数是资源名，第二个参数是资源类型例如drawable，string等，第三个参数包名
            iconModel.setResId(getResources().getIdentifier("ic_category_" + i, "drawable", getPackageName()));
            models.add(iconModel);
        }
        model.setData(models);

        //AD广告资源位
        adHttpModel = new ADHttpModel();
        List<ADModel> adModels = new ArrayList<>();
        DESCRIPT = getResources().getStringArray(R.array.ad_descript);
        int count = DESCRIPT.length;
        for(int i = 0; i < count; i++){
            ADModel adModel = new ADModel();
            adModel.setId(i);
            adModel.setDescript(DESCRIPT[i]);
            adModel.setResId(getResources().getIdentifier("vip_bg_" + i, "drawable", getPackageName()));
            adModels.add(adModel);
        }
        adHttpModel.setData(adModels);

    }

    private void bindData(){
        //icon
        SafariIconViewPagerContainerPresenter presenter = new SafariIconViewPagerContainerPresenter();
        presenter.bind(mContainer,model);

        //AD
        mADContainer.setDelay(2000);
        mADContainer.setData(adHttpModel);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mADContainer.cancelSubscription();
    }
}
