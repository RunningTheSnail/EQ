package me.danwi.utils;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
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
    @GET("http://video.wingamings.com/resources/video/4d61aeb7-55b0-44c1-87c5-a06400a01b24.mp4")
    @Streaming
    Observable<ResponseBody> download();
}
