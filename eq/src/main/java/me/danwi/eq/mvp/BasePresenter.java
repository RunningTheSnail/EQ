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

    //BaseActivity在生命周期中自动调用
    public void attach(V view) {
        this.view = view;
    }

    public void onDetach() {
        this.view = null;
    }
}
