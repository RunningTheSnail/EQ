package me.danwi.eq.compat;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 17/1/4
 * Time: 下午2:36
 */
public interface Printer {

    String defaultTag();

    //初始化标记
    void init(String tag, boolean debug);

    //输出json
    void json(String json);

    void json(String tag, String json);

    void xml(String xml);

    void xml(String tag, String xml);

    void d(String message);

    void d(String tag, String message);

    void e(String message);

    void e(String tag, String message);

}
