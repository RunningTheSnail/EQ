package com.example.tanshuai.easy_http.progress;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

/**
 * Created by RunningSnail on 16/1/20.
 */
public class ProgressRequestBody extends RequestBody {
    //上传的文件
    private File file;
    private RequestBody requestBody;
    private ProgressListener progressListener;

    public ProgressRequestBody(RequestBody requestBody, File file, ProgressListener progressListener) {
        this.requestBody = requestBody;
        this.file = file;
        this.progressListener = progressListener;
    }

    @Override
    public MediaType contentType() {
        return requestBody.contentType();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        byte[] bytes = new byte[1024];
        FileInputStream fileInputStream = new FileInputStream(file);
        //每一次读取
        int result;
        //读取的总字节
        int load = 0;
        while ((result = fileInputStream.read(bytes)) != -1) {
            load = load + result;
            //更新
            progressListener.update(load, requestBody.contentLength(), true);
            sink.write(bytes, 0, result);
        }
    }
}
