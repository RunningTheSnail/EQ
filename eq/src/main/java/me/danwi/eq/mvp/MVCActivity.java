package me.danwi.eq.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/8/17
 * Time: 下午6:46
 */
public abstract class MVCActivity extends AppCompatActivity {
    public String TAG = this.getClass().getSimpleName();

    //子类不需要知道
    private CompositeSubscription compositeSubscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        compositeSubscription = new CompositeSubscription();
    }

    public void addSubscription(Subscription subscription) {
        if (compositeSubscription != null) {
            if (subscription != null) {
                compositeSubscription.add(subscription);
            }
        }
    }

    public abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (compositeSubscription != null && compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
        }
    }
}
