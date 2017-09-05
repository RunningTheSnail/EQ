package me.danwi.eq.entity;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/7/29
 * Time: 下午3:14
 */
public class HttpResult<T> {
    public int result;
    //调用接口返回的信息,假装在message中
    public String msg;
    public String statusCode;
    public T data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "result=" + result +
                ", msg='" + msg + '\'' +
                ", statusCode='" + statusCode + '\'' +
                ", data=" + data +
                '}';
    }
}
