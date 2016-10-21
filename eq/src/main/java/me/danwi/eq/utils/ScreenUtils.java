package me.danwi.eq.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/6/1
 * Time: 下午3:21
 */
public class ScreenUtils {

    private ScreenUtils() {

    }

    public static float dpToPx(Context context, float dp) {
        return context.getResources().getDisplayMetrics().density * dp;
    }

    public static float pxToDp(Context context, float px) {
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static int dpToPxInt(Context context, float dp) {
        return (int) (dpToPx(context, dp) + 0.5f);
    }

    public static int pxToDpInt(Context context, float px) {
        return (int) (pxToDpInt(context, px) + 0.5f);
    }

    public static float getTotalWidthDp(Context context) {
        DisplayMetrics displayMetrics = getDisplayMetrics(context);
        return displayMetrics.widthPixels / displayMetrics.density;
    }

    public static float getTotalHeightDp(Context context) {
        DisplayMetrics displayMetrics = getDisplayMetrics(context);
        return displayMetrics.heightPixels / displayMetrics.density;
    }

    public static int getTotalWidthPx(Context context) {
        return getDisplayMetrics(context).widthPixels;
    }

    public static int getTotalHeightPx(Context context) {
        return getDisplayMetrics(context).heightPixels;
    }

    public static int getDensityDpi(Context context) {
        return getDisplayMetrics(context).densityDpi;
    }

    public static DisplayMetrics getDisplayMetrics(Context context) {
        return context.getResources().getDisplayMetrics();
    }
}
