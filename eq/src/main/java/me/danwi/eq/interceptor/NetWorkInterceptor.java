package me.danwi.eq.interceptor;

import java.io.IOException;

import me.danwi.eq.utils.LogUtils;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created with Android Studio.
 * User: 最帅最帅的RunningSnail
 * Date: 16/8/1
 * Time: 下午1:40
 */
public class NetWorkInterceptor implements Interceptor {
    public static final String TAG = "NetWorkInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        LogUtils.d(TAG,"Pre");
        Response response = chain.proceed(chain.request());
        LogUtils.d(TAG,"Post");
        return response;
    }
}
