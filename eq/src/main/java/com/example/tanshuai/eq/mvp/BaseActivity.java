package com.example.tanshuai.eq.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by RunningSnail on 16/6/1.
 */
public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {
    public T presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        presenter = initPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attach((V) this);
    }

    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }

    public abstract int getLayoutId();

    public abstract T initPresenter();

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }
}
