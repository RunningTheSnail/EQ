package me.danwi.eq.mvp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/8/31
 * Time: 上午11:12
 * <p>
 * 具备底部导航栏逻辑的Activity
 */
public abstract class BaseMainActivity<V, T extends BasePresenter<V>> extends BaseMVPActivity<V, T> {

    private List<Fragment> fragmentList;

    //当前的Fragment
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentList = new ArrayList<>();
    }

    public void add(Fragment fragment) {
        fragmentList.add(fragment);
    }

    public Fragment get(int position) {
        return fragmentList.get(position);
    }

    private void replaceFragment(Fragment src, Fragment des) {
        if (src != des) {
            currentFragment = des;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            //已经添加
            if (des.isAdded()) {
                transaction.hide(src).show(des).commit();
            } else {
                //未添加
                transaction.add(getContainer(), des, des.getClass().getName()).hide(src).commit();
            }
        }
    }

    //容器
    public abstract int getContainer();
}
