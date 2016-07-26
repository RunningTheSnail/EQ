package me.danwi.eq.api;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import rx.Observable;

/**
 * Created by RunningSnail on 16/7/21.
 */
public interface DownLoadApi {
    /**
     * 文件下载
     *
     * @param map 文件路径,由于服务器地址不确定所以外部控制
     * @return
     */
    @GET("http://video.wingamings.com/resources/video/5a57fa18-d1cf-4ed3-b8a0-7ae58b11ea24.mp4")
    @Streaming
    Observable<ResponseBody> download(@QueryMap Map<String, String> map);
}
