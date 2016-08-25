package me.danwi.eq.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.ButterKnife;
import me.danwi.eq.utils.LogUtils;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/8/17
 * Time: 下午6:46
 */
public abstract class BaseMVCActivity extends AppCompatActivity {
    //获取类名
    public String TAG = this.getClass().getSimpleName();

    //子类不需要知道
    private CompositeSubscription compositeSubscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        compositeSubscription = new CompositeSubscription();
        LogUtils.d(TAG, "进入了%s", TAG);
    }

    @Override
    protected void onStop() {
        if (compositeSubscription != null && compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
        }
        super.onStop();
    }

    public void addSubscription(Subscription subscription) {
        if (compositeSubscription != null) {
            if (subscription != null) {
                compositeSubscription.add(subscription);
            }
        }
    }

    public String getValue(TextView tv) {
        return tv.getText().toString();
    }

    public abstract int getLayoutId();

}
