package me.danwi.eq.mvp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import me.danwi.eq.utils.LogUtils;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/6/1
 * Time: 下午3:18
 */
public abstract class BaseMVPFragment<V, T extends BasePresenter<V>> extends Fragment {
    //判断是否可视
    protected boolean isVisiable;
    //判断Fragment是否初始化完成
    protected boolean isPrepare;
    //判断是否已经加载了数据
    protected boolean isLoad;
    //日志TAG
    public String TAG = this.getClass().getSimpleName();

    public Activity activity;

    public T presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
    }

    //设计成final禁止子类重写该方法
    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtils.d(TAG, "onCreateView");
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        isPrepare = true;
        return view;
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
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        LogUtils.d(TAG, "setUserVisibleHint");
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint() && isPrepare) {
            if (!isLoad) {
                isLoad = true;
                init();
            }
        }
    }

    public abstract T initPresenter();

    //获取布局id
    public abstract int getLayoutId();

    public abstract void init();

}
