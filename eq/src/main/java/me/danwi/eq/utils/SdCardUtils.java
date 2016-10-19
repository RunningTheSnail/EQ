package me.danwi.eq.utils;

import android.os.Environment;

import java.io.File;

import me.danwi.eq.EQApplication;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/6/2
 * Time: 下午4:59
 */
public class SdCardUtils {
    private SdCardUtils() {

    }

    /**
     * 判断sd卡是否存在
     *
     * @return false不存在, true存在
     */
    public static boolean isExist() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 获取sd卡根路径
     *
     * @return
     */
    public static String getRootPath() {
        if (getRootFile() != null) {
            return getRootFile().getPath();
        }
        return null;
    }

    public static File getRootFile() {
        if (isExist()) {
            return Environment.getExternalStorageDirectory();
        }
        return null;
    }

    /**
     * 缓存存储位置
     *
     * @return
     */
    public static String getDiskCacheDirPath() {
        //缓存路径
        String cachePath;
        //判断是否存在sd卡
        if (isExist()) {
            //目录   /sd卡/Android/data/程序包名/cache
            cachePath = EQApplication.context.getExternalCacheDir().getPath();
        } else {
            //目录  /data/data/程序包名/cache
            cachePath = EQApplication.context.getCacheDir().getPath();
        }
        return cachePath;
    }

    public static String getDiskFileDirPath() {
        //缓存路径
        String filePath;
        //判断是否存在sd卡
        if (isExist()) {
            //目录   /sd卡/Android/data/程序包名/cache
            filePath = EQApplication.context.getExternalFilesDir("").getPath();
        } else {
            //目录  /data/data/程序包名/cache
            filePath = EQApplication.context.getFilesDir().getPath();
        }
        return filePath;
    }
}
