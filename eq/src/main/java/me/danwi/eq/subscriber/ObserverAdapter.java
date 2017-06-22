package me.danwi.eq.subscriber;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.danwi.eq.mvp.SubscriptionManager;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 17/6/22
 * Time: 下午3:02
 */
public class ObserverAdapter<T> implements Observer<T> {

    private SubscriptionManager subscriptionManager;

    private Disposable disposable;

    public ObserverAdapter(SubscriptionManager subscriptionManager) {
        this.subscriptionManager = subscriptionManager;
    }


    @Override
    public void onSubscribe(Disposable d) {
        this.disposable = d;
        subscriptionManager.addSubscription(d);
    }

    @Override
    public void onNext(T value) {

    }

    @Override
    public void onError(Throwable e) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    public void onComplete() {

    }
}
