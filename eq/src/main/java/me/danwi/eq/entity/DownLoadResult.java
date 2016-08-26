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

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public long getProgress() {
        return progress;
    }

    public void setProgress(long progress) {
        this.progress = progress;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

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
