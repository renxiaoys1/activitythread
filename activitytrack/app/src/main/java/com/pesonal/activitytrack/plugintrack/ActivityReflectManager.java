/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pesonal.activitytrack.plugintrack;

import android.app.Instrumentation;
import android.content.Context;
import android.os.Looper;

public class ActivityReflectManager {

    private static ActivityReflectManager SINGLETON;

    private Context mContext;


    private ActivityReflectManager(Context context) {
        if (!isMainThread()) {
            return;
        }

        mContext = context;
        DelegateActivityThread delegateActivityThread = DelegateActivityThread.getSingletion();
        Instrumentation originInstrumentation = delegateActivityThread.getInstrumentation();
        if (!(originInstrumentation instanceof DelegateInstrumentation)) {
            DelegateInstrumentation delegateInstrumentation = new DelegateInstrumentation(originInstrumentation);
            delegateActivityThread.setInstrumentation(delegateInstrumentation);
        }

    }


    public static void init(Context context) {
        if (null != SINGLETON) {
            return;
        }

        SINGLETON = new ActivityReflectManager(context);
    }


    private boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

}