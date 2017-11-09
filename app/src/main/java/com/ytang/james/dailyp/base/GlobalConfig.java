package com.ytang.james.dailyp.base;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by James on 16/10/21.
 */
public class GlobalConfig {

    private static final String CLIENT_VERSION = "client_version";
    private static Context appContext;
    private static boolean debug = false;
    private static boolean sIsUpgrade;

    public static void setAppContext(Context context) {
        appContext = context;
    }

    public static Context getAppContext() {
        return appContext;
    }

    /**
     * This should only be called in main android project, like in Application,
     * because in Gradle build system the BuildConfig.DEBUG in library project is always false.
     *
     * @param debug true if in debug mode, false otherwise.
     */
    public static void setDebug(boolean debug) {
        GlobalConfig.debug = debug;
    }

    /**
     * Because in Gradle build system the BuildConfig.DEBUG in library project is always false,
     * and this will not be fixed in short-term, so we need to use this instead of
     * BuildConfig.DEBUG in library project.
     *
     * @return true if in debug mode, false otherwise.
     */
    public static boolean isDebug() {
        return debug;
    }

    public static int getVersionCode() {
        try {
            PackageInfo packageInfo = appContext.getPackageManager().getPackageInfo(appContext
                    .getPackageName(), PackageManager.GET_ACTIVITIES);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getVersionName() {
        try {
            PackageInfo packageInfo = appContext.getPackageManager().getPackageInfo(appContext
                    .getPackageName(), PackageManager.GET_ACTIVITIES);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /*public static void initClientVersion() {
        int versionCode = getVersionCode();
        int oldVersion = PreferenceHelper.getInstance().getInt(CLIENT_VERSION, 0);
        PreferenceHelper.getInstance().saveInt(CLIENT_VERSION, versionCode);
        sIsUpgrade = (oldVersion != versionCode);
    }*/

    public static boolean isUpgrade() {
        return sIsUpgrade;
    }
}
