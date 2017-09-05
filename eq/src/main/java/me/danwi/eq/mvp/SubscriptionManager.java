package me.danwi.eq.mvp;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/8/29
 * Time: 上午10:42
 */
public class SubscriptionManager {

    private CompositeDisposable compositeDisposable;

    public SubscriptionManager() {
        compositeDisposable = new CompositeDisposable();
    }

    //取消所有订阅
    public void removeAllSubscription() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public void addSubscription(Disposable disposable) {
        if (compositeDisposable != null) {
            if (disposable != null) {
                compositeDisposable.add(disposable);
            }
        }
    }

    public void removeSubscription(Disposable disposable) {
        if (disposable != null && compositeDisposable != null) {
            compositeDisposable.remove(disposable);
        }
    }
}
