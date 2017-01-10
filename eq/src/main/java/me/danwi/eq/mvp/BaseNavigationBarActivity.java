package me.danwi.eq.mvp;

import android.os.Bundle;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/8/31
 * Time: 上午11:12
 * <p>
 * 具备底部导航栏逻辑的Activity
 */
public abstract class BaseNavigationBarActivity<V, T extends BasePresenter<V>> extends BaseMVPActivity<V, T> {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //容器
    public abstract int getContainer();
}
