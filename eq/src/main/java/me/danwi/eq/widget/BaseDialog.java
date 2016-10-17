package me.danwi.eq.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import butterknife.ButterKnife;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/9/29
 * Time: 下午5:02
 */
public class BaseDialog extends Dialog {

    private Context context;

    public BaseDialog(Context context) {
        super(context);
        this.context = context;
    }

    public BaseDialog(Context context, int resId) {
        this(context);
        //去掉标题
        if (withoutTitle()) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        setContentView(resId);
        ButterKnife.bind(this);
    }

    //配置是否显示标题
    public boolean withoutTitle() {
        return false;
    }

}
