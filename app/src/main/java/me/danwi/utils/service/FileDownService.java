package me.danwi.utils.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/12/13
 * Time: 下午2:19
 */
public class FileDownService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new ExecuteTaskBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //获取下载地址
        String url = intent.getStringExtra("url");
        return super.onStartCommand(intent, flags, startId);
    }

    public static class ExecuteTaskBinder extends Binder {

    }
}
