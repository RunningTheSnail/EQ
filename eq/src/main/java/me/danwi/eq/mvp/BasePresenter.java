package me.danwi.eq.mvp;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/6/1
 * Time: 下午3:18
 */
public abstract class BasePresenter<V> {
    //V和P对应
    public V view;

    private SubscriptionManager subscriptionManager;

    public BasePresenter() {
        subscriptionManager = new SubscriptionManager();
    }


    //BaseActivity在生命周期中自动调用
    public void attach(V view) {
        this.view = view;
    }

    public void onDetach() {
        if (subscriptionManager != null) {
            subscriptionManager.removeAllSubscription();
        }
        this.view = null;
    }
}
