package me.danwi.eq.core;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created with Android Studio.
 * User: 最帅最帅的RunningSnail
 * Date: 16/6/7
 * Time: 上午10:06
 */
public class RxBus {
    private static RxBus rxBus;

    private RxBus() {

    }

    public static RxBus getDefault() {
        if (rxBus == null) {
            synchronized (RxBus.class) {
                if (rxBus == null) {
                    rxBus = new RxBus();
                }
            }
        }
        return rxBus;
    }

    private Subject<Object, Object> subject = new SerializedSubject<>(PublishSubject.create());

    public void send(Object o) {
        subject.onNext(o);
    }

    public Observable<Object> toObservable() {
        return subject;
    }
}
