package me.danwi.eq.compat;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 17/1/4
 * Time: 下午2:40
 */
public class LoggerPrinter extends PrinterAdapter {

    private static LoggerPrinter loggerPrinter;

    private LoggerPrinter(boolean debug) {
        init(defaultTag(), debug);
    }

    public static LoggerPrinter init(boolean debug) {
        if (loggerPrinter == null) {
            synchronized (LoggerPrinter.class) {
                if (loggerPrinter == null) {
                    loggerPrinter = new LoggerPrinter(debug);
                }
            }
        }
        return loggerPrinter;
    }

    public static LoggerPrinter getInstance() {
        return new LoggerPrinter(true);
    }

    @Override
    public String defaultTag() {
        return "EQ";
    }

    @Override
    public void init(String tag, boolean debug) {
        LogLevel logLevel = debug ? LogLevel.FULL : LogLevel.NONE;
        Logger.init(tag).hideThreadInfo().methodCount(0).logLevel(logLevel);
    }

    @Override
    public void json(String json) {
        Logger.json(json);
    }

    @Override
    public void xml(String xml) {
        Logger.xml(xml);
    }

    @Override
    public void d(String message) {
        Logger.d(message);
    }

    @Override
    public void e(String message) {
        Logger.e(message);
    }

    @Override
    public void xml(String tag, String xml) {
        Logger.t(tag).xml(xml);
    }

    @Override
    public void json(String tag, String json) {
        Logger.t(tag).json(json);
    }

    @Override
    public void d(String tag, String message) {
        Logger.t(tag).d(message);
    }

    @Override
    public void e(String tag, String message) {
        Logger.t(tag).e(message);
    }
}
