package me.danwi.utils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.List;

import me.danwi.eq.core.ServiceProducers;
import me.danwi.eq.subscriber.CommonSubscriber;
import me.danwi.eq.transform.ThreadTransFormer;
import me.danwi.eq.utils.LogUtils;
import rx.Subscription;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    public DownLoadApi downLoadApi = ServiceProducers.createService(DownLoadApi.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView tv = (TextView) findViewById(R.id.send);
        Subscription subscription = downLoadApi.getAll("608bb376-5baf-4828-b670-8d5fe8bc08a0")
                .compose(new ThreadTransFormer<List<Video>>())
                .subscribe(new CommonSubscriber<List<Video>>() {
                    @Override
                    public void deal(String message) {
                        LogUtils.e(TAG, message);
                    }

                    @Override
                    public void onNext(List<Video> videos) {
                        LogUtils.d(TAG, "success");
                    }
                });
    }


}
