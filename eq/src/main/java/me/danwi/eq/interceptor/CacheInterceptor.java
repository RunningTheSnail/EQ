package me.danwi.eq.interceptor;

import android.text.TextUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.danwi.eq.EQApplication;
import me.danwi.eq.utils.NetWorkUtils;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created with Android Studio.
 * User: 最帅最帅的RunningSnail
 * Date: 16/1/20
 * Time: 上午11:37
 * 缓存拦截器
 */
public class CacheInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        //判断是否是get请求
        if ("GET".equalsIgnoreCase(request.method())) {
            //获取请求的地址
            String url = request.url().url().toString();
            String cacheControl = request.cacheControl().toString();
            Response response;
            if (NetWorkUtils.isNetWorkAvailable(EQApplication.context)) {
                //截取请求的url不包含参数
                String result = url.split("\\?")[0];
                if (forceNetWork().contains(result)) {
                    //强制从网络中获取
                    response = chain.proceed(chain.request().newBuilder().cacheControl(CacheControl.FORCE_NETWORK).build());
                } else {
                    //先判断缓存是否过期,没有过期在缓存中获取,过期了从网络中获取
                    response = chain.proceed(request);
                }
            } else {
                //没有网络时,强制从缓存中读取,如果缓存不存在或者过期会返回504错误
                response = chain.proceed(request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build());
            }
            //获取Cache-Control请求头
            if (TextUtils.isEmpty(cacheControl)) {
                cacheControl = "no-cache";
            }
            //修改响应头,如果请求头设置了缓存就沿用
            return response.newBuilder().removeHeader("Pragma").header("Cache-Control", cacheControl).build();
        }
        return chain.proceed(request);
    }

    //需要强制从网络中获取的请求
    //子类可以重写该方法
    public List<String> forceNetWork() {
        return new ArrayList<>();
    }
}
