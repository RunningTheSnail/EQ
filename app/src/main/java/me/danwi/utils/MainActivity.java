package me.danwi.utils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.tanshuai.eq.core.ServiceProducers;
import com.example.tanshuai.eq.subscriber.BaseSubscriber;
import com.example.tanshuai.eq.transform.ThreadTransFormer;

import me.danwi.utils.api.BDApi;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BDApi bdApi = ServiceProducers.createService(BDApi.class);

        bdApi.get()
                .compose(new ThreadTransFormer<Void>())
                .subscribe(new BaseSubscriber<Void>() {
                    @Override
                    public void onNext(Void aVoid) {
                        super.onNext(aVoid);
                    }
                });
    }

}
