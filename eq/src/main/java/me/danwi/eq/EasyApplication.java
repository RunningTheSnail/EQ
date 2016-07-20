package me.danwi.t.eq;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.LogLevel;

import java.util.List;

import me.danwi.t.eq.core.ServiceProducers;
import me.danwi.t.eq.utils.LogUtils;
import okhttp3.Interceptor;

/**
 * Created by RunningSnail on 16/2/27.
 */
public abstract class EasyApplication extends Application {

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
     * 获取缓存目录
     *
     * @return
     */
    public abstract String getDir();
}
