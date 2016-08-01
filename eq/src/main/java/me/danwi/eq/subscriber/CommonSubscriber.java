package me.danwi.eq.subscriber;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.ConnectException;

import me.danwi.eq.entity.ErrorMessage;
import me.danwi.eq.utils.LogUtils;
import okhttp3.ResponseBody;
import retrofit2.Response;
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
        //统一错误输出
        LogUtils.e(TAG, e.toString());
        if (e instanceof ConnectException) {
            deal("服务器连接异常,请检查网络");
            return;
        }
        //通用异常
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            Response response = httpException.response();
            ResponseBody responseBody = response.errorBody();
            //针对自家业务解析
            if (response.code() == 500) {
                try {
                    ErrorMessage errorMessage = new Gson().fromJson(responseBody.string(), ErrorMessage.class);
                    //如果服务器黑了,并不是正常返回
                    if (errorMessage != null) {
                        if ("10000".equals(errorMessage.code)) {

                        } else {

                        }
                        deal(errorMessage.message);
                        return;
                    }
                } catch (IOException e1) {
                    //忽略
                }
            }
        }
        deal(e.toString());
    }

    @Override
    public void onNext(T t) {

    }

    public abstract void deal(String message);
}
