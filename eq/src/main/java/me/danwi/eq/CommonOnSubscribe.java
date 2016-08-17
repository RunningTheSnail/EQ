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
        //判断是否取消订阅,使用create操作符创建Observable必须自己判断订阅是否取消
        //否则即使调用了subscription.unSubscribe()还会继续接受事件
        if (!subscriber.isUnsubscribed()) {
            work(subscriber);
        }
    }

    public abstract void work(Subscriber<? super T> subscriber);
}
