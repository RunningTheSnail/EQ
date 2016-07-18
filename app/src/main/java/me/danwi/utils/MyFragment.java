package me.danwi.utils;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by RunningSnail on 16/7/4.
 */
public class MyFragment extends Fragment {

    private Activity activity;

    private int index;

    @Override
    public void onAttach(Context context) {
        activity = (Activity) context;
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        show("onCreate");
        Bundle bundle = getArguments();
        if (bundle != null) {
            index = bundle.getInt("index");
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        show("onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        show("onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        show("onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        show("onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        show("onStop");
        super.onStop();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        show("onCreateView");

        View view = inflater.inflate(R.layout.item, container, false);
        return view;
    }

    @Override
    public void onDestroyView() {
        show("onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        show("onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        show("onDetach");
        super.onDetach();
    }

    public static MyFragment getInstance(int index) {
        MyFragment myFragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("index", index);
        myFragment.setArguments(bundle);
        return myFragment;
    }

    public void show(String message) {
        Log.i("MyFragment", message);
    }
}
