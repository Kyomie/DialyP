package com.ytang.james.dailyp.http.interceptor;


import android.util.Log;

import com.ytang.james.dailyp.BuildConfig;
import com.ytang.james.dailyp.base.GlobalConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 商户app请求公共参数设置
 * Created by James on 16/10/24.
 */
public class ExtraParamsInterceptor implements Interceptor {

    private final static String KEY_VERSION = "version";
    private final static String KEY_USER_ID = "userId";
    private final static String KEY_AG_ID = "agid";
    private final static String KEY_LOGIN_TOKEN = "loginToken";
    private final static String VALUE_CLIENT_TYPE = "Android";
    private final static String DEVICE_ID = "deviceId";


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl.Builder urlBuilder = request.url().newBuilder();
        urlBuilder.addQueryParameter(KEY_VERSION, String.valueOf(BuildConfig.VERSION_CODE));
        HttpUrl httpUrl = urlBuilder.build();

        Log.i("DailyP"," http: " + httpUrl.toString());

        request = request.newBuilder().url(httpUrl).build();
        return chain.proceed(request);
    }

}
