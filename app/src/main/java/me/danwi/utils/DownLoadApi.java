package me.danwi.utils;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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
    @POST("http://192.9.68.36/admin/uploadSelftime.do")
    Observable<ResponseBody> upload(@Part MultipartBody.Part selfTimeVideo, @Part MultipartBody.Part cover, @Part("title") RequestBody title, @Part("detail") RequestBody detail, @Header("AUTH-TOKEN") String token);
}
