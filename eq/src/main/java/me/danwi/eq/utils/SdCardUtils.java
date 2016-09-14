package me.danwi.eq.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by RunningSnail on 16/6/2.
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
        if (getRoot() != null) {
            return getRoot().getPath();
        }
        return null;
    }

    public static File getRoot() {
        if (isExist()) {
            return Environment.getRootDirectory();
        }
        return null;
    }
}
