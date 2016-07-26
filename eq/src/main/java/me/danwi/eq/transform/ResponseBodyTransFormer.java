package me.danwi.eq.transform;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import me.danwi.eq.entity.DownLoadResult;
import me.danwi.eq.utils.LogUtils;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by RunningSnail on 16/7/21.
 */
public class ResponseBodyTransFormer implements Observable.Transformer<ResponseBody, DownLoadResult> {
    public static final String TAG = "ResponseBodyTransFormer";

    @Override
    public Observable<DownLoadResult> call(Observable<ResponseBody> responseBodyObservable) {
        return responseBodyObservable.flatMap(new Func1<ResponseBody, Observable<DownLoadResult>>() {
            @Override
            public Observable<DownLoadResult> call(final ResponseBody responseBody) {

                return Observable.create(new Observable.OnSubscribe<DownLoadResult>() {
                    @Override
                    public void call(Subscriber<? super DownLoadResult> subscriber) {
                        InputStream inputStream = responseBody.byteStream();
                        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                        DownLoadResult downLoadResult = new DownLoadResult();
                        byte[] bytes = new byte[1024];
                        downLoadResult.contentLength = responseBody.contentLength();
                        int total = 0;
                        int temp;
                        try {
                            while ((temp = bufferedInputStream.read(bytes)) != -1) {
                                total = total + temp;
                            }
                        } catch (IOException e) {
                            LogUtils.e(TAG, e.toString());
                        }
                    }
                });
            }
        });
    }
}
