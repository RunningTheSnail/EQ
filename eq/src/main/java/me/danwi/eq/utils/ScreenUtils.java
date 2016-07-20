package me.danwi.t.eq.utils;

import android.content.Context;

/**
 * Created by RunningSnail on 16/6/1.
 */
public class ScreenUtils {

    private ScreenUtils() {

    }
    public static float dpToPx(Context context, float dp) {
        checkNotNull(context);
        return context.getResources().getDisplayMetrics().density * dp;
    }

    public static float pxToDp(Context context, float px) {
        checkNotNull(context);
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static int dpToPxInt(Context context, float dp) {
        return (int) (dpToPx(context, dp) + 0.5f);
    }

    public static int pxToDpInt(Context context, float px) {
        return (int) (pxToDpInt(context, px) + 0.5f);
    }

    private static boolean checkNotNull(Context context) {
        if (context == null) {
            throw new NullPointerException("context不能为空");
        }
        return true;
    }
}
