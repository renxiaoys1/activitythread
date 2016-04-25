
package com.pesonal.activitytrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

public class LogActivity extends Activity {

    private TextView mClickButton;
    private TextView mReflectButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_layout);
        initView();
        ActivityManager.getInstance().addActivity(this);
    }


    public void initView() {
        mClickButton = (TextView) findViewById(R.id.tv_click);
        mReflectButton = (TextView) findViewById(R.id.tv_click_three);
        mClickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSecondActivity();
            }
        });

        mReflectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReflectAcitivity();
            }
        });
    }

    /**
     * 打开另一个activity
     * */
    private void openSecondActivity() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    /**
     * 使用返回获取activity生命周期的方法
     * */
    private void openReflectAcitivity() {
        Intent intent = new Intent(this, ReflectActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        ActivityManager.getInstance().popActivity(this);
        super.onDestroy();
    }

}
