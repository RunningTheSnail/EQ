package me.danwi.utils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.danwi.eq.RequestUtils;
import me.danwi.eq.core.ServiceProducers;
import me.danwi.eq.entity.Upload;
import me.danwi.eq.subscriber.CommonSubscriber;
import me.danwi.eq.transform.ThreadTransFormer;
import me.danwi.eq.utils.LogUtils;
import me.danwi.eq.utils.SdCardUtils;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    public DownLoadApi downLoadApi = ServiceProducers.createService(DownLoadApi.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Upload upload1 = new Upload.Builder()
                .key("selftimeVideo")
                .fileName("test.jpg")
                .filePath(SdCardUtils.getRootPath() + "/cache/test.jpg")
                .build();
        Upload upload2 = new Upload.Builder()
                .key("cover")
                .fileName("test.jpg")
                .filePath(SdCardUtils.getRootPath() + "/cache/test.jpg")
                .build();
        Upload upload3 = new Upload.Builder()
                .key("title")
                .value("title")
                .build();
        Upload upload4 = new Upload.Builder()
                .key("detail")
                .value("detail")
                .build();
        List<Upload> uploadList = new ArrayList<>();
        uploadList.add(upload1);
        uploadList.add(upload2);
        uploadList.add(upload3);
        uploadList.add(upload4);

        downLoadApi
                .upload(RequestUtils.combine(uploadList), "608bb376-5baf-4828-b670-8d5fe8bc08a0")
                .compose(new ThreadTransFormer<ResponseBody>())
                .subscribe(new CommonSubscriber<ResponseBody>() {
                    @Override
                    public void deal(String message) {
                        LogUtils.d(TAG, message);
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            LogUtils.d(TAG, responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


}
