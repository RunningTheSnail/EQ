package me.danwi.easy_utils;

import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;

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
    public static String getDeviceId() {
        TelephonyManager telephonyManager = (TelephonyManager) AppContext.getContext().getSystemService(Context.TELEPHONY_SERVICE);
        //手机设备串号,装有SIM卡设备,首次启动系统随机生成的64位数字
        String deviceId, serialNumber, androidId;
        deviceId = telephonyManager.getDeviceId();
        serialNumber = telephonyManager.getSimSerialNumber();
        androidId = Settings.Secure.getString(AppContext.getContext().getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) deviceId.hashCode() << 32) | serialNumber.hashCode());
        return deviceUuid.toString();
    }
}
