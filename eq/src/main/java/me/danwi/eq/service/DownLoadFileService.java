package me.danwi.eq.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by RunningSnail on 16/7/4.
 */
public class DownLoadFileService extends IntentService {

    private static final String TAG = "DownLoadFileService";

    public DownLoadFileService() {
        super("DownLoadFileService");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public DownLoadFileService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }

    @Override
    public IBinder onBind(Intent intent) {
        show(Thread.currentThread().getName());
        return new MyBinder();
    }

    public void show(String message) {
        Log.i(TAG, message);
    }

    public class MyBinder extends Binder {

        public DownLoadFileService getService() {
            return DownLoadFileService.this;
        }
    }
}
