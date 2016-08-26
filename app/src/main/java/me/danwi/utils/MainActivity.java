package me.danwi.utils;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.danwi.eq.RequestUtils;
import me.danwi.eq.core.ServiceProducers;
import me.danwi.eq.entity.Param;
import me.danwi.eq.mvp.BaseMVCActivity;
import me.danwi.eq.subscriber.CommonSubscriber;
import me.danwi.eq.transform.ThreadTransFormer;
import me.danwi.eq.utils.LogUtils;
import okhttp3.ResponseBody;

public class MainActivity extends BaseMVCActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView imageView = (ImageView) findViewById(R.id.iv);

        DownLoadApi downLoadApi = ServiceProducers.createService(DownLoadApi.class);
        String path = Environment.getExternalStorageDirectory().getPath() + "/head.jpg";
        List<Param> paramList = new ArrayList<>();
        Param param1 = new Param.Builder().key("avatar").fileName("head.jpg").filePath(path).build();
        Param param2 = new Param.Builder().key("id").value("128e82e0-6b3f-11e6-8e89-433fe177ddc0").build();
//        Param param3 = new Param.Builder().key("detail").value("detail").build();
//        Param param4 = new Param.Builder().key("cover").fileName("test.jpg").filePath(path).build();
        paramList.add(param1);
//        paramList.add(param2);
//        paramList.add(param3);
//        paramList.add(param4);

        downLoadApi.upload(RequestUtils.combine(param1, param2))
                .compose(new ThreadTransFormer<ResponseBody>())
                .subscribe(new CommonSubscriber<ResponseBody>() {
                    @Override
                    public void deal(String message) {

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

//        final Api api = ServiceProducers.createService(Api.class);
        //断点续传
//        api.download("")
//                .flatMap(new Func1<ResponseBody, Observable<Long>>() {
//                    @Override
//                    public Observable<Long> call(ResponseBody responseBody) {
//                        Long length = responseBody.contentLength();
//                        responseBody.close();
//                        return Observable.just(length);
//                    }
//                })
//                .flatMap(new Func1<Long, Observable<DownLoadResult>>() {
//                    @Override
//                    public Observable<DownLoadResult> call(Long aLong) {
//                        long number = aLong / 3;
//                        Observable<DownLoadResult> o1 = api.download("bytes=" + 0 + "-" + (number - 1)).compose(new ResponseBodyTransFormer() {
//                            @Override
//                            public String getFolder() {
//                                return "java";
//                            }
//
//                            @Override
//                            public String getFileName() {
//                                return "test1.jpg";
//                            }
//                        }).subscribeOn(Schedulers.io());
//
//                        Observable<DownLoadResult> o2 = api.download("bytes=" + number + "-" + (2 * number - 1)).compose(new ResponseBodyTransFormer() {
//                            @Override
//                            public String getFolder() {
//                                return "java";
//                            }
//
//                            @Override
//                            public String getFileName() {
//                                return "test2.jpg";
//                            }
//                        }).subscribeOn(Schedulers.io());
//
//                        Observable<DownLoadResult> o3 = api.download("bytes=" + 2 * number + "-" + 3 * number)
//                                .compose(new ResponseBodyTransFormer() {
//                                    @Override
//                                    public String getFolder() {
//                                        return "java";
//                                    }
//
//                                    @Override
//                                    public String getFileName() {
//                                        return "test3.jpg";
//                                    }
//                                })
//                                .subscribeOn(Schedulers.io());
//
//                        return Observable.zip(o1, o2, o3, new Func3<DownLoadResult, DownLoadResult, DownLoadResult, DownLoadResult>() {
//                            @Override
//                            public DownLoadResult call(DownLoadResult downLoadResult, DownLoadResult downLoadResult2, DownLoadResult downLoadResult3) {
//                                DownLoadResult d = new DownLoadResult();
//                                LogUtils.d(TAG,downLoadResult.current);
//                                LogUtils.d(TAG,downLoadResult2.current);
//                                LogUtils.d(TAG,downLoadResult3.current);
//                                d.done = downLoadResult.done && downLoadResult2.done && downLoadResult3.done;
//                                d.current = downLoadResult.current + downLoadResult2.current + downLoadResult3.current;
//                                return d;
//                            }
//                        });
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
//                        LogUtils.d(TAG, downLoadResult.current);
//                        if (downLoadResult.done) {
//                            LogUtils.d(TAG, "success");
//                        }
//                    }
//                });

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
}
