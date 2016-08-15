package me.danwi.utils;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Streaming;
import rx.Observable;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/8/12
 * Time: 下午2:37
 */
public interface Api {
    @GET("http://192.168.253.133:3001/resources/video/1018cf98-721a-4c4c-ad7a-e9e160dc09fb.mp4")
    @Streaming
    Observable<ResponseBody> download(@Header("Range") String range);
}
