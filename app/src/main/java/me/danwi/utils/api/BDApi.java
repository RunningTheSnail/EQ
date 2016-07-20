package me.danwi.utils.api;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by RunningSnail on 16/7/20.
 */
public interface BDApi {
    @GET("/")
    Observable<Void> get();
}
