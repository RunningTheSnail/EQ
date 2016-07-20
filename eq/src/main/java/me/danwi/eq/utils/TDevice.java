package me.danwi.eq.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.io.File;

import me.danwi.eq.EQApplication;

/**
 * Created by RunningSnail on 16/7/4.
 */
public class TDevice {

    /**
     * 安装apk
     *
     * @param context
     * @param file
     */
    public static void installApk(Context context, File file) {
        if (context == null || !file.exists()) {
            return;
        }
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    public static void unInstallApk(Context context, File file) {

    }

    /**
     * 获取应用程序版本
     *
     * @param packageName
     * @return
     */
    public static int getVersionCode(String packageName) {
        int versionCode = 0;
        try {
            versionCode = EQApplication.getContext().getPackageManager()
                    .getPackageInfo(packageName, 0).versionCode;
        } catch (PackageManager.NameNotFoundException ex) {
            versionCode = 0;
        }
        return versionCode;
    }

    /**
     * 获取应用名称
     *
     * @return
     */
    public static String getVersionName() {
        String name = "";
        try {
            name = EQApplication
                    .getContext()
                    .getPackageManager()
                    .getPackageInfo(EQApplication.getContext().getPackageName(),
                            0).versionName;
        } catch (PackageManager.NameNotFoundException ex) {
            name = "";
        }
        return name;
    }
}
