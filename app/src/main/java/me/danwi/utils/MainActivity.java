package me.danwi.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.danwi.eq.mvp.BaseMVCActivity;
import me.danwi.eq.utils.LogUtils;
import me.danwi.utils.fragment.LazyFragment;

public class MainActivity extends BaseMVCActivity {

    @BindView(R.id.vp)
    ViewPager vp;

    @BindView(R.id.rl)
    RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        vp = (ViewPager) findViewById(R.id.vp);

        final List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new LazyFragment());
        fragmentList.add(new LazyFragment());
        fragmentList.add(new LazyFragment());
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                LogUtils.d(TAG, position);
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
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
