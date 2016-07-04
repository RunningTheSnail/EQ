package com.example.tanshuai.eq;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.Settings;

/**
 * Created by RunningSnail on 16/2/27.
 */
public class EasyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Settings settings = Logger.init().methodCount(0).hideThreadInfo();
        //非debug模式不开启日志输出功能
        if (!BuildConfig.DEBUG) {
            settings.logLevel(LogLevel.NONE);
        }
    }

    public static Context getContext() {
        return context;
    }
}
