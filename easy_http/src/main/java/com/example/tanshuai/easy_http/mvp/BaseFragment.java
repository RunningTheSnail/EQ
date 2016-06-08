package com.example.tanshuai.easy_http.mvp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by RunningSnail on 16/6/1.
 */
public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment {

    public T presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        presenter = initPresenter();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attach((V) this);
    }

    public abstract T initPresenter();

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.onDetach();
    }
}
