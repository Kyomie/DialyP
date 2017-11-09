package com.ytang.james.dailyp.http;

/**
 * Created by James on 16/10/24.
 */
public class ApiCreator {

    /**
     * 统一域名 OkHttpConfigImpl中的BP_SIT ＝ "http://api.sit.ffan.com"
     */
    public static <T> T createApi(Class<T> clazz) {
        if (clazz == null) {
            return null;
        }
        return DpOkHttpWrapper.getInstance().getRetrofit().create(clazz);
    }

    /**
     * 非统一域名的。可以A接口使http://api.sit.ffan.com，B接口使http://baidu.com,此时baseUrl传入的参数使http://baidu.com
     */
    public static <T> T createApiWithSopHost(Class<T> clazz, String baseUrl) {
        if (clazz == null) {
            return null;
        }
        DpOkHttpWrapper.getInstance().updateBaseUrl(baseUrl);
        T t = DpOkHttpWrapper.getInstance().getRetrofit().create(clazz);
        DpOkHttpWrapper.getInstance().updateBaseUrl(baseUrl);
        return t;
    }
}
