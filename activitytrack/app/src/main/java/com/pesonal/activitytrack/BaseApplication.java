/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pesonal.activitytrack;

import com.pesonal.activitytrack.plugintrack.ActivityReflectManager;

import android.app.Application;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ActivityReflectManager.init(this);
    }

}
