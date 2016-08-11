package me.danwi.eq.mvp;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/6/1
 * Time: 下午3:18
 */
public abstract class BasePresenter<V> {
    public CompositeSubscription compositeSubscription;

    public void addSubscription(Subscription subscription) {
        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        if (subscription != null) {
            compositeSubscription.add(subscription);
        }
    }

    //V和P对应
    public V view;

    //BaseActivity在生命周期中自动调用
    public void attach(V view) {
        this.view = view;
    }

    public void onDetach() {
        if (compositeSubscription != null) {
            compositeSubscription.unsubscribe();
        }
        this.view = null;
    }
}
