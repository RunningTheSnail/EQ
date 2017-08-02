package me.danwi.eq;

import android.app.Application;
import android.content.Context;

import me.danwi.eq.core.ServiceProducers;
import me.danwi.eq.third.ImgLoader;
import me.danwi.eq.third.PicassoLoader;

/**
 * Created with Android Studio.
 * User: 最帅最帅的RunningSnail
 * Date: 16/2/27
 * Time: 上午10:27
 */
public abstract class EQApplication extends Application {

    public String TAG = this.getClass().getSimpleName();

    public static Context context;

    public static ImgLoader imgLoader;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        EQConfig eqConfig = new EQConfig() {
            @Override
            public boolean isDebug() {
                return false;
            }

            @Override
            public String getUrl() {
                return null;
            }
        };
        new ServiceProducers.Builder()
                .url(eqConfig.getUrl())
                .connectTimeOut(eqConfig.connectionTimeOut())
                .debug(eqConfig.isDebug())
                .dir(eqConfig.getDir(this))
                .post(eqConfig.getPost())
                .pre(eqConfig.getPre())
                .readTimeOut(eqConfig.readTimeOut())
                .build();
        imgLoader = PicassoLoader.with(this);
    }

    public static Context getContext() {
        return context;
    }

}
