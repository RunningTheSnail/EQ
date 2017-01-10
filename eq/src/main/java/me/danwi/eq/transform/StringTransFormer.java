package me.danwi.eq.transform;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/12/22
 * Time: 下午4:53
 */
public class StringTransFormer implements Observable.Transformer<ResponseBody, String> {

    @Override
    public Observable<String> call(Observable<ResponseBody> responseBodyObservable) {
        return responseBodyObservable.flatMap(new Func1<ResponseBody, Observable<String>>() {
            @Override
            public Observable<String> call(ResponseBody responseBody) {
                try {
                    return Observable.just(responseBody.string());
                } catch (IOException e) {
                    return Observable.error(e);
                }
            }
        });
    }
}
