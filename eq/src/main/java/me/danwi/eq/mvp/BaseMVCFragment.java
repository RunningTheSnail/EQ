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
 * Date: 16/8/29
 * Time: 下午3:32
 */
public abstract class BaseMVCFragment extends Fragment {
    //判断是否可视
    protected boolean isVisible;
    //判断Fragment是否初始化完成
    protected boolean isPrepare;
    //判断是否已经加载了数据
    protected boolean isLoad;
    //日志TAG
    public String TAG = this.getClass().getSimpleName();

    public Activity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
    }

    //设计成final禁止子类重写该方法
    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        isPrepare = true;
        lazyLoad();
        return view;
    }


    @Override
    public void onDestroyView() {
        isLoad = false;
        super.onDestroyView();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisible = isVisibleToUser;
        lazyLoad();
    }

    //懒加载
    public void lazyLoad() {
        if (isVisible && isPrepare) {
            if (!isLoad) {
                LogUtils.d(TAG, "data load");
                isLoad = true;
                //加载数据
                init();
            }
        }
    }

    //获取布局id
    public abstract int getLayoutId();

    public abstract void init();

}
