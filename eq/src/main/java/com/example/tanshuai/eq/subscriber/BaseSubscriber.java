package com.example.tanshuai.eq.subscriber;

import android.util.Log;

import rx.Subscriber;

/**
 * Created by RunningSnail on 16/7/12
 */
public class BaseSubscriber<T> extends Subscriber<T> {
    private static final String TAG = BaseSubscriber.class.getSimpleName();

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, e.toString());
    }

    @Override
    public void onNext(T t) {

    }
}
