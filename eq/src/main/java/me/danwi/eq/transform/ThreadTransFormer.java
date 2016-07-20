package me.danwi.eq.transform;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by RunningSnail on 16/7/8.
 * Rx线程切换
 */
public class ThreadTransFormer<T> implements Observable.Transformer<T, T> {

    @Override
    public Observable<T> call(Observable<T> tObservable) {
        return tObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
