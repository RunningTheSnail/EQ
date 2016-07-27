package me.danwi.utils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.io.File;
import java.io.IOException;

import me.danwi.eq.core.ServiceProducers;
import me.danwi.eq.progress.ProgressListener;
import me.danwi.eq.progress.ProgressRequestBody;
import me.danwi.eq.subscriber.CommonSubscriber;
import me.danwi.eq.transform.ThreadTransFormer;
import me.danwi.eq.utils.LogUtils;
import me.danwi.eq.utils.SdCardUtils;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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

//        downLoadApi.download()
//                .compose(new ResponseBodyTransFormer() {
//                    @Override
//                    public String getFolder() {
//                        return "cache";
//                    }
//
//                    @Override
//                    public String getFileName() {
//                        return "test.jpg";
//                    }
//                })
//                .compose(new ThreadTransFormer<DownLoadResult>())
//                .subscribe(new CommonSubscriber<DownLoadResult>() {
//                    @Override
//                    public void deal(String message) {
//
//                    }
//
//                    @Override
//                    public void onNext(DownLoadResult downLoadResult) {
//                        LogUtils.d(TAG, downLoadResult);
//                    }
//                });

        File file = new File(SdCardUtils.getRootPath() + "/cache", "test.jpg");

        ProgressRequestBody progressRequestBody1 = new ProgressRequestBody(RequestBody.create(MultipartBody.FORM, file), new ProgressListener() {
            @Override
            public void update(long bytes, long contentLength, boolean done) {
                LogUtils.d(TAG, bytes + "-----" + contentLength + "-----" + done);
            }
        });

        ProgressRequestBody progressRequestBody2 = new ProgressRequestBody(RequestBody.create(MultipartBody.FORM, file), new ProgressListener() {
            @Override
            public void update(long bytes, long contentLength, boolean done) {
                LogUtils.d(TAG, bytes + "-----" + contentLength + "-----" + done);
            }
        });
        MultipartBody.Part selfTimeVideo = MultipartBody.Part.createFormData("selftimeVideo", "test.jpg", progressRequestBody1);
        MultipartBody.Part cover = MultipartBody.Part.createFormData("cover", "test.jpg", progressRequestBody2);
        RequestBody title = RequestBody.create(null, "title");
        RequestBody detail = RequestBody.create(null, "detail");


        downLoadApi
                .upload(selfTimeVideo,cover , title, detail, "608bb376-5baf-4828-b670-8d5fe8bc08a0")
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
