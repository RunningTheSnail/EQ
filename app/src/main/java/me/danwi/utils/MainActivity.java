package me.danwi.utils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import me.danwi.eq.core.ServiceProducers;
import me.danwi.eq.utils.FileUtil;
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

        FileUtil.writeFile("test", "test.text", "Running Snail".getBytes());
        String content = FileUtil.readFile("test", "test.text");
        LogUtils.d(TAG, content);
    }


}
