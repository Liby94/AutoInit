package com.liby.autoinitapi;

import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;

import androidx.annotation.NonNull;

/**
 * @author: LiBing
 * @date: 2021_05_30
 * @desc: application生命周期分发服务
 */
interface AutoInitService {

    void attachBaseContext(Context base);

    void onCreate();

    void onTrimMemory(int level);

    void onConfigurationChanged(@NonNull Configuration newConfig);

    void registerComponentCallbacks(ComponentCallbacks callback);

    void unregisterComponentCallbacks(ComponentCallbacks callback);

    void registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callback);

    void unregisterActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callback);
}
