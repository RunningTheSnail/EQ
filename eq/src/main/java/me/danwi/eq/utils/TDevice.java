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
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/7/4
 * Time: 上午11:01
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
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /**
     * 卸载应用
     *
     * @param context
     * @param packageName
     */
    public static void unInstallApk(Context context, String packageName) {
        Intent intent = new Intent(Intent.ACTION_DELETE);
        Uri packageURI = Uri.parse("package:" + packageName);
        intent.setData(packageURI);
        context.startActivity(intent);
    }

    /**
     * 获取应用程序版本
     *
     * @param packageName
     * @return
     */
    public static int getVersionCode(Context context, String packageName) {
        int versionCode;
        try {
            versionCode = context.getPackageManager().getPackageInfo(packageName, 0).versionCode;
        } catch (PackageManager.NameNotFoundException ex) {
            versionCode = 1;
        }
        return versionCode;
    }

    /**
     * 获取应用名称
     *
     * @return
     */
    public static String getVersionName(Context context) {
        String name;
        try {
            name = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException ex) {
            name = "";
        }
        return name;
    }
}
