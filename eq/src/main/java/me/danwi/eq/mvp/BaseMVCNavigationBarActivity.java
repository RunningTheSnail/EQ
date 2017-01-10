package me.danwi.eq.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/12/15
 * Time: 下午8:01
 */
public abstract class BaseMVCNavigationBarActivity extends BaseMVCActivity {

    private List<Fragment> fragmentList;

    protected Fragment currentFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentList = new ArrayList<>();
    }


    protected void add(Fragment fragment) {
        fragmentList.add(fragment);
    }

    protected Fragment get(int position) {
        return fragmentList.get(position);
    }

    protected void replaceFragment(Fragment src, Fragment des) {
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
