package me.danwi.utils.api;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by RunningSnail on 16/7/20.
 */
public interface BDApi {
    @GET("http://video.wingamings.com/resources/video/4d61aeb7-55b0-44c1-87c5-a06400a01b24.mp4")
    Observable<Void> get();
}
