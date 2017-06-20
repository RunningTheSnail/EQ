package me.danwi.eq.subscriber;

import io.reactivex.functions.Consumer;

/**
 * Created by RunningSnail on 16/7/12
 */
public abstract class BaseConsumerThrowable implements Consumer<Throwable> {

    public String TAG = this.getClass().getSimpleName();

    @Override
    public void accept(Throwable throwable) throws Exception {
        //// TODO: 17/3/4 日志记录 
    }
}
