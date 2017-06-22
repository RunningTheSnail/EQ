package me.danwi.eq;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/8/16
 * Time: 下午11:43
 */
public abstract class CommonOnSubscribe<T> implements ObservableOnSubscribe<T> {

    public abstract void work(ObservableEmitter<T> e);

    @Override
    public void subscribe(ObservableEmitter<T> e) throws Exception {
        if (!e.isDisposed()) {
            work(e);
        }
    }
}
