package com.ytang.james.dailyp.http.config;

import com.ytang.james.dailyp.base.GlobalConfig;
import com.ytang.james.dailyp.http.converter.GsonConverterFactory;
import com.ytang.james.dailyp.http.interceptor.CacheInterceptor;
import com.ytang.james.dailyp.http.interceptor.CookieInterceptor;
import com.ytang.james.dailyp.http.interceptor.ExtraParamsInterceptor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

import okhttp3.Cache;
import okhttp3.Interceptor;
import retrofit2.Converter;

/**
 * Created by James on 16/10/21.
 */
public class OkHttpConfigImpl implements IOkHttpConfig {
    private List<Interceptor> mInterceptors;
    private Cache mCache;
    private List<Converter.Factory> mConverterFactories;
    private static final String CACHE_PATH = "/api/okhttp";
    private static final int CACHE_SIZE = 5 * 1024 * 1024;

    private static final String BP_SIT = "http://api.sit.ffan.com";

    public OkHttpConfigImpl() {
        initInterceptors();
        initCache();
        initConverters();
    }

    private void initInterceptors() {
        mInterceptors = new ArrayList<>();
        mInterceptors.add(new ExtraParamsInterceptor());
        mInterceptors.add(new CacheInterceptor());
        mInterceptors.add(new CookieInterceptor());
    }

    private void initCache() {
        String parentPath = getCacheParentPath();
        if (parentPath != null) {
            File f = new File(parentPath.concat(CACHE_PATH));
            if (f.exists() || f.mkdirs()) {
                mCache = new Cache(f, CACHE_SIZE);
            }
        }
    }

    private void initConverters() {
        mConverterFactories = new ArrayList<>();
        mConverterFactories.add(GsonConverterFactory.create());
    }

    @Override
    public List<Interceptor> getInterceptors() {
        return mInterceptors;
    }

    @Override
    public Cache getCache() {
        return mCache;
    }

    @Override
    public String getBaseUrl() {
        return BP_SIT;
    }

    @Override
    public List<Converter.Factory> getConverter() {
        return mConverterFactories;
    }

    @Override
    public SSLSocketFactory getSSlSocketFactory() {
        return null;
    }

    @Override
    public HostnameVerifier getHostnameVerifier() {
        return null;
    }


    private String getCacheParentPath() {
        File file = GlobalConfig.getAppContext().getExternalFilesDir(null);
        if (file == null || !file.exists()) {
            file = GlobalConfig.getAppContext().getCacheDir();
        }
        String path = null;
        if (file != null) {
            path = file.getAbsolutePath();
        }
        return path;
    }
}

