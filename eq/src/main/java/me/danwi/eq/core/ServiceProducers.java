package me.danwi.eq.core;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import me.danwi.eq.cookie.SimpleCookieJar;
import me.danwi.eq.interceptor.LoggerInterceptor;
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
 * <p/>
 * 服务生产者
 */
public class ServiceProducers {

    //请求的服务器地址,必须以 /  结尾
    final String url;

    //缓存目录设置
    final String dir;

    //请求之前处理 拦截器
    final List<Interceptor> pre;

    //响应之后处理 拦截器
    final List<Interceptor> post;

    //配置缓存大小
    final int size;

    final boolean debug;

    final int connectTimeOut;
    final int readTimeOut;
    final int writeTimeOut;

    private static ServiceProducers serviceProducers;

    private Retrofit retrofit;

    private OkHttpClient okHttpClient;

    //没有默认配置Builder,可以通过外部构建
    private ServiceProducers(Builder builder) {
        this.url = builder.url;
        this.dir = builder.dir;
        this.pre = builder.pre;
        this.post = builder.post;
        this.debug = builder.debug;
        this.size = builder.size;
        this.connectTimeOut = builder.connectTimeOut;
        this.readTimeOut = builder.readTimeOut;
        this.writeTimeOut = builder.writeTimeOut;

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
        httpBuilder.connectTimeout(connectTimeOut, TimeUnit.SECONDS);
        httpBuilder.readTimeout(readTimeOut, TimeUnit.SECONDS);
        httpBuilder.writeTimeout(writeTimeOut, TimeUnit.SECONDS);
        //默认支持cookie
        httpBuilder.cookieJar(new SimpleCookieJar());
        //创建OkHttpClient客户端
        okHttpClient = httpBuilder.build();
        //创建retrofit对象,默认使用Gson解析Request和response
        retrofit = retrofitBuilder
                .baseUrl(url)
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
            throw new NullPointerException("you don't init serviceProducers");
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
