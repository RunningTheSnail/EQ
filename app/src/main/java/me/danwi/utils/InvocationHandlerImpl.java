package me.danwi.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by RunningSnail on 16/6/12.
 */
public class InvocationHandlerImpl implements InvocationHandler {
//    private Object object;
//
//    public InvocationHandlerImpl(Object object) {
//        this.object = object;
//    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("start");
        return this;
    }
}
