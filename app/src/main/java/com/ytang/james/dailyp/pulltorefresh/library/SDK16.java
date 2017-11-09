package com.ytang.james.dailyp.pulltorefresh.library;

import android.annotation.TargetApi;
import android.view.View;

/**
 * Created by James on 16/10/20.
 */
@TargetApi(16)
public class SDK16 {

    public static void postOnAnimation(View view, Runnable runnable) {
        view.postOnAnimation(runnable);
    }

}
