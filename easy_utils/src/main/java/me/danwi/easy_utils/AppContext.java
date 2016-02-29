package me.danwi.easy_utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by tanshuai on 16/2/27.
 */
public class AppContext extends Application {
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
