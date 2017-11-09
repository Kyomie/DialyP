package com.ytang.james.dailyp.test.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
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
import com.ytang.james.dailyp.http.ApiCreator;
import com.ytang.james.dailyp.http.DpCallback;
import com.ytang.james.dailyp.pulltorefresh.library.PullToRefreshBase;
import com.ytang.james.dailyp.pulltorefresh.library.draglayout.DragFullViewLayout;
import com.ytang.james.dailyp.pulltorefresh.library.draglayout.PullToRefreshDragFullViewLayout;
import com.ytang.james.dailyp.test.model.AdvertiseResponseModel;
import com.ytang.james.dailyp.test.request.AdvertiseRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class TestSafariActivity extends AppCompatActivity {

    public static final String TAG = "SafariActivity";

    private String[] TITLES;
    private IconHttpModel model;
    private String[] DESCRIPT;
    private ADHttpModel adHttpModel;


    private PullToRefreshDragFullViewLayout dragFullViewLayout;
    private SafariIconViewPagerContainer mContainer;
    private SafariADViewPagerContainer mADContainer;

    public static void launch(Context context) {
        Intent intent = new Intent(context, TestSafariActivity.class);
        if (!(context instanceof Activity)) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_safari);
        Log.i(TAG, "onCreate()");
        initViews();
        initData();
        bindData();
        getADData();
    }

    private void initViews(){
        dragFullViewLayout = (PullToRefreshDragFullViewLayout) findViewById(R.id.refresh_layout);
        dragFullViewLayout.setMode(PullToRefreshBase.Mode.PULL_DOWN_TO_REFRESH);
        dragFullViewLayout.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<DragFullViewLayout>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<DragFullViewLayout> refreshView) {
                initData();
                bindData();
                new Handler().postDelayed(runnable,2000L);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<DragFullViewLayout> refreshView) {

            }
        });
        mContainer = (SafariIconViewPagerContainer) findViewById(R.id.container);
        mADContainer = (SafariADViewPagerContainer) findViewById(R.id.ad_container);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            dragFullViewLayout.onRefreshComplete();
        }
    };

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

    private void getADData(){
//        AdvertiseRequest request = ApiCreator.createApi(AdvertiseRequest.class);
        AdvertiseRequest request = ApiCreator.createApiWithSopHost(AdvertiseRequest.class,"http://api.sit.ffan.com");
        Call<AdvertiseResponseModel> call = request.getAdvertiseModel("FA60A96BFF93327A5C3A1D703D2C656D","310100");
        call.enqueue(new DpCallback<AdvertiseResponseModel>() {
            @Override
            public void onResponse(AdvertiseResponseModel model) {
                if(model != null && model.getData() != null){
                    Log.i("dailyp","model != null");
                }
            }

            @Override
            public void onFailure(String message) {
                Log.e("dailyp","msg = " + message);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mADContainer.cancelSubscription();
    }




}
