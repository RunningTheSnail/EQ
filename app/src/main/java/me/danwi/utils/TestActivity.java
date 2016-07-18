package me.danwi.utils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Button button = (Button) findViewById(R.id.btn);
        Button button1 = (Button) findViewById(R.id.btn1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().detach(getFragmentManager().findFragmentByTag("MyFragment")).commit();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().attach(getFragmentManager().findFragmentByTag("MyFragment")).commit();
            }
        });
        if (savedInstanceState != null) {
            MyFragment myFragment = (MyFragment) getFragmentManager().findFragmentByTag("MyFragment");
            getFragmentManager().beginTransaction().show(myFragment).commit();
        } else {
            getFragmentManager().beginTransaction().add(R.id.fl, MyFragment.getInstance(1), "MyFragment").addToBackStack(null).commit();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
