package me.danwi.eq.subscriber;

import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;

import io.reactivex.observers.DisposableObserver;
import me.danwi.eq.utils.ToastHelper;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 17/6/22
 * Time: 下午3:02
 */

public class ObserverAdapter<T> extends DisposableObserver<T> {

    @Override
    public void onNext(T value) {

    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof NoRouteToHostException) {
            ToastHelper.showToast("无法解析域名");
        } else if (e instanceof SocketTimeoutException) {
            ToastHelper.showToast("响应超时");
        } else if (e instanceof ConnectException) {
            ToastHelper.showToast("服务器连接超时");
        }
    }

    @Override
    public void onComplete() {

    }
}
