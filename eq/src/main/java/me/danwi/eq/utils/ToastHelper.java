package me.danwi.eq.utils;

import android.content.Context;
import android.widget.Toast;

import me.danwi.eq.AppManager;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/6/18
 * Time: 上午9:39
 */
public class ToastHelper {

    private static Toast mToast;

    private ToastHelper() {

    }

    public static Context getCurrentContext() {
        return AppManager.getAppManager().currentContext();
    }

    public static Toast getToast(int resId) {
        return Toast.makeText(getCurrentContext(), resId, Toast.LENGTH_SHORT);
    }

    public static Toast getToast(String text) {
        return Toast.makeText(getCurrentContext(), text, Toast.LENGTH_SHORT);
    }

    public static Toast getLongToast(int resId) {
        return Toast.makeText(getCurrentContext(), resId, Toast.LENGTH_LONG);
    }

    public static Toast getLongToast(String text) {
        return Toast.makeText(getCurrentContext(), text, Toast.LENGTH_LONG);
    }

    public static Toast getSingletonToast(int resId) {
        if (mToast == null) {
            mToast = getToast(resId);
        } else {
            mToast.setText(resId);
        }
        return mToast;
    }

    public static Toast getSingletonToast(String text) {
        if (mToast == null) {
            mToast = getToast(text);
        } else {
            mToast.setText(text);
        }
        return mToast;
    }

    public static Toast getSingleLongToast(int resId) {
        if (mToast == null) {
            mToast = getLongToast(resId);
        } else {
            mToast.setText(resId);
        }
        return mToast;
    }

    public static Toast getSingleLongToast(String text) {
        if (mToast == null) {
            mToast = getLongToast(text);
        } else {
            mToast.setText(text);
        }
        return mToast;
    }


    public static void showSingletonToast(int resId) {
        getSingletonToast(resId).show();
    }


    public static void showSingletonToast(String text) {
        getSingletonToast(text).show();
    }

    public static void showSingleLongToast(int resId) {
        getSingleLongToast(resId).show();
    }


    public static void showSingleLongToast(String text) {
        getSingleLongToast(text).show();
    }

    public static void showToast(int resId) {
        getToast(resId).show();
    }

    public static void showToast(String text) {
        getToast(text).show();
    }

    public static void showLongToast(int resId) {
        getLongToast(resId).show();
    }

    public static void showLongToast(String text) {
        getLongToast(text).show();
    }

}
