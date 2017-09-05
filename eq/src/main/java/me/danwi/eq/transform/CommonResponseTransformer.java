package me.danwi.eq.transform;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;
import me.danwi.eq.entity.HttpResult;
import me.danwi.eq.exception.BizException;

/**
 * Created by RunningSnail on 16/7/29.
 * 普通的请求结果转换
 */
public class CommonResponseTransformer<T> implements ObservableTransformer<HttpResult<T>, T> {

    @Override
    public ObservableSource<T> apply(Observable<HttpResult<T>> upstream) {

        return upstream.flatMap(new Function<HttpResult<T>, ObservableSource<T>>() {
            @Override
            public ObservableSource<T> apply(HttpResult<T> tHttpResult) throws Exception {
                int result = tHttpResult.result;
                //判断结果码
                if (result == 1) {
                    return Observable.just(tHttpResult.data);
                }
                //抛出业务异常
                return Observable.error(new BizException(tHttpResult.msg));
            }

        });
    }
}
