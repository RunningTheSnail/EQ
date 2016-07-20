package me.danwi.eq.interceptor;

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by RunningSnail on 16/1/20.
 * <p>
 * 缓存拦截器
 */
public class CacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        //获取Cache-Control请求头
        String cacheControl = request.cacheControl().toString();
        if (TextUtils.isEmpty(cacheControl)) {
            cacheControl = "no-cache";
        }
        //修改响应头,如果请求头设置了缓存就沿用
        return response.newBuilder().removeHeader("Pragma").header("Cache-Control", "max-age=3600").build();
    }
}
