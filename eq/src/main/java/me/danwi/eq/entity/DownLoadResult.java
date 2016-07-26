package me.danwi.eq.entity;

/**
 * Created by RunningSnail on 16/7/21.
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
