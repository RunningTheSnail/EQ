package me.danwi.eq.interceptor;

import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by RunningSnail on 16/1/20.
 * <p>
 * 日志拦截器
 */
public class LoggerInterceptor implements Interceptor {

    private static final String TAG = LoggerInterceptor.class.getSimpleName();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long t1 = System.nanoTime();
        Logger.d(TAG, String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));
        Response response = chain.proceed(request);
        long t2 = System.nanoTime();
        Logger.d(TAG, String.format("Received response for %s in %.1fms%n%s%s", response.request().url(), (t2 - t1) / 1e6d, response.headers(), (response.cacheResponse() == null ? "请求结果来自网络" : "请求结果来自缓存")));
        return response;
    }
}
