/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pesonal.activitytrack.plugintrack;

import com.pesonal.activitytrack.reflect.Reflect;

import android.app.ActivityThread;
import android.app.Application;
import android.app.Instrumentation;

public class DelegateActivityThread {

    private static DelegateActivityThread SINGLETOPN = new DelegateActivityThread();

    private Reflect mActivityThreadReflect;

    public DelegateActivityThread() {
        mActivityThreadReflect = Reflect.on(ActivityThread.currentActivityThread());
    }

    public static DelegateActivityThread getSingletion() {
        return SINGLETOPN;
    }

    public Application getInitialApplication() {
        return mActivityThreadReflect.get("mInitialApplication");
    }

    public Instrumentation getInstrumentation() {
        return mActivityThreadReflect.get("mInstrumentation");
    }

    public void setInstrumentation(Instrumentation newInstrumentation) {
        mActivityThreadReflect.set("mInstrumentation", newInstrumentation);
    }
}
