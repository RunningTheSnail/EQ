package me.danwi.eq.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

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
    public static String getDiskCacheDirPath(Context context) {
        //缓存路径
        String cachePath = null;
        //判断是否存在sd卡
        if (isExist()) {
            //目录   /sd卡/Android/data/程序包名/cache
            File file = context.getExternalCacheDir();
            if (file != null) {
                cachePath = file.getPath();
            }
        } else {
            //目录  /data/data/程序包名/cache
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
    }

    public static File getDiskCacheDir(Context context) {
        return new File(getDiskCacheDirPath(context));
    }

    public static String getDiskFileDirPath(Context context) {
        //缓存路径
        String filePath = null;
        //判断是否存在sd卡
        if (isExist()) {
            //目录   /sd卡/Android/data/程序包名/cache
            File file = context.getExternalFilesDir("");
            if (file != null) {
                filePath = file.getPath();
            }
        } else {
            //目录  /data/data/程序包名/cache
            filePath = context.getFilesDir().getPath();
        }
        return filePath;
    }

    public static File getDiskFileDir(Context context) {
        return new File(getDiskFileDirPath(context));
    }
}
