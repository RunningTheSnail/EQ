package me.danwi.t.eq.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by RunningSnail on 16/6/1.
 */
public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment {
    public String TAG = "";

    public BaseActivity activity;

    public T presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (BaseActivity) context;
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
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        TAG = this.getClass().getSimpleName();
        init();
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

    public abstract T initPresenter();

    //获取布局id
    public abstract int getLayoutId();

    public abstract void init();

}
