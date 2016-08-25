package me.danwi.eq.mvp;

import android.os.Bundle;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/6/1
 * Time: 下午3:18
 */
public abstract class BaseMVPActivity<V, T extends BasePresenter<V>> extends BaseMVCActivity {

    //一个Activity对应一个Presenter
    public T presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter != null) {
            presenter.attach((V) this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.onDetach();
        }
    }

    public abstract int getLayoutId();

    public abstract T initPresenter();


}
