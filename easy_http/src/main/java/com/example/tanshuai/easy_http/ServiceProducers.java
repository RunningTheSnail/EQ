package com.example.tanshuai.easy_http;

import com.example.tanshuai.easy_http.cookie.SimpleCookieJar;
import com.example.tanshuai.easy_http.interceptor.LoggerInterceptor;

import java.io.File;
import java.util.List;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by RunningSnail on 16/1/20.
 * 服务生产者
 */
public class ServiceProducers {
    private static ServiceProducers serviceProducers;

    private Retrofit retrofit;

    private ServiceProducers(String url, String dir, List<Interceptor> netWork, List<Interceptor> interceptors) {
        checkNotNull(url, "url==null");
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        //为了配置拦截器
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        //开发环境配置日志拦截器
        if (BuildConfig.DEBUG) {
            httpBuilder.addInterceptor(new LoggerInterceptor());
        }
        if (dir != null) {
            //设置缓存
            httpBuilder.cache(createCache(dir));
        }
        //修改Request和Response之类的拦截器
        if (netWork != null) {
            for (Interceptor interceptor : netWork) {
                httpBuilder.addNetworkInterceptor(interceptor);
            }
        }
        if (interceptors != null) {
            for (Interceptor interceptor : interceptors) {
                httpBuilder.addInterceptor(interceptor);
            }
        }
        //默认支持cookie
        httpBuilder.cookieJar(new SimpleCookieJar());
        //创建OkHttpClient客户端
        OkHttpClient okHttpClient = httpBuilder.build();
        //创建retrofit对象,默认使用Gson解析Request和response
        retrofit = retrofitBuilder.baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    private static ServiceProducers getInstance(String url, String dir, List<Interceptor> netWorks, List<Interceptor> interceptors) {
        if (serviceProducers == null) {
            synchronized (ServiceProducers.class) {
                if (serviceProducers == null) {
                    serviceProducers = new ServiceProducers(url, dir, netWorks, interceptors);
                }
            }
        }
        return serviceProducers;
    }

    public static class Builder {
        //请求的服务器地址,必须以 /  结尾
        private String url;
        //缓存目录设置
        private String dir;
        //响应之后处理 拦截器
        private List<Interceptor> netWorks;
        //请求之前处理 拦截器
        private List<Interceptor> applications;

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder dir(String dir) {
            this.dir = dir;
            return this;
        }

        public Builder netWork(List<Interceptor> netWork) {
            this.netWorks = netWork;
            return this;
        }

        public Builder applicationInterceptor(List<Interceptor> applications) {
            this.applications = applications;
            return this;
        }

        public ServiceProducers build() {
            return getInstance(url, dir, netWorks, applications);
        }
    }

    //生产服务
    public static <T> T createService(Class<T> service) {
        if (serviceProducers == null) {
            throw new NullPointerException("ServiceProducers必须初始化");
        }
        return serviceProducers.retrofit.create(service);
    }

    /**
     * 创建缓存
     *
     * @return
     */
    public Cache createCache(String dir) {
        return createCache(dir, 10);
    }

    public Cache createCache(String dir, long size) {
        checkNotNull(dir, "缓存目录不能为null");
        //判断文件是否存在
        File file = new File(dir);
        if (file.exists()) {
            if (file.delete()) {
                if (file.mkdir()) {

                }
            }
        }
        //缓存目录大小10M
        return new Cache(file, size * 1024 * 1024);
    }

    //检查服务器地址是否合法
    public String checkNotNull(String url, String message) {
        if (url == null) {
            throw new NullPointerException(message);
        }
        return url;
    }

}
