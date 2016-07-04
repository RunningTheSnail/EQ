package com.example.tanshuai.eq.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.example.tanshuai.eq.EasyApplication;

import java.util.UUID;

/**
 * Created by tanshuai on 16/2/27.
 */
public class CommonUtils {

    private CommonUtils() {

    }

    /**
     * 获取设备的唯一标志
     *
     * @return
     */
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public static String getDeviceId() {
        TelephonyManager telephonyManager = (TelephonyManager) EasyApplication.getContext().getSystemService(Context.TELEPHONY_SERVICE);
        //手机设备串号,装有SIM卡设备,首次启动系统随机生成的64位数字
        String deviceId, serialNumber, androidId;
        deviceId = telephonyManager.getDeviceId();
        serialNumber = telephonyManager.getSimSerialNumber();
        androidId = Settings.Secure.getString(EasyApplication.getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) deviceId.hashCode() << 32) | serialNumber.hashCode());
        return deviceUuid.toString();
    }
}
