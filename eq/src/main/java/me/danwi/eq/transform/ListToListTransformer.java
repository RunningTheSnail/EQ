package me.danwi.eq.transform;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/8/17
 * Time: 上午12:07
 */
public abstract class ListToListTransformer<T, R> implements Observable.Transformer<List<T>, List<R>> {

    @Override
    public Observable<List<R>> call(Observable<List<T>> listObservable) {
        return listObservable.flatMap(new Func1<List<T>, Observable<T>>() {
            @Override
            public Observable<T> call(List<T> ts) {
                return Observable.from(ts);
            }
        })
                .flatMap(new Func1<T, Observable<R>>() {
                    @Override
                    public Observable<R> call(T t) {
                        return Observable.just(trans(t));
                    }
                })
                .toList();
    }

    public abstract R trans(T t);

}
