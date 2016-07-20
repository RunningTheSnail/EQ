package com.example.tanshuai.eq.interceptor;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by RunningSnail on 16/7/20.
 * 给每个请求添加请求头
 */
public abstract class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        Map<String, String> map = add();
        if (map != null) {
            Set<String> set = map.keySet();
            for (String key : set) {
                builder.header(key, map.get(key));
            }
        }
        return chain.proceed(builder.build());
    }

    /**
     * 要添加的请求头
     *
     * @return
     */
    public abstract Map<String, String> add();
}
