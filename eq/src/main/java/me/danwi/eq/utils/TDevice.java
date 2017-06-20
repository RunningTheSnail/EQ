package me.danwi.eq.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.FileProvider;
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
     * @param apkFile
     */
    public static void installApk(Context context, File apkFile) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        // 由于没有在Activity环境下启动Activity,设置下面的标签
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 24) { //判读版本是否在7.0以上
            //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
            Uri apkUri = FileProvider.getUriForFile(context, String.format("%s.fileprovider", context.getPackageName()), apkFile);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(apkFile),
                    "application/vnd.android.package-archive");
        }
        context.startActivity(intent);
    }


    /**
     * 卸载应用
     *
     * @param context
     */
    public static void unInstallApk(Context context) {
        Intent intent = new Intent(Intent.ACTION_DELETE);
        Uri packageURI = Uri.parse("package:" + context.getPackageName());
        intent.setData(packageURI);
        context.startActivity(intent);
    }

    /**
     * 获取应用程序版本号
     *
     * @return
     */
    public static int getVersionCode(Context context) {
        int versionCode;
        try {
            versionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException ex) {
            versionCode = 1;
        }
        return versionCode;
    }

    /**
     * 获取应用程序版本名称
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
