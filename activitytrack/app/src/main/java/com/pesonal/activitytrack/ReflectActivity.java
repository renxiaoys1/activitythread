/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pesonal.activitytrack;

import java.lang.reflect.Field;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

public class ReflectActivity extends Activity {

    private static final String TAG = "ReflectActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflect_layout);
        ActivityManager.getInstance().addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        reflectFun();
    }

    @Override
    public void onDestroy() {
        ActivityManager.getInstance().popActivity(this);
        super.onDestroy();
    }


    private void reflectFun() {
        Class activityThreadClass = null;

        try {
            activityThreadClass = Class.forName("android.app.ActivityThread");
            Object activityThread = activityThreadClass.getMethod("currentActivityThread").invoke(null);
            Field activitiesField = activityThreadClass.getDeclaredField("mActivities");
            activitiesField.setAccessible(true);
            Map activities = (Map) activitiesField.get(activityThread);
            int i = 0;
            for (Object activityRecord : activities.values()) {
                Class activityRecordClass = activityRecord.getClass();
                Field activityField = activityRecordClass.getDeclaredField("activity");
                activityField.setAccessible(true);
                Activity activity = (Activity) activityField.get(activityRecord);
                Log.e(TAG, "activitysum:" + activities.size() + "index:" + i +  ", activity class name:" + activity.getClass().getName());
                i++;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
