package me.danwi.eq.core;

import java.io.File;
import java.util.List;

import me.danwi.eq.cookie.SimpleCookieJar;
import me.danwi.eq.interceptor.LoggerInterceptor;
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

    //私有构造函数
    private ServiceProducers() {

    }

    private static ServiceProducers serviceProducers;

    private Retrofit retrofit;

    private ServiceProducers(String url, String dir, List<Interceptor> pre, List<Interceptor> post, boolean debug, int size) {
        checkNotNull(url, "url==null");
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        //为了配置拦截器
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();

        if (dir != null) {
            //设置缓存
            httpBuilder.cache(createCache(dir, size));
        }

        if (pre != null) {
            for (Interceptor interceptor : pre) {
                httpBuilder.addInterceptor(interceptor);
            }
        }

        //修改Request和Response之类的拦截器
        if (post != null) {
            for (Interceptor interceptor : post) {
                httpBuilder.addNetworkInterceptor(interceptor);
            }
        }

        if (debug) {
            //内置日志拦截器,外部控制
            httpBuilder.addInterceptor(new LoggerInterceptor());
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

    private static ServiceProducers getInstance(String url, String dir, List<Interceptor> pre, List<Interceptor> post, boolean debug, int size) {
        if (serviceProducers == null) {
            synchronized (ServiceProducers.class) {
                if (serviceProducers == null) {
                    serviceProducers = new ServiceProducers(url, dir, pre, post, debug, size);
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

        //请求之前处理 拦截器
        private List<Interceptor> pre;

        //响应之后处理 拦截器
        private List<Interceptor> post;

        //配置缓存大小
        private int size;

        private boolean debug = false;

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder debug(boolean debug) {
            this.debug = debug;
            return this;
        }

        public Builder dir(String dir) {
            this.dir = dir;
            return this;
        }

        public Builder post(List<Interceptor> post) {
            this.post = post;
            return this;
        }

        public Builder pre(List<Interceptor> pre) {
            this.pre = pre;
            return this;
        }

        public Builder size(int size) {
            this.size = size;
            return this;
        }

        public ServiceProducers build() {
            return getInstance(url, dir, pre, post, debug, size);
        }
    }

    //生产服务
    public static <T> T createService(Class<T> service) {
        if (serviceProducers == null) {
            throw new NullPointerException("ServiceProducers必须初始化");
        }
        return serviceProducers.retrofit.create(service);
    }


    public Cache createCache(String dir, int size) {
        File file = new File(dir);
        //判断文件夹是否存在
        if (!file.exists()) {
            //创建目录
            file.mkdir();
        }
        return new Cache(file, size * 1024 * 1024);
    }

    //检查服务器地址是否合法
    private String checkNotNull(String url, String message) {
        if (url == null) {
            throw new NullPointerException(message);
        }
        return url;
    }

}
