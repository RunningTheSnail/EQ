package me.danwi.eq.utils;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import me.danwi.eq.BuildConfig;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 17/7/31
 * Time: 下午4:43
 */
public class LogUtils {


    public static void init() {
        init("");
    }

    public static void init(String tag) {
        Logger.clearLogAdapters();
        FormatStrategy prettyFormatStrategy = "".equals(tag) ? PrettyFormatStrategy.newBuilder().build() : PrettyFormatStrategy.newBuilder().tag(tag).build();
        Logger.addLogAdapter(new AndroidLogAdapter(prettyFormatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }
}
