package me.danwi.eq.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.io.File;
import java.util.UUID;

import me.danwi.eq.EQApplication;

/**
 * Created by RunningSnail on 16/7/4.
 */
public class TDevice {

    /**
     * 获取设备的唯一标志
     *
     * @return
     */
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public static String getDeviceId() {
        TelephonyManager telephonyManager = (TelephonyManager) EQApplication.getContext().getSystemService(Context.TELEPHONY_SERVICE);
        //手机设备串号,装有SIM卡设备,首次启动系统随机生成的64位数字
        String deviceId, serialNumber, androidId;
        deviceId = telephonyManager.getDeviceId();
        serialNumber = telephonyManager.getSimSerialNumber();
        androidId = Settings.Secure.getString(EQApplication.getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) deviceId.hashCode() << 32) | serialNumber.hashCode());
        return deviceUuid.toString();
    }

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
