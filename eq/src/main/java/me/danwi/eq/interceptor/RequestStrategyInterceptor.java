package me.danwi.eq.interceptor;

import java.io.IOException;

import me.danwi.eq.utils.NetUtils;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by RunningSnail on 16/7/28.
 * 控制请求策略
 */
public class RequestStrategyInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response;
        if (NetUtils.isNetWorkAvailable()) {
            //有网时,强制从网络中读取
            response = chain.proceed(request.newBuilder().cacheControl(CacheControl.FORCE_NETWORK).build());
        } else {
            //没有网络时,强制从缓存中读取
            response = chain.proceed(request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build());
        }
        return response;
    }
}
