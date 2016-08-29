package me.danwi.eq.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/6/1
 * Time: 下午3:18
 */
public abstract class BaseMVPActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {

    //一个View对应一个Presenter
    public T presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
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
