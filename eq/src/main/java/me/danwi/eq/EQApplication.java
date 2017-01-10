package me.danwi.eq;

import android.app.Application;
import android.content.Context;

import java.io.File;
import java.util.Collections;
import java.util.List;

import me.danwi.eq.core.ServiceProducers;
import me.danwi.eq.utils.FileUtils;
import me.danwi.eq.utils.SdCardUtils;
import okhttp3.Interceptor;

/**
 * Created with Android Studio.
 * User: 最帅最帅的RunningSnail
 * Date: 16/2/27
 * Time: 上午10:27
 */
public abstract class EQApplication extends Application {

    public String TAG = this.getClass().getSimpleName();

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        new ServiceProducers.Builder()
                .url(getUrl())
                .connectTimeOut(connectionTimeOut())
                .readTimeOut(readTimeOut())
                .writeTimeOut(writeTimeOut())
                .pre(getPre())
                .post(getPost())
                .dir(getDir())
                .size(getSize())
                .debug(isDebug())
                .build();

    }

    public static Context getContext() {
        return context;
    }


    /**
     * 是否开启调试模式
     *
     * @return
     */
    public abstract boolean isDebug();

    /**
     * 配置服务器地址
     *
     * @return
     */
    public abstract String getUrl();

    /**
     * 请求之前的拦截器
     *
     * @return
     */
    public List<Interceptor> getPre() {
        return Collections.emptyList();
    }

    /**
     * 请求之后的拦截器
     *
     * @return
     */
    public List<Interceptor> getPost() {
        return Collections.emptyList();
    }

    /**
     * 默认的缓存目录 eq_cache
     *
     * @return
     */
    public String getDir() {
        String parent = SdCardUtils.getDiskCacheDirPath(this);
        File dir = FileUtils.createDir(parent, "eq_cache");
        return dir.getPath();
    }

    /**
     * 配置缓存大小,默认是10M
     *
     * @return
     */
    public int getSize() {
        return 10;
    }

    //10秒钟
    public int connectionTimeOut() {
        return 10;
    }

    public int readTimeOut() {
        return 10;
    }

    public int writeTimeOut() {
        return 10;
    }
}
