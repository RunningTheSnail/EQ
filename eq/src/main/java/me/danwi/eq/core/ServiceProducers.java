package me.danwi.eq.core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import me.danwi.eq.cookie.SimpleCookieJar;
import me.danwi.eq.interceptor.LoggerInterceptor;
import me.danwi.eq.utils.FileUtils;
import me.danwi.eq.utils.Utils;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
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

    public Retrofit retrofit;

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
            builder.pre.forEach(httpBuilder::addInterceptor);
        }

        if (builder.post != null) {
            builder.post.forEach(httpBuilder::addNetworkInterceptor);
        }

        //内置日志拦截器,外部控制
        if (builder.debug) {
            httpBuilder.addInterceptor(new LoggerInterceptor());
        }

        //设置证书
        if (builder.certificate != null) {
            SSLContext sslContext = setCertificate(builder.certificate);
            if (sslContext != null) {
                httpBuilder.socketFactory(sslContext.getSocketFactory());
            }
        }

        //设置连接，读取写入超时时间
        if (builder.connectTimeOut == 0) {
            builder.connectTimeOut = 60;
        }
        if (builder.readTimeOut == 0) {
            builder.readTimeOut = 60;
        }
        if (builder.writeTimeOut == 0) {
            builder.writeTimeOut = 60;
        }
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
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
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
        private InputStream certificate;

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

        public Builder certificate(InputStream certificate) {
            this.certificate = certificate;
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

    public SSLContext setCertificate(InputStream certificate) {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            String certificateAlias = Integer.toString(0);
            keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));
            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
            return sslContext;
        } catch (CertificateException | KeyStoreException | NoSuchAlgorithmException | KeyManagementException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
