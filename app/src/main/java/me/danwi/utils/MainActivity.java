package me.danwi.utils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import me.danwi.eq.core.ServiceProducers;
import me.danwi.eq.entity.DownLoadResult;
import me.danwi.eq.subscriber.CommonSubscriber;
import me.danwi.eq.transform.ResponseBodyTransFormer;
import me.danwi.eq.transform.ThreadTransFormer;
import me.danwi.eq.utils.LogUtils;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    public DownLoadApi downLoadApi = ServiceProducers.createService(DownLoadApi.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        downLoadApi.download()
                .compose(new ResponseBodyTransFormer() {
                    @Override
                    public String getFolder() {
                        return "cache";
                    }

                    @Override
                    public String getFileName() {
                        return "TiMo.mp4";
                    }
                })
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
    }

}
