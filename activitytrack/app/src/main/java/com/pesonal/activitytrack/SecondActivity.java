/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pesonal.activitytrack;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_layout);
        ActivityManager.getInstance().addActivity(this);
    }

    @Override
    public void onDestroy() {
        ActivityManager.getInstance().popActivity(this);
        super.onDestroy();
    }
}
