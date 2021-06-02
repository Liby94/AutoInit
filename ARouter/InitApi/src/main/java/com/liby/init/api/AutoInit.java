package com.liby.init.api;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.util.Set;

/**
 * @author: LiBing
 * @date: 2021_06_03
 * @desc:
 */
public class AutoInit {

    public static final String TAG = AutoInit.class.getSimpleName();

    public static class GetInstance {
        public static final AutoInit INIT = new AutoInit();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void init(Context context) {
        try {
            Set<String> name = ClassUtil.getFileNameByPackageName(context, "com.liby.autoinit.init");
            name.stream().forEach(c -> Log.i(TAG,"name:" + c));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
