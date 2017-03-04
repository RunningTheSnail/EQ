package me.danwi.eq.transform;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/12/22
 * Time: 下午4:53
 */
public class StringTransFormer implements ObservableTransformer<ResponseBody, String> {

    @Override
    public ObservableSource<String> apply(Observable<ResponseBody> upstream) {
        return upstream.flatMap(new Function<ResponseBody, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(ResponseBody responseBody) throws Exception {
                try {
                    return Observable.just(responseBody.string());
                } catch (IOException e) {
                    return Observable.error(e);
                }
            }
        });
    }
}
