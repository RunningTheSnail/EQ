package me.danwi.eq.compat;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 17/1/4
 * Time: 下午2:54
 */
public class PrinterAdapter implements Printer {

    @Override
    public String defaultTag() {
        return null;
    }

    @Override
    public void init(String tag, boolean debug) {

    }

    @Override
    public void json(String json) {

    }

    @Override
    public void json(String tag, String json) {

    }

    @Override
    public void xml(String xml) {

    }

    @Override
    public void xml(String tag, String xml) {

    }

    @Override
    public void d(String message) {

    }

    @Override
    public void d(String tag, String message) {

    }

    @Override
    public void e(String message) {

    }

    @Override
    public void e(String tag, String message) {

    }
}
