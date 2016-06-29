package com.example.tanshuai.easy_http.mvp;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by RunningSnail on 16/6/1.
 */
public abstract class BasePresenter<T> {
    public CompositeSubscription compositeSubscription;

    public void addSubscription(Subscription subscription) {
        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        if (subscription != null) {
            compositeSubscription.add(subscription);
        }
    }

    public T view;

    public void attach(T view) {
        this.view = view;
    }

    public void onDetach() {
        this.view = null;
        if (compositeSubscription != null) {
            compositeSubscription.unsubscribe();
        }
    }
}
