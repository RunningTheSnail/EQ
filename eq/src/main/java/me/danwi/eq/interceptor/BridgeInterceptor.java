package me.danwi.eq.interceptor;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/7/20
 * Time: 下午3:15
 * <p/>
 * 1.请求头修改拦截器,添加统一的请求头
 * 2.每个请求添加不同的请求可以使用@Header注解
 */
public abstract class BridgeInterceptor implements Interceptor {
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
     * 要添加的请求头,例如:token,appVersion
     *
     * @return
     */
    public abstract Map<String, String> add();
}
