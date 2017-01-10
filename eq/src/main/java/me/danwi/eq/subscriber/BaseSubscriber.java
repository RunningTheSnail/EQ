package me.danwi.eq.subscriber;

import me.danwi.eq.utils.LogUtils;
import rx.Subscriber;

/**
 * Created by RunningSnail on 16/7/12
 * 适配器模式
 */
public class BaseSubscriber<T> extends Subscriber<T> {
    public String TAG = this.getClass().getSimpleName();

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        LogUtils.e(TAG, e.toString());
    }

    @Override
    public void onNext(T t) {

    }
}
