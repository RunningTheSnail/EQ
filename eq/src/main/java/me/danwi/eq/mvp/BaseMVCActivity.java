package me.danwi.eq.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.ButterKnife;
import me.danwi.eq.utils.LogUtils;
import rx.Subscription;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/8/17
 * Time: 下午6:46
 */
public abstract class BaseMVCActivity extends AppCompatActivity {
    private SubscriptionManager subscriptionManager;

    //获取类名
    public String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        subscriptionManager = new SubscriptionManager();
        LogUtils.d(TAG, "进入了%s", TAG);
    }

    @Override
    protected void onStop() {
        if (subscriptionManager != null) {
            subscriptionManager.removeAllSubscription();
        }
        super.onStop();
    }

    public void addSubscription(Subscription subscription) {
        subscriptionManager.addSubscription(subscription);
    }

    public String getValue(TextView tv) {
        return tv.getText().toString();
    }

    public void d(String message, Object... object) {
        LogUtils.d(TAG, message, object);
    }

    public void d(String message) {
        LogUtils.d(TAG, message);
    }


    //模板设计模式哦~
    public abstract int getLayoutId();

}
