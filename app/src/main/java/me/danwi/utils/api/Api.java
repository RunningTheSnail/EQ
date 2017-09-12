package me.danwi.utils.api;

import io.reactivex.Observable;
import me.danwi.eq.EQ;
import retrofit2.http.GET;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 17/9/12
 * Time: 下午2:40
 */
public interface Api {
    @EQ
    @GET("https://www.baidu.com")
    Observable<Empty> get();
}
