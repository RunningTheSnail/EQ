package me.danwi.utils;

import android.app.Application;

import com.example.tanshuai.eq.core.ServiceProducers;
import com.example.tanshuai.eq.interceptor.HeaderInterceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Interceptor;

/**
 * Created by RunningSnail on 16/7/20.
 */
public class AppApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        List<Interceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderInterceptor() {
            @Override
            public Map<String, String> add() {
                Map<String, String> map = new HashMap<String, String>();
                map.put("name", "Jerry");
                return map;
            }
        });
        new ServiceProducers.Builder().url("http://192.168.253.103:3000/").applicationInterceptor(interceptors).build();
    }
}
