package me.danwi.eq.interceptor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/7/20
 * Time: 下午3:15
 * <p/>
 */
public class BridgeInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        RequestBody requestBody = request.body();
        //判断GET请求
        if ("GET".equalsIgnoreCase(request.method())) {
            HttpUrl originUrl = request.url();
            HttpUrl.Builder urlBuilder = originUrl.newBuilder();
            //添加Query参数
            Set<String> set = getCommonParams().keySet();
            for (String key : set) {
                urlBuilder.addEncodedQueryParameter(key, getCommonParams().get(key));
            }
            builder.url(urlBuilder.build());
        } else if (requestBody != null) {
            if (requestBody.contentType().equals(MediaType.parse("application/x-www-form-urlencoded"))) {
                FormBody.Builder formBuilder = new FormBody.Builder();
                FormBody old = (FormBody) requestBody;
                //添加原有的参数
                for (int i = 0; i < old.size(); i++) {
                    formBuilder.addEncoded(old.encodedName(i), old.encodedValue(i));
                }
                //添加共同参数
                Set<String> set = getCommonParams().keySet();
                for (String key : set) {
                    formBuilder.addEncoded(key, getCommonParams().get(key));
                }
                builder.post(formBuilder.build());
            } else if (requestBody.contentType().toString().contains("multipart/form-data")) {
                MultipartBody oldBody = (MultipartBody) requestBody;
                MultipartBody.Builder multiBuilder = new MultipartBody.Builder();
                //设置MediaType 默认是multipart/mixed
                multiBuilder.setType(MultipartBody.FORM);
                for (int i = 0; i < oldBody.size(); i++) {
                    multiBuilder.addPart(oldBody.part(i));
                }
                //添加共同参数
                Set<String> set = getCommonParams().keySet();
                for (String key : set) {
                    multiBuilder.addPart(MultipartBody.Part.createFormData(key, getCommonParams().get(key)));
                }
                builder.post(multiBuilder.build());
            }
        }
        //添加共同的请求头
        Set<String> set = getCommonHeaders().keySet();
        for (String key : set) {
            builder.header(key, getCommonHeaders().get(key));
        }
        return chain.proceed(builder.build());
    }

    public Map<String, String> getCommonParams() {
        return new HashMap<>();
    }

    public Map<String, String> getCommonHeaders() {
        return new HashMap<>();
    }
}
