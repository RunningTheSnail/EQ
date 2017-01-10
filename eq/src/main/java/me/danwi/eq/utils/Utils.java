package me.danwi.eq.utils;

/**
 * Created with Android Studio.
 * User: 最帅最帅的RunningSnail
 * Date: 16/8/9
 * Time: 下午2:33
 */
public class Utils {

    // TODO: 16/8/9 日志记录
    private Utils() {

    }

    /**
     * 检查元素是否为空
     *
     * @param obj
     * @param message 提示消息
     */
    public static void checkNotNull(Object obj, String message) {
        if (obj == null) {
            throw new NullPointerException(message);
        }
    }

    public static void checkArgument(Object obj, String message) {
        if (obj == null) {
            throw new IllegalArgumentException(message);
        }
    }

}
