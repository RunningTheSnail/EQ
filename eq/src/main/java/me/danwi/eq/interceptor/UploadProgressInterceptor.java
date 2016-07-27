package me.danwi.eq.interceptor;

import java.io.IOException;

import me.danwi.eq.progress.ProgressListener;
import me.danwi.eq.progress.ProgressRequestBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by RunningSnail on 16/7/26.
 */
public class UploadProgressInterceptor implements Interceptor {
    public static final String TAG = "UploadProgressInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        return chain.proceed(request.newBuilder().post(new ProgressRequestBody(request.body(), new ProgressListener() {
            @Override
            public void update(long bytes, long contentLength, boolean done) {
//                LogUtils.d(TAG, bytes + "------" + contentLength + "-----" + done);
            }
        })).build());
    }
}
