package me.danwi.utils.fragment;

import android.os.Bundle;

import me.danwi.eq.mvp.BaseMVCFragment;
import me.danwi.utils.R;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/8/29
 * Time: 下午3:31/
 * //
 */
public class LazyFragment extends BaseMVCFragment {
    private int index;

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public void init() {
//        LogUtils.d(TAG, index);
    }

    @Override
    public void getParams(Bundle bundle) {
        index = bundle.getInt("index");
    }

    @Override
    public boolean isAlone() {
        return false;
    }
}
