package me.danwi.eq.entity;

/**
 * Created by RunningSnail on 16/7/29.
 */
public class BaseEntity<T> {
    public int code;
    //调用接口返回的信息,假装在message中
    public String message;
    public T data;
}
