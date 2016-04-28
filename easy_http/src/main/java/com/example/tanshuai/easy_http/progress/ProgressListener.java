package com.example.tanshuai.easy_http.progress;

/**
 * Created by tanshuai on 16/1/20.
 */
interface ProgressListener {
    void update(long bytesRead, long contentLength, boolean done);
}
