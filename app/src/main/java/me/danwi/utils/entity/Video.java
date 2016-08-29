package me.danwi.utils.entity;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by RunningSnail on 16/7/28.
 */
public class Video {
    /**
     * release : false
     * show : true
     * title : title
     * type : []
     * userId : bdd7281e-72f3-4676-8b22-30499b2d21ca
     * coverUrl : http://video.wingamings.com/resources/selftimeCover/813470f6-17d2-4b3a-a796-352385dd563f.jpg
     * videoUrl : http://video.wingamings.com/resources/selftime/813470f6-17d2-4b3a-a796-352385dd563f.jpg
     * size : 301203
     * watch : 0
     * share : 0
     * id : 813470f6-17d2-4b3a-a796-352385dd563f
     * detail : detail
     * create_at : 2016-07-28T03:36:40.963Z
     * collect : 0
     * favorite : 0
     */
    public boolean release;
    public boolean show;
    public String title;
    public List<?> type;
    public String userId;
    public String coverUrl;
    public String videoUrl;
    public int size;
    public int watch;
    public int share;
    public String id;
    public String detail;
    public String create_at;
    public int collect;
    public int favorite;

    public static Video objectFromData(String str) {

        return new Gson().fromJson(str, Video.class);
    }

    @Override
    public String toString() {
        return "Video{" +
                "release=" + release +
                ", show=" + show +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", userId='" + userId + '\'' +
                ", coverUrl='" + coverUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", size=" + size +
                ", watch=" + watch +
                ", share=" + share +
                ", id='" + id + '\'' +
                ", detail='" + detail + '\'' +
                ", create_at='" + create_at + '\'' +
                ", collect=" + collect +
                ", favorite=" + favorite +
                '}';
    }
}
