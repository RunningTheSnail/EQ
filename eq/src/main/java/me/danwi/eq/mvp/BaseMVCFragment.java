package me.danwi.eq.mvp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;

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

    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

    public Activity activity;

    protected SubscriptionManager subscriptionManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        subscriptionManager = new SubscriptionManager();
        Bundle bundle = getArguments();
        if (bundle != null) {
            getParams(bundle);
        }
        //当Fragment重建时state不为null,原理和Activity重建一样
        if (savedInstanceState != null) {
            boolean status = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            if (!status) {
                fragmentTransaction.show(this);
            } else {
                fragmentTransaction.hide(this);
            }
            fragmentTransaction.commit();
        }
        super.onCreate(savedInstanceState);
    }

    //设计成final禁止子类重写该方法
    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        isPrepare = true;
        //判断是否单独使用Fragment
        if (isAlone()) {
            //可视的
            isVisible = true;
        }
        lazyLoad();
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //状态恢复
        //通过isHidden()判断在当前状态是显示还是隐藏
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyView() {
//        LogUtils.d(TAG, "%s onDestroyView", TAG);
        //视图层级销毁了,表示需要重新加载数据?
        isLoad = false;

        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消订阅(取消所有异步任务)
        subscriptionManager.removeAllSubscription();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDetach() {
//        LogUtils.d(TAG, "%s onDetach", TAG);
        super.onDetach();
    }

    //单独的Fragment不会调用,配合ViewPager使用才会调用
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
//                LogUtils.d(TAG, "data load");
                isLoad = true;
                //加载数据
                init();
            }
        }
    }

    public void addSubscription(Disposable subscription) {
        subscriptionManager.addSubscription(subscription);
    }

    //获取布局id
    public abstract int getLayoutId();

    public abstract void init();

    //判断是否单独使用Fragment
    public abstract boolean isAlone();

    //获取从Activity传过来的参数
    public abstract void getParams(Bundle bundle);

}
