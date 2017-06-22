package me.danwi.eq.subscriber;

import com.google.gson.Gson;

import me.danwi.eq.entity.ErrorMessage;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 17/1/18
 * Time: 上午10:41
 */
public class EasySubscriber extends CommonSubscriber {

    @Override
    protected void resp(int code, String message) {
        //其他小伙伴可以根据自己的业务进行分析
        //针对自家业务解析
        if (code == 500) {
            ErrorMessage errorMessage = new Gson().fromJson(message, ErrorMessage.class);
            //如果服务器黑了,并不是正常返回
            if (errorMessage != null) {
                if ("10000".equals(errorMessage.code)) {

                } else {

                }
                dealException(errorMessage.message);
            }
        }

        //没有网络,缓存又过期
        if (code == 504) {
            dealException("服务器连接异常,请检查网络");
        }
    }
}
