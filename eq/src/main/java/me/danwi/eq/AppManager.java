package me.danwi.eq;

import android.app.Activity;

import java.util.Stack;

/**
 * 应用程序Activity管理类：用于Activity管理和应用程序退出
 *
 * @author kymjs
 * @version 1.0
 * @created 2013-11-24
 */
public class AppManager {
    private static Stack<Activity> activityStack;
    private static AppManager instance;

    public AppManager() {
    }

    public static AppManager getAppManager() {
        if (instance == null) {
            synchronized (AppManager.class) {
                if (instance == null) {
                    instance = new AppManager();
                }
            }
        }
        return instance;
    }

    // 退出栈顶Activity,即结束指定的Activity
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activity.finish();
            removeActivity(activity);
        }
    }

    public void removeActivity(Activity activity) {
        if (activity != null) {
            if (activityStack.contains(activity)) {
                activityStack.remove(activity);
            }
        }
    }

    // 获得当前栈顶Activity
    public Activity currentActivity() {
        Activity activity = null;
        if (null != activityStack && 0 != activityStack.size()) {
            activity = activityStack.lastElement();
        }
        return activity;
    }

    // 将当前Activity推入栈中
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    // 除了某个Activity,退出栈中其它所有Activity
    public void popAllActivityExceptOne(Class<?> cls) {
        if (null == activityStack || activityStack.size() == 0) {
            return;
        }
        int len = activityStack.size();
        for (int i = len - 1; i >= 0; i--) {
            Activity activity = activityStack.get(i);
            if (activity == null || (null != cls && activity.getClass().equals(cls))) {
                continue;
            }
            finishActivity(activity);
        }
    }

    // 退出栈中当前Activity
    public void popUpActivity(Class<?> cls) {
        if (null == activityStack || activityStack.size() == 0) {
            return;
        }
        int len = activityStack.size();
        for (int i = len - 1; i >= 0; i--) {
            Activity activity = activityStack.get(i);
            if (activity == null)
                continue;
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                break;
            }
        }
    }

    /**
     * 结束所有activity
     */
    public void finishAllActivities() {
        for (Activity activity : activityStack) {
            activity.finish();
        }
        activityStack.clear();
    }
}