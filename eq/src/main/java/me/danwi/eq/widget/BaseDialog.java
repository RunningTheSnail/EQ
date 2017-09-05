package me.danwi.eq.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import me.danwi.eq.R;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/9/29
 * Time: 下午5:02
 */
public abstract class BaseDialog extends Dialog {

    protected Context context;

    private int width, height;

    public BaseDialog(Context context, int resId) {
        this(context, resId, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    public BaseDialog(Context context, int resId, int width, int height) {
        super(context);
        this.context = context;
        this.width = width;
        this.height = height;
        //去掉标题
        if (withoutTitle()) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        setContentView(resId);
        setCancelable(cancelAble());
        ButterKnife.bind(this);
        Window window = getWindow();
        setAnimation(window);
    }

    //配置是否显示标题
    public abstract boolean withoutTitle();

    //窗口外是否能点击dismiss
    public abstract boolean cancelAble();

    //配置对话框的动画
    public void setAnimation(Window window) {
        window.setWindowAnimations(R.style.BaseDialogAnimation);
    }

    @Override
    public void show() {
        super.show();
        //控制dialog大小
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = width;
        layoutParams.height = height;
        //不设置这个dialog不会全屏
        window.setBackgroundDrawable(null);
        window.setAttributes(layoutParams);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (open()) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (open()) {
            EventBus.getDefault().unregister(this);
        }
    }

    protected boolean open() {
        return false;
    }
}
