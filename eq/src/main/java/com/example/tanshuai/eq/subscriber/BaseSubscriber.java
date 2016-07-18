package com.example.tanshuai.eq.subscriber;

import rx.Subscriber;

/**
 * Created by RunningSnail on 16/7/12
 */
public class BaseSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T t) {

    }
}
