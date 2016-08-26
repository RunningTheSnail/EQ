package me.danwi.eq.transform;

import me.danwi.eq.entity.HttpResult;
import me.danwi.eq.exception.BizException;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by RunningSnail on 16/7/29.
 * 普通的请求结果转换
 */
public class CommonResponseTransformer<T> implements Observable.Transformer<HttpResult<T>, T> {

    @Override
    public Observable<T> call(Observable<HttpResult<T>> baseEntityObservable) {
        return baseEntityObservable.flatMap(new Func1<HttpResult<T>, Observable<T>>() {
            @Override
            public Observable<T> call(HttpResult<T> tHttpResult) {
                int code = tHttpResult.code;
                //判断结果码
                if (code == 200) {
                    return Observable.just(tHttpResult.data);
                }
                //抛出业务异常
                return Observable.error(new BizException(tHttpResult.message));
            }
        });
    }
}
