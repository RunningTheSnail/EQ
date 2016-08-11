package me.danwi.eq.progress;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/1/20
 * Time: 下午3:18
 */
public interface ProgressListener {
    /**
     * 上传下载进度监听
     * @param bytes 传输的字节数
     * @param contentLength 需要传输的总长度
     * @param done  是否传输完成
     */
    void update(long bytes, long contentLength, boolean done);
}
