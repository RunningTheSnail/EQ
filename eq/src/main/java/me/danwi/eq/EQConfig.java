package me.danwi.eq;

import android.content.Context;

import java.io.File;
import java.util.Collections;
import java.util.List;

import me.danwi.eq.utils.FileUtils;
import me.danwi.eq.utils.SdCardUtils;
import okhttp3.Interceptor;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 17/8/2
 * Time: 下午2:20
 */
public abstract class EQConfig {
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
    public String getDir(Context context) {
        String parent = SdCardUtils.getDiskCacheDirPath(context);
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
