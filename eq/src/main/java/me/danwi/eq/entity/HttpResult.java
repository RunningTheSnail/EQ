package me.danwi.eq.entity;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/7/29
 * Time: 下午3:14
 */
public class HttpResult<T> {
    public int code;
    //调用接口返回的信息,假装在message中
    public String message;
    public T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
