package me.danwi.eq.utils;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import me.danwi.eq.compat.Printer;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/7/19
 * Time: 下午2:35
 */
public class LogUtils {

    public static Printer printer;

    public static void init(Printer printer, boolean debug) {

    }

    public static void init(String tag, boolean debug) {
        init(tag, debug, 0);
    }

    public static void init(String tag, boolean debug, int count) {
        //默认隐藏线程信息
        LogLevel logLevel = debug ? LogLevel.FULL : LogLevel.NONE;
        Logger.init(tag).hideThreadInfo().methodCount(count).logLevel(logLevel);
    }


    public static void d(String tag, String message, Object... objects) {
        Logger.t(tag).d(message, objects);
    }

    /**
     * debug日志输出普通信息,输出被调用在那个方法
     *
     * @param tag—
     * @param message
     */
    public static void d(String tag, String message) {
        Logger.t(tag).d(message);
    }

    /**
     * Array, Map, Set and List are supported
     *
     * @param tag
     * @param object
     */
    public static void d(String tag, Object object) {
        Logger.t(tag).d(object);
    }


    /**
     * 错误日志输出
     *
     * @param tag
     * @param message
     */
    public static void e(String tag, String message) {
        Logger.t(tag).e(message);
    }

    public static void e(String tag, Throwable throwable, String message, Object... objects) {
        Logger.t(tag).e(throwable, message, objects);
    }


    public static void xml(String tag, String message) {
        Logger.t(tag).xml(message);
    }

    public static void json(String tag, String message) {
        Logger.t(tag).json(message);
    }
}
