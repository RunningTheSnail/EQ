package com.example.tanshuai.eq;

import android.app.Application;
import android.content.Context;

/**
 * Created by RunningSnail on 16/2/27.
 */
public class EasyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
