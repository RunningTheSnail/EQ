package me.danwi.utils;

import com.example.tanshuai.eq.EasyApplication;
import com.example.tanshuai.eq.interceptor.BaseHeaderInterceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Interceptor;

/**
 * Created by RunningSnail on 16/7/20.
 */
public class AppApplication extends EasyApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        List<Interceptor> interceptors = new ArrayList<>();
        interceptors.add(new BaseHeaderInterceptor() {
            @Override
            public Map<String, String> add() {
                Map<String, String> map = new HashMap<String, String>();
                map.put("name", "Jerry");
                map.put("age", "23");
                return map;
            }
        });
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
        return null;
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
