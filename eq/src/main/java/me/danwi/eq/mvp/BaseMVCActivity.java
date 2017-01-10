package me.danwi.eq.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
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
    protected SubscriptionManager subscriptionManager;

    //获取类名
    public String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (fullScreen()) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        if (loadLayout()) {
            setContentView(getLayoutId());
        }
        ButterKnife.bind(this);
        subscriptionManager = new SubscriptionManager();
        d(TAG, "进入了%s", TAG);
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

    //默认显示的Fragment
    public abstract Fragment defaultFragment();

    public boolean loadLayout() {
        return true;
    }

    public boolean fullScreen() {
        return false;
    }
}
