package me.danwi.eq.subscriber;

import java.io.IOException;
import java.net.ConnectException;

import me.danwi.eq.utils.LogUtils;
import me.danwi.eq.utils.ToastHelper;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;

/**
 * Created with Android Studio.
 * User: 最帅最帅的RunningSnail
 * Date: 16/6/2
 * Time: 下午4:05
 * <p>
 * 封装Subscriber,对异常进行封装,统一处理(针对不同的Response可以定制不懂的转换规则)
 */
public abstract class CommonSubscriber<T> extends BaseSubscriber<T> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        if (e instanceof ConnectException) {
            dealException("服务器连接异常,请检查网络");
            return;
        }
        //通用异常
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            Response response = httpException.response();
            ResponseBody responseBody = response.errorBody();
            String message;
            try {
                message = responseBody.string();
                LogUtils.d(TAG, message);
            } catch (IOException io) {
                dealException(io.getMessage());
                return;
            }
            resp(response.code(), message);
        }
    }

    @Override
    public void onNext(T t) {

    }

    protected abstract void resp(int code, String message);

    //提供默认实现
    public void dealException(String message) {
        ToastHelper.showToast(message);
    }
}
