package me.danwi.eq.subscriber;

import com.orhanobut.logger.Logger;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 17/6/22
 * Time: 下午3:02
 */
public class ObserverAdapter<T> implements Observer<T> {


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T value) {

    }

    @Override
    public void onError(Throwable e) {
        Logger.e(e.toString());
    }

    @Override
    public void onComplete() {

    }
}
