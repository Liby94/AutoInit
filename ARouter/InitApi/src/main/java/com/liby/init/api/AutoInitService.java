package com.liby.init.api;

import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;

import androidx.annotation.NonNull;

/**
 * @author: LiBing
 * @date: 2021_05_30
 * @desc:
 */
public interface AutoInitService {

    void attachBaseContext(Context base);

    void onCreate();

    void onTrimMemory(int level);

    void onConfigurationChanged(@NonNull Configuration newConfig);

    void registerComponentCallbacks(ComponentCallbacks callback);

    void unregisterComponentCallbacks(ComponentCallbacks callback);

    void registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callback);

    void unregisterActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callback);

}
