package com.ytang.james.dailyp.base.utils;

import com.google.gson.Gson;

/**
 * 全局使用的 Gson ，避免重复创建。
 *
 *  Created by James on 16/10/24.
 */
public class GsonUtils {

    private static Gson sGson = new Gson();

    public static Gson getGson() {
        return sGson;
    }

}
