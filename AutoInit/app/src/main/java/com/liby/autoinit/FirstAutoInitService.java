package com.liby.autoinit;

import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;

import androidx.annotation.NonNull;

import com.liby.init.annotation.AutoInit;
import com.liby.init.api.AutoInitService;

/**
 * @author: LiBing
 * @date: 2021_05_30
 * @desc:
 */
@AutoInit
public class FirstAutoInitService implements AutoInitService {
    @Override
    public void attachBaseContext(Context context) {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onTrimMemory(int i) {

    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration configuration) {

    }

    @Override
    public void registerComponentCallbacks(ComponentCallbacks componentCallbacks) {

    }

    @Override
    public void unregisterComponentCallbacks(ComponentCallbacks componentCallbacks) {

    }

    @Override
    public void registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {

    }

    @Override
    public void unregisterActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {

    }
}
