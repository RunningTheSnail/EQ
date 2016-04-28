package com.example.tanshuai.easy_http;

import android.app.Application;
import android.content.Context;

/**
 * Created by tanshuai on 16/1/21.
 */
public class App extends Application {
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
