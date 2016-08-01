package me.danwi.eq.entity;

/**
 * Created with Android Studio.
 * User: 最帅最帅的RunningSnail
 * Date: 16/6/2
 * Time: 下午4:34
 * <p/>
 * 业务异常错误信息
 */
public class ErrorMessage {
    public String code;
    public String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
