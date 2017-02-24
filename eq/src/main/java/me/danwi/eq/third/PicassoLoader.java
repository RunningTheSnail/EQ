package me.danwi.eq.third;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Created by toonew on 2017/2/23.
 */

public class PicassoLoader implements ImgLoader {

    private Picasso picasso;

    private static PicassoLoader picassoLoader;

    private PicassoLoader(Context context) {
        picasso = Picasso.with(context);
    }

    public static PicassoLoader with(Context context) {
        if (picassoLoader == null) {
            synchronized (PicassoLoader.class) {
                if (picassoLoader == null) {
                    picassoLoader = new PicassoLoader(context);
                }
            }
        }
        return picassoLoader;
    }


    @Override
    public void load(int resId, ImageView iv) {
        picasso.load(resId).into(iv);
    }

    @Override
    public void load(File file, ImageView iv) {
        picasso.load(file).into(iv);
    }

    @Override
    public void load(String path, ImageView iv) {
        picasso.load(path).into(iv);
    }
}
