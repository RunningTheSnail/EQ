package me.danwi.eq.entity;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/7/29
 * Time: 下午3:14
 */
public class BaseEntity<T> {
    public int code;
    //调用接口返回的信息,假装在message中
    public String message;
    public T data;
}
