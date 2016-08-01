package me.danwi.eq.entity;

/**
 * Created with Android Studio.
 * User: 最帅最帅的RunningSnail
 * Date: 16/7/21
 * Time: 下午4:34
 * <p/>
 * 下载结果
 */
public class DownLoadResult {
    public long current;
    public long contentLength;
    public long progress;
    public boolean done;

    @Override
    public String toString() {
        return "DownLoadResult{" +
                "current=" + current +
                ", contentLength=" + contentLength +
                ", progress=" + progress +
                ", done=" + done +
                '}';
    }
}
