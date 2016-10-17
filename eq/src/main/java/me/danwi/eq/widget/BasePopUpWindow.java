package me.danwi.eq.widget;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

import butterknife.ButterKnife;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/10/11
 * Time: 下午1:57
 */
public class BasePopUpWindow extends PopupWindow {

    private Context context;

    //设置透明度
    private float mShowAlpha = 0.6f;

    private long duration = 320;

    private Drawable mBackgroundDrawable;

    public BasePopUpWindow(Context context, int resId, int width, int height) {
        this(context, resId, width, height, android.R.anim.fade_in);
    }

    public BasePopUpWindow(Context context, int resId, int width, int height, int animationStyle) {
        this.context = context;
        View view = LayoutInflater.from(context).inflate(resId, null);
        setContentView(view);
        ButterKnife.bind(this, view);
        //设置宽高
        setWidth(width);
        setHeight(height);
        //默认设置outside点击无响应 true 有响应
        setOutsideTouchable(true);
        //设置PopUpWindow中如果包含EditText能弹出软键盘
        setFocusable(true);
        //设置动画
        setAnimationStyle(animationStyle);
        //弹出软键盘把PopUpWindow顶上去
        setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }


    public void showBottom(View view) {
        showAtLocation(view, Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 0);
    }

    public void showCenter(View view) {
        showAtLocation(view, Gravity.CENTER, 0, 0);
    }

    @Override
    public void setOutsideTouchable(boolean touchable) {
        super.setOutsideTouchable(touchable);
        if (touchable) {
            if (mBackgroundDrawable == null) {
                mBackgroundDrawable = new ColorDrawable(0x00000000);
            }
            //如果不设置背景点击其他的地方不会dismiss
            super.setBackgroundDrawable(mBackgroundDrawable);
        } else {
            super.setBackgroundDrawable(null);
        }
    }

    @Override
    public void setBackgroundDrawable(Drawable background) {
        mBackgroundDrawable = background;
        setOutsideTouchable(isOutsideTouchable());
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        showAnimator().start();
    }

    @Override
    public void showAsDropDown(View anchor) {
        super.showAsDropDown(anchor);
        showAnimator().start();
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        super.showAsDropDown(anchor, xoff, yoff);
        showAnimator().start();
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
        super.showAsDropDown(anchor, xoff, yoff, gravity);
        showAnimator().start();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        dismissAnimator().start();
    }

    /**
     * 窗口显示，窗口背景透明度渐变动画
     */
    private ValueAnimator showAnimator() {
        ValueAnimator animator = ValueAnimator.ofFloat(1.0f, mShowAlpha);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float alpha = (float) animation.getAnimatedValue();
                setWindowBackgroundAlpha(alpha);
            }
        });
        animator.setDuration(duration);
        return animator;
    }

    /**
     * 窗口隐藏，窗口背景透明度渐变动画
     */
    private ValueAnimator dismissAnimator() {
        ValueAnimator animator = ValueAnimator.ofFloat(mShowAlpha, 1.0f);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float alpha = (float) animation.getAnimatedValue();
                setWindowBackgroundAlpha(alpha);
            }
        });
        animator.setDuration(duration);
        return animator;
    }

    /**
     * 控制窗口背景的不透明度
     */
    private void setWindowBackgroundAlpha(float alpha) {
        Window window = ((Activity) context).getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.alpha = alpha;
        window.setAttributes(layoutParams);
    }

    public void setmShowAlpha(float mShowAlpha) {
        this.mShowAlpha = mShowAlpha;
    }

}
