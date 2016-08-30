package me.danwi.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.danwi.eq.mvp.BaseMVCActivity;
import me.danwi.utils.fragment.Lazy2Fragment;
import me.danwi.utils.fragment.Lazy3Fragment;
import me.danwi.utils.fragment.LazyFragment;

public class MainActivity extends BaseMVCActivity {

//    @BindView(R.id.vp)
//    ViewPager vp;

    @BindView(R.id.rl)
    FrameLayout rl;

    Button btn1, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rl = (FrameLayout) findViewById(R.id.fl);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        final List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new LazyFragment());
        fragmentList.add(new Lazy2Fragment());
        fragmentList.add(new LazyFragment());

        if (savedInstanceState != null) {

        } else {
            getSupportFragmentManager().beginTransaction().add(R.id.rl, new Lazy3Fragment()).commit();
        }

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().add(R.id.rl, fragmentList.get(0), "1").commit();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().add(R.id.rl, fragmentList.get(1)).commit();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = getSupportFragmentManager().findFragmentByTag("1");
                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction().show(fragment).commit();
                }
            }
        });


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
}
