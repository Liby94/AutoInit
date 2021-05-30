package com.liby.autoinitapi;

import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author: LiBing
 * @date: 2021_05_30
 * @desc:
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public final class Init {

    private static Context context;
    public static List<AutoInitService> autoInitServiceList = new ArrayList<>();

    public static synchronized void putAutoService(AutoInitService autoInitService) {
        Optional.ofNullable(autoInitService).ifPresent(auto -> {
            if (!autoInitServiceList.contains(autoInitService)) {
                autoInitServiceList.add(auto);
            }
        });
    }

    public static synchronized void putAllAutoService(List<AutoInitService> autoInitService) {
        Optional.ofNullable(autoInitService).ifPresent(auto -> {
            autoInitServiceList.clear();
            autoInitServiceList.addAll(autoInitService);
        });
    }

    private Init(){}

    public static void attachBaseContext(Context base) {
        context = base;
    }

    public static void onCreate() {
        autoInitServiceList.forEach(AutoInitService::onCreate);
    }

    public static void onTrimMemory(int level) {
        autoInitServiceList.forEach(autoInitService -> autoInitService.onTrimMemory(level));
    }

    public static void onConfigurationChanged(@NonNull Configuration newConfig) {
        autoInitServiceList.forEach(autoInitService -> autoInitService.onConfigurationChanged(newConfig));
    }

    public static void registerComponentCallbacks(ComponentCallbacks callback) {
        autoInitServiceList.forEach(autoInitService -> autoInitService.registerComponentCallbacks(callback));
    }

    public static void unregisterComponentCallbacks(ComponentCallbacks callback) {
        autoInitServiceList.forEach(autoInitService -> autoInitService.unregisterComponentCallbacks(callback));
    }

    public static void registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callback) {
        autoInitServiceList.forEach(autoInitService -> autoInitService.registerActivityLifecycleCallbacks(callback));
    }

    public static void unregisterActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callback) {
        autoInitServiceList.forEach(autoInitService -> autoInitService.unregisterActivityLifecycleCallbacks(callback));
    }
}
