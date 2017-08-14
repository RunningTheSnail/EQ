package me.danwi.utils;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import me.danwi.eq.CommonOnSubscribe;
import me.danwi.eq.mvp.BaseMVCActivity;
import me.danwi.eq.subscriber.ObserverAdapter;
import me.danwi.utils.service.FileDownService;

public class MainActivity extends BaseMVCActivity {

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            FileDownService.ExecuteTaskBinder executeTaskBinder = (FileDownService.ExecuteTaskBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        bindService(new Intent(this, FileDownService.class), serviceConnection, BIND_AUTO_CREATE);
        Observable.create(new CommonOnSubscribe<String>() {
            @Override
            public void work(ObservableEmitter<String> e) {

            }
        }).subscribe(new ObserverAdapter<String>(subscriptionManager));

        Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {

            }
        })
//        rl.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                getSupportFragmentManager().beginTransaction().add(R.id.rl, fragmentList.get(1)).commit();
//            }
//        }, 3000);

//        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
//            @Override
//            public Fragment getItem(int position) {
//                LogUtils.d(TAG, position);
//                return fragmentList.get(position);
//            }
//
//            @Override
//            public int getCount() {
//                return fragmentList.size();
//            }
//        });

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

    @Override
    public android.support.v4.app.Fragment defaultFragment() {
        return null;
    }
}
