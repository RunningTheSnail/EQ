package me.danwi.eq.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

import me.danwi.eq.api.DownLoadApi;
import me.danwi.eq.core.ServiceProducers;
import me.danwi.eq.entity.DownLoadResult;
import me.danwi.eq.subscriber.CommonSubscriber;
import me.danwi.eq.transform.ResponseBodyTransFormer;
import me.danwi.eq.transform.ThreadTransFormer;
import me.danwi.eq.utils.LogUtils;

/**
 * Created by RunningSnail on 16/7/4.
 */
public class DownLoadFileService extends Service {
    public DownLoadApi downLoadApi = ServiceProducers.createService(DownLoadApi.class);


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //获取文件的下载地址
        String key = intent.getStringExtra("key");
        String value = intent.getStringExtra("value");
        Map<String, String> params = new HashMap<>();
        params.put("", "");
        //下载文件
        downLoadApi.download(params)
                .compose(new ResponseBodyTransFormer())
                .compose(new ThreadTransFormer<DownLoadResult>())
                .subscribe(new CommonSubscriber<DownLoadResult>() {
                    @Override
                    public void deal(String message) {

                    }

                    @Override
                    public void onNext(DownLoadResult downLoadResult) {
                        LogUtils.d(TAG, downLoadResult);
                    }
                });

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
