package me.danwi.eq.transform;

import android.text.TextUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * Created by RunningSnail on 16/7/11.
 * 表单数据校验
 */
public abstract   class ValidFormTransFormer<T> implements ObservableTransformer<T, Boolean> {

    //表单数据校验
    public abstract boolean valid(String value);

    @Override
    public ObservableSource<Boolean> apply(Observable<T> upstream) {
        return upstream.flatMap(new Function<T, ObservableSource<Boolean>>() {
            @Override
            public ObservableSource<Boolean> apply(T t) throws Exception {
                return Observable.just(!TextUtils.isEmpty(t.toString()) && valid(t.toString()));
            }
        });
    }
}
