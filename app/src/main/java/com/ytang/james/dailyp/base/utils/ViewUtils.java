package com.ytang.james.dailyp.base.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by James on 16/10/17.
 */
public class ViewUtils {

    public static View newInstance(ViewGroup parent, int resId) {
        return LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);
    }

    public static View newInstance(Context context, int resId) {
        return LayoutInflater.from(context).inflate(resId, (ViewGroup)null);
    }

}
