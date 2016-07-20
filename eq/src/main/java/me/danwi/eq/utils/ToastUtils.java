package me.danwi.eq.utils;

import android.widget.Toast;

import me.danwi.eq.EasyApplication;

/**
 * Created by RunningSnail on 16/6/18.
 */
public class ToastUtils {

    private ToastUtils() {

    }

    public static void showMessage(String message) {
        showMessage(message, 0);
    }

    public static void showMessage(String message, int time) {
        int duration = Toast.LENGTH_SHORT;
        if (time != 0) {
            duration = Toast.LENGTH_LONG;
        }
        Toast.makeText(EasyApplication.getContext(), message, duration).show();
    }
}
