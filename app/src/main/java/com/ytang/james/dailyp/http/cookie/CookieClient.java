package com.ytang.james.dailyp.http.cookie;


import com.ytang.james.dailyp.base.utils.CollectionUtils;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by James on 16/10/24.
 */
public class CookieClient implements ICookieClient {
    private static ICookieClient sInstance;
    private CookieManager mCookieManager;
    private CookieStoreWrap mBpCookieStore;

    private CookieClient() {
        mBpCookieStore = new CookieStoreWrap();
        mCookieManager = new CookieManager(mBpCookieStore, CookiePolicy.ACCEPT_ALL);
    }

    public static synchronized ICookieClient getInstance() {
        if (sInstance == null) {
            sInstance = new CookieClient();
        }
        return sInstance;
    }

    @Override
    public void saveCookie(URI uri, Map<String, List<String>> header) {
        if (uri == null || header == null) {
            return;
        }
        try {
            mCookieManager.put(uri, header);
        } catch (IOException e) {
            // do nothing
        }
    }

    @Override
    public void saveCookie(URI uri, HttpCookie cookie) {
        if (uri == null || cookie == null) {
            return;
        }
        mBpCookieStore.add(uri, cookie);
    }

    @Override
    public String getCookieValue(URI uri) {
        if (uri == null) {
            return null;
        }
        Map<String, List<String>> cookieHeader = null;

        try {
            cookieHeader = mCookieManager.get(uri, new HashMap<String, List<String>>());
        } catch (IOException e) {
            // do nothing
        }

        if (CollectionUtils.isEmpty(cookieHeader)
                || CollectionUtils.isEmpty(cookieHeader.get(KEY_REQUEST_COOKIE))) {
            return null;
        }

        return cookieHeader.get(KEY_REQUEST_COOKIE).get(0);
    }

    @Override
    public List<HttpCookie> getCookies(URI uri) {
        return mBpCookieStore.get(uri);
    }

    @Override
    public void clear() {
        mBpCookieStore.removeAll();
    }
}
