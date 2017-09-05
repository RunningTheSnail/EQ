package me.danwi.eq.progress;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/1/20
 * Time: 下午3:18
 */
public class ProgressRequestBody extends RequestBody {
    public static final String TAG = "ProgressRequestBody";
    private RequestBody requestBody;
    private ProgressListener progressListener;

    public ProgressRequestBody(RequestBody requestBody, ProgressListener progressListener) {
        this.requestBody = requestBody;
        this.progressListener = progressListener;
    }

    @Override
    public MediaType contentType() {
        return requestBody.contentType();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        BufferedSink bufferedSink = Okio.buffer(sink(sink));
        //写入
        requestBody.writeTo(bufferedSink);
        //必须调用flush，否则最后一部分数据可能不会被写入
        bufferedSink.flush();
    }

    private Sink sink(Sink sink) {
        return new ForwardingSink(sink) {
            //当前写入字节数
            long bytesWritten = 0L;
            //总字节长度，避免多次调用contentLength()方法
            long contentLength = 0L;

            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                super.write(source, byteCount);
                if (contentLength == 0) {
                    //获得contentLength的值，后续不再调用
                    contentLength = requestBody.contentLength();
//                    LogUtils.d(TAG, contentLength);
                }
                //增加当前写入的字节数
                bytesWritten += byteCount;
                //回调
                progressListener.update(bytesWritten, contentLength, bytesWritten == contentLength);
            }
        };
    }
}
