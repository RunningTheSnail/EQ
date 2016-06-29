package me.danwi.utils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.tanshuai.easy_http.utils.DesUtils;
import com.example.tanshuai.easy_http.utils.MD5Utils;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        String password = "r";
        String md5Password = MD5Utils.md5(password);
        System.out.println(md5Password);
        String des = DesUtils.encode("123456789", "hello");
        System.out.println(des);
        System.out.println(DesUtils.decode("123456789", des)+"----");
    }
}
