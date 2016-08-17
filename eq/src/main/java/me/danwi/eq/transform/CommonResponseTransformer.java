package me.danwi.eq.transform;

import me.danwi.eq.entity.BaseEntity;
import me.danwi.eq.exception.BizException;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by RunningSnail on 16/7/29.
 * 普通的请求结果转换
 */
public class CommonResponseTransformer<T> implements Observable.Transformer<BaseEntity<T>, T> {

    @Override
    public Observable<T> call(Observable<BaseEntity<T>> baseEntityObservable) {
        return baseEntityObservable.flatMap(new Func1<BaseEntity<T>, Observable<T>>() {
            @Override
            public Observable<T> call(BaseEntity<T> tBaseEntity) {
                int code = tBaseEntity.code;
                //判断结果码
                if (code == 200) {
                    return Observable.just(tBaseEntity.data);
                }
                //抛出业务异常
                return Observable.error(new BizException(tBaseEntity.message));
            }
        });
    }
}
