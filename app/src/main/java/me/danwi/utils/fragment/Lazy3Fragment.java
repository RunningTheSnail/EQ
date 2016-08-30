package me.danwi.utils.fragment;

import android.os.Bundle;

import me.danwi.eq.mvp.BaseMVCFragment;
import me.danwi.utils.R;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/8/30
 * Time: 下午4:20
 */
public class Lazy3Fragment extends BaseMVCFragment {
    @Override
    public int getLayoutId() {
        return R.layout.test;
    }

    @Override
    public void init() {

    }

    @Override
    public boolean isAlone() {
        return true;
    }

    @Override
    public void getParams(Bundle bundle) {

    }
}
