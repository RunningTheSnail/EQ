package com.example.tanshuai.easy_http.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;

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

    public static void unInstallApk(Context context,File file){
        
    }
}
