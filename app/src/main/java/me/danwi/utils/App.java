package me.danwi.utils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.danwi.eq.EQApplication;
import me.danwi.eq.interceptor.BridgeInterceptor;
import me.danwi.eq.utils.LogUtils;
import me.danwi.eq.utils.ScreenUtils;
import me.danwi.eq.utils.SdCardUtils;
import okhttp3.Interceptor;

/**
 * Created by RunningSnail on 16/7/20.
 */
public class App extends EQApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.d(TAG, SdCardUtils.getDiskFileDirPath());
        LogUtils.d(TAG, SdCardUtils.getDiskCacheDirPath());
        LogUtils.d(TAG, "手机分辨率宽%d,高%d", getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels);
        LogUtils.d(TAG, "densityDpi%d,density%f", getResources().getDisplayMetrics().densityDpi, getResources().getDisplayMetrics().density);
        LogUtils.d(TAG, "分辨率width:%d,height:%d", ScreenUtils.getScreenSize(this)[0], ScreenUtils.getScreenSize(this)[1]);
        LogUtils.d(TAG, "状态栏高度%d", ScreenUtils.getStatusBarHeight(this));
    }

    @Override
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    @Override
    public String getUrl() {
        return BuildConfig.SERVER_URL;
    }

    @Override
    public List<Interceptor> getPre() {
        List<Interceptor> pre = new ArrayList<>();
        pre.add(new BridgeInterceptor() {
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
        List<Interceptor> post = new ArrayList<>();
        return post;
    }

    //配置缓存目录

    @Override
    public String getDir() {
        return getExternalCacheDir().getPath() + "/cache";
    }
}
