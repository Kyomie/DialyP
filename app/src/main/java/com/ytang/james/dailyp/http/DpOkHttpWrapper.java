package com.ytang.james.dailyp.http;


import android.text.TextUtils;

import com.ytang.james.dailyp.base.utils.CollectionUtils;
import com.ytang.james.dailyp.http.config.OkHttpConfigImpl;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * wrapper,a single instance, for OkHttp,retrofit,rxJava...
 * Created by James on 16/10/21.
 */
public class DpOkHttpWrapper {

    private static DpOkHttpWrapper sInstance;
    private OkHttpConfigImpl mConfig;
    private OkHttpClient mOkHttpClient;
    private Retrofit mRetrofit;
    private Retrofit.Builder mBuilder;

    private DpOkHttpWrapper() {
        init();
    }

    private void init() {
        initConfig();
        initOkHttpClient();
        initRetrofit();
    }

    private void initConfig() {
        mConfig = new OkHttpConfigImpl();
    }

    private void initOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (mConfig.getCache() != null) {
            builder.cache(mConfig.getCache());
        }
        if (!CollectionUtils.isEmpty(mConfig.getInterceptors())) {
            for (Interceptor i : mConfig.getInterceptors()) {
                builder.addInterceptor(i);
            }
        }

        mOkHttpClient = builder.build();
    }

    /**
     * retrofit默认连接配置，连接超时15秒,读取超时20秒,没有写入超时
     */
    private void initRetrofit() {
        mBuilder = new Retrofit.Builder();
        mBuilder.callFactory(mOkHttpClient)
                .baseUrl(mConfig.getBaseUrl());
        if (!CollectionUtils.isEmpty(mConfig.getConverter())) {
            for (Converter.Factory c : mConfig.getConverter()) {
                mBuilder.addConverterFactory(c);
            }
        }
        mBuilder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        mRetrofit = mBuilder.build();
    }

    public static DpOkHttpWrapper getInstance() {
        if (sInstance == null) {
            synchronized (DpOkHttpWrapper.class) {
                if (sInstance == null) {
                    sInstance = new DpOkHttpWrapper();
                }
            }
        }
        return sInstance;
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    public boolean clearCache() {
        try {
            mConfig.getCache().delete();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public void updateBaseUrl(String url) {
        if (!TextUtils.isEmpty(url)) {
            mBuilder = mBuilder.baseUrl(url);
            mRetrofit = mBuilder.build();
        }
    }
}
