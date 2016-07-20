package me.danwi.eq.subscriber;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.ConnectException;

import me.danwi.eq.entity.ErrorMessage;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by RunningSnail on 16/6/2.
 * 封装Subscriber,对异常进行封装,统一处理(针对不同的Response可以定制不懂的转换规则)
 */
public abstract class CommonSubscriber<T> extends BaseSubscriber<T> {
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ConnectException) {
            deal("服务器连接异常,请检查网络");
            return;
        }
        //通用异常
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ResponseBody responseBody = httpException.response().errorBody();
            try {
                ErrorMessage errorMessage = new Gson().fromJson(responseBody.string(), ErrorMessage.class);
                if ("10000".equals(errorMessage.code)) {

                } else {

                }
                deal(errorMessage.message);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return;
        }
        deal(e.getMessage());
    }

    @Override
    public void onNext(T t) {

    }

    public abstract void deal(String message);
}
