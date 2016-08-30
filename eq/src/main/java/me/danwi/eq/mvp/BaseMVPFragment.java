package me.danwi.eq.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/6/1
 * Time: 下午3:18
 */
public abstract class BaseMVPFragment<V, T extends BasePresenter<V>> extends BaseMVCFragment {

    public T presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attach((V) this);
    }

    //销毁视图层级结构
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDetach();
        subscriptionManager.removeAllSubscription();
    }

    public abstract T initPresenter();

}
