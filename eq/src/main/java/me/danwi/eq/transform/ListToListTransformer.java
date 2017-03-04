package me.danwi.eq.transform;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/8/17
 * Time: 上午12:07
 */
public abstract class ListToListTransformer<T, R> implements ObservableTransformer<List<T>, List<R>> {

    public abstract R trans(T t);

    @Override
    public ObservableSource<List<R>> apply(Observable<List<T>> upstream) {
//        return upstream.flatMap(new Function<List<T>, ObservableSource<T>>() {
//            @Override
//            public ObservableSource<T> apply(List<T> ts) throws Exception {
//                return Observable.fromIterable(ts);
//
//            }
//        })
//                .flatMap(new Function<T, ObservableSource<R>>() {
//                    @Override
//                    public ObservableSource<R> apply(T t) throws Exception {
//                        return Observable.just(trans(t));
//                    }
//
//                });
        return null;
    }
}
