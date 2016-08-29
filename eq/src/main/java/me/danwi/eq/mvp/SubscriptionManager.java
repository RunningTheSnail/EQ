package me.danwi.eq.mvp;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/8/29
 * Time: 上午10:42
 */
public class SubscriptionManager {

    private CompositeSubscription compositeSubscription;

    public SubscriptionManager() {
        compositeSubscription = new CompositeSubscription();
    }

    //取消所有订阅
    public void removeAllSubscription() {
        if (compositeSubscription != null && compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
        }
    }

    public void addSubscription(Subscription subscription) {
        if (compositeSubscription != null) {
            if (subscription != null) {
                compositeSubscription.add(subscription);
            }
        }
    }
}
