package me.danwi.eq.third;

import android.widget.ImageView;

import java.io.File;

/**
 * Created by toonew on 2017/2/23.
 */

public interface ImgLoader {
    //加载资源图片
    void load(int resId, ImageView iv);

    //加载本地图片
    void load(File file, ImageView iv);

    //加载网络图片
    void load(String path, ImageView iv);
}