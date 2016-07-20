package me.danwi.utils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.danwi.eq.EasyApplication;
import me.danwi.eq.interceptor.BaseHeaderInterceptor;
import okhttp3.Interceptor;

/**
 * Created by RunningSnail on 16/7/20.
 */
public class AppApplication extends EasyApplication {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    @Override
    public String getUrl() {
        return "http://192.168.253.103:3000";
    }

    @Override
    public List<Interceptor> getPre() {
        List<Interceptor> pre = new ArrayList<>();
        pre.add(new BaseHeaderInterceptor() {
            @Override
            public Map<String, String> add() {
                Map<String, String> map = new HashMap<>();
                map.put("name", "Jerry");
                map.put("age", "23");
                return map;
            }
        });
        return pre;
    }

    @Override
    public List<Interceptor> getPost() {
        return null;
    }

    @Override
    public String getDir() {
        return null;
    }
}
