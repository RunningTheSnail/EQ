package me.danwi.eq;

import rx.Observable;
import rx.Subscriber;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/8/16
 * Time: 下午11:43
 */
public abstract class CommonOnSubscribe<T> implements Observable.OnSubscribe<T> {

    @Override
    public void call(Subscriber<? super T> subscriber) {
        //判断是否取消订阅
        if (!subscriber.isUnsubscribed()) {
            work(subscriber);
        }
    }

    public abstract void work(Subscriber<? super T> subscriber);
}
