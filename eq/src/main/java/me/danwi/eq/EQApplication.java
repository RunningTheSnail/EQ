package me.danwi.eq;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.LogLevel;

import java.util.List;

import me.danwi.eq.core.ServiceProducers;
import me.danwi.eq.utils.LogUtils;
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
        LogLevel logLevel = isDebug() ? LogLevel.FULL : LogLevel.NONE;
        LogUtils.init("EQ", logLevel);
        new ServiceProducers.Builder()
                .url(getUrl())
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
    public abstract List<Interceptor> getPre();

    /**
     * 请求之后的拦截器
     *
     * @return
     */
    public abstract List<Interceptor> getPost();

    /**
     * 默认的缓存目录
     *
     * @return
     */
    public String getDir() {
        return getCacheDir().getPath();
    }

    /**
     * 配置缓存大小,默认是10M
     *
     * @return
     */
    public int getSize() {
        return 10;
    }
}
