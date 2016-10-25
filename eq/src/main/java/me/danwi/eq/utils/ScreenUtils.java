package me.danwi.eq.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

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

    public static int getTotalWidthDpToInt(Context context) {
        return (int) getTotalWidthDp(context);
    }

    public static int getTotalHeightDpToInt(Context context) {
        return (int) getTotalHeightDp(context);
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

    /**
     * 获取状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        try {
            int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = context.getResources().getDimensionPixelSize(resourceId);
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 获取屏幕的宽高
     *
     * @param context
     * @return
     */
    public static int[] getScreenSize(Context context) {

        int[] size = new int[2];

        WindowManager w = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display d = w.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        d.getMetrics(metrics);
// since SDK_INT = 1;
        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;

// includes window decorations (statusbar bar/menu bar)
        if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17)
            try {
                widthPixels = (Integer) Display.class.getMethod("getRawWidth").invoke(d);
                heightPixels = (Integer) Display.class.getMethod("getRawHeight").invoke(d);
            } catch (Exception ignored) {
            }
// includes window decorations (statusbar bar/menu bar)
        if (Build.VERSION.SDK_INT >= 17)
            try {
                Point realSize = new Point();
                d.getRealSize(realSize);
                widthPixels = realSize.x;
                heightPixels = realSize.y;
            } catch (Exception ignored) {
            }
        size[0] = widthPixels;
        size[1] = heightPixels;
        return size;
    }


}
