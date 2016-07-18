package com.example.tanshuai.eq.transform;

import android.text.TextUtils;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by RunningSnail on 16/7/11.
 * 表单数据校验
 */
public abstract class ValidFormTransFormer<T> implements Observable.Transformer<T, Boolean> {
    @Override
    public Observable<Boolean> call(Observable<T> stringObservable) {
        return stringObservable.flatMap(new Func1<T, Observable<Boolean>>() {
            @Override
            public Observable<Boolean> call(T s) {
                return Observable.just(!TextUtils.isEmpty(s.toString()) && valid(s.toString()));
            }
        });
    }

    //表单数据校验
    public abstract boolean valid(String value);

}
