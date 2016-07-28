package me.danwi.eq.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import me.danwi.eq.EQApplication;

/**
 * Created by RunningSnail on 16/7/28.
 */
public class NetUtils {

    private NetUtils() {

    }

    /**
     * 判断网络是否连接
     *
     * @return
     */
    public static boolean isNetWorkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) EQApplication.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            try {
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                return networkInfo.isAvailable();
            } catch (Exception ignored) {
            }
        }
        return false;
    }
}
