package me.danwi.utils;

import java.util.List;
import java.util.Map;

import me.danwi.utils.entity.Video;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Streaming;
import rx.Observable;

/**
 * Created by RunningSnail on 16/7/21.
 */
public interface DownLoadApi {
    /**
     * 文件下载
     *
     * @return
     */
    @GET("http://p4.music.126.net/55a6eGUs2Amsju9vL9Y09Q==/1391981734063127.jpg")
    @Streaming
    Observable<ResponseBody> download();

    @Multipart
    @POST("http://sbuu.danwi.me/app_api/user/changeAvatar.do")
    Observable<ResponseBody> upload(@PartMap Map<String, RequestBody> params);

    @Headers({"cache-control:max-age=60"})
    @GET("http://119.9.68.36/admin/uploadSelftime.do")
    Observable<List<Video>> getAll(@Header("AUTH-TOKEN") String token, @PartMap Map<String, RequestBody> params);

//    Observable<ResponseBody> upload(@PartMap Map<String, RequestBody> params);
}
