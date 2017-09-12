package me.danwi.utils.api;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import me.danwi.eq.core.ServiceProducers;
import me.danwi.eq.subscriber.ObserverAdapter;
import me.danwi.utils.R;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 17/9/7
 * Time: 上午10:13
 */
public class IndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ServiceProducers.Builder().url("https://www.baidu.com").build();
        Api api = ServiceProducers.createService(Api.class);
        api.get().subscribe(new ObserverAdapter<Empty>());
    }

}
