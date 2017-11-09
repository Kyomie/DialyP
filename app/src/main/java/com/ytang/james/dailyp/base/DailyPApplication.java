package com.ytang.james.dailyp.base;

import android.app.Application;

/**
 * Created by James on 16/10/21.
 */
public class DailyPApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        GlobalConfig.setAppContext(this);
        initProcess();
    }

    private void initProcess(){

    }

}
