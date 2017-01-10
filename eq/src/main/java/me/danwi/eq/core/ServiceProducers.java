package me.danwi.eq.core;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import me.danwi.eq.cookie.SimpleCookieJar;
import me.danwi.eq.interceptor.LoggerInterceptor;
import me.danwi.eq.utils.FileUtils;
import me.danwi.eq.utils.Utils;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created with Android Studio.
 * User: 最帅最帅的RunningSnail
 * Date: 16/1/20
 * Time: 下午5:06
 * <p>
 * 服务生产者
 */
public class ServiceProducers {

    private static ServiceProducers serviceProducers;

    private Retrofit retrofit;

    //没有默认配置Builder,可以通过外部构建
    private ServiceProducers(Builder builder) {
        //参数校验
        Utils.checkArgument(builder.url, String.format("Server Url:%s is null", builder.url));

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();

        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();

        //设置缓存
        if (builder.dir != null) {
            httpBuilder.cache(createCache(builder.dir, builder.size));
        }

        //配置拦截器
        if (builder.pre != null) {
            for (Interceptor interceptor : builder.pre) {
                httpBuilder.addInterceptor(interceptor);
            }
        }

        if (builder.post != null) {
            for (Interceptor interceptor : builder.post) {
                httpBuilder.addNetworkInterceptor(interceptor);
            }
        }

        //内置日志拦截器,外部控制
        if (builder.debug) {
            httpBuilder.addInterceptor(new LoggerInterceptor());
        }

        //设置连接，读取写入超时时间
        httpBuilder.connectTimeout(builder.connectTimeOut, TimeUnit.SECONDS);
        httpBuilder.readTimeout(builder.readTimeOut, TimeUnit.SECONDS);
        httpBuilder.writeTimeout(builder.writeTimeOut, TimeUnit.SECONDS);
        //默认支持cookie
        httpBuilder.cookieJar(new SimpleCookieJar());
        //创建OkHttpClient客户端
        OkHttpClient okHttpClient = httpBuilder.build();
        //创建retrofit对象,默认使用Gson解析Request和response
        retrofit = retrofitBuilder
                .baseUrl(builder.url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static ServiceProducers getInstance(Builder builder) {
        if (serviceProducers == null) {
            synchronized (ServiceProducers.class) {
                if (serviceProducers == null) {
                    serviceProducers = new ServiceProducers(builder);
                }
            }
        }
        return serviceProducers;
    }

    public static class Builder {
        private String url;
        private String dir;
        private List<Interceptor> pre;
        private List<Interceptor> post;
        private int size;
        private boolean debug = false;
        private int connectTimeOut;
        private int readTimeOut;
        private int writeTimeOut;

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

        //连接超时时长
        public Builder connectTimeOut(int connectTimeOut) {
            this.connectTimeOut = connectTimeOut;
            return this;
        }

        //读取超时时长
        public Builder readTimeOut(int readTimeOut) {
            this.readTimeOut = readTimeOut;
            return this;
        }

        //写入超时时长
        public Builder writeTimeOut(int writeTimeOut) {
            this.writeTimeOut = writeTimeOut;
            return this;
        }

        public ServiceProducers build() {
            return getInstance(this);
        }

    }

    //生产服务
    public static <T> T createService(Class<T> service) {
        if (serviceProducers == null) {
            throw new NullPointerException("You don't init ServiceProducers");
        }
        return serviceProducers.retrofit.create(service);
    }

    /**
     * 创建缓存目录
     *
     * @param dir  配置缓存目录
     * @param size 配置缓存大小
     * @return
     */
    private Cache createCache(String dir, int size) {
        File cacheDir = FileUtils.createDir(new File(dir));
        if (cacheDir != null) {
            return new Cache(cacheDir, size * 1024 * 1024);
        } else {
            throw new IllegalStateException("创建缓存文件夹失败");
        }
    }

}
