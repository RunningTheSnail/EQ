package me.danwi.eq.transform;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import me.danwi.eq.entity.HttpResult;
import me.danwi.eq.utils.GsonUtils;
import okhttp3.ResponseBody;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 17/9/6
 * Time: 下午3:55
 */
public class RxTrans<T> implements ObservableTransformer<ResponseBody, T> {
    @Override
    public ObservableSource<T> apply(Observable<ResponseBody> upstream) {
        upstream.compose(new StringTransFormer())
                .flatMap(new Function<String, ObservableSource<HttpResult<T>>>() {
                    @Override
                    public ObservableSource<HttpResult<T>> apply(@NonNull String s) throws Exception {
                        return Observable.just(GsonUtils.toBean(s, HttpResult.class));
                    }
                });
        return null;
    }
}
