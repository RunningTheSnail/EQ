package me.danwi.eq.interceptor;

import android.text.TextUtils;

import java.io.IOException;

import me.danwi.eq.utils.LogUtils;
import me.danwi.eq.utils.NetUtils;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by RunningSnail on 16/1/20.
 * <p>
 * 缓存拦截器
 */
public class CacheInterceptor implements Interceptor {
    private static final String TAG = "CacheInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String cacheControl = request.cacheControl().toString();
        Response response;
        if (NetUtils.isNetWorkAvailable()) {
            //有网时,强制从网络中读取
            response = chain.proceed(request.newBuilder().cacheControl(CacheControl.FORCE_NETWORK).build());
        } else {
            //没有网络时,强制从缓存中读取
            response = chain.proceed(request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build());
        }
        //请求
        //获取Cache-Control请求头
        if (TextUtils.isEmpty(cacheControl)) {
            cacheControl = "no-cache";
        }
        //修改响应头,如果请求头设置了缓存就沿用
        return response.newBuilder().removeHeader("Pragma").header("Cache-Control", cacheControl).build();
    }
}
