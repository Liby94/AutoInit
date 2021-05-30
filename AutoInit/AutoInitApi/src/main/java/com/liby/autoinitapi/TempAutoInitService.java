package com.liby.autoinitapi;

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
class TempAutoInitService implements AutoInitService {
    @Override
    public void attachBaseContext(Context base) {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onTrimMemory(int level) {

    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {

    }

    @Override
    public void registerComponentCallbacks(ComponentCallbacks callback) {

    }

    @Override
    public void unregisterComponentCallbacks(ComponentCallbacks callback) {

    }

    @Override
    public void registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callback) {

    }

    @Override
    public void unregisterActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callback) {

    }
}
