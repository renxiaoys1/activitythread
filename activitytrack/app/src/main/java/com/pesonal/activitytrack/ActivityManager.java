
package com.pesonal.activitytrack;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.util.Log;

/**
 * activity的管理栈，方便进行activity进行查找、处理
 * <p/>
 * 目前仅仅适用于单线程
 */
public class ActivityManager {
    private static final String TAG = "ActivityManager";
    private static ActivityManager SINGLETON = new ActivityManager();
    private static List<Activity> mAcitivityList = new ArrayList<Activity>();

    private ActivityManager() {
        if (null == mAcitivityList) {
            mAcitivityList = new ArrayList<Activity>();
        }
        mAcitivityList.clear();
    }

    public static ActivityManager getInstance() {
        if (null == SINGLETON) {
            SINGLETON = new ActivityManager();
        }

        return SINGLETON;
    }

    /**
     * activity入栈
     */
    public void addActivity(Activity activity) {
        showLog(activity);
        mAcitivityList.add(activity);
    }

    /**
     * activity出栈
     */
    public void popActivity(Activity activity) {
        if (null == mAcitivityList) {
            return;
        }
        int total = mAcitivityList.size();
        if (total > 0) {
            mAcitivityList.remove(activity);
        }
    }

    /**
     * 获取栈顶的activity
     */
    public Activity getTopActivity() {
        if (null == mAcitivityList) {
            return null;
        }
        int total = mAcitivityList.size();
        if (total > 0) {
            Activity currentActivity = mAcitivityList.get(total - 1);
            return currentActivity;
        }
        return null;
    }

    /**
     * 清空所有的activity
     */
    public void onExit() {
        if (null != mAcitivityList) {
            mAcitivityList.clear();
        }
        mAcitivityList = null;
        SINGLETON = null;
    }
    
    
    /**
     *
     * 功能：输出上一个以及当前activity的名称
     * */
    public void showLog(Activity currentActivity) {
        
        String currentClassName = currentActivity.getClass().getName();
        String lastAcitivityName = "";
        Activity topActivity = getTopActivity();
        if (topActivity != null) {
            lastAcitivityName = topActivity.getClass().getName();
        }

        Log.e(TAG, "lastActivityName:" + lastAcitivityName + ",currentActivityName:" + currentClassName);
        
    } 
}
