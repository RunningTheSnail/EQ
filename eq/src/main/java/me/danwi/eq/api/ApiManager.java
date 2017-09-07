package me.danwi.eq.api;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 17/9/6
 * Time: 上午11:32
 */
public interface ApiManager {

    @FormUrlEncoded
    @POST
    Observable<ResponseBody> postForm(@Url String url, @FieldMap Map<String, String> params);

    @POST
    @Multipart
    Observable<ResponseBody> postMultiPart(@Url String url, @PartMap Map<String, RequestBody> params);

    @GET
    Observable<ResponseBody> get(@Url String url, @QueryMap Map<String, String> params);

    @GET
    Observable<ResponseBody> get(@Url String url);
}
