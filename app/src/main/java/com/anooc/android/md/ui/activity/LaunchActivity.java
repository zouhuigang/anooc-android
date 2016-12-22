package com.anooc.android.md.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.anooc.android.md.R;
import com.anooc.android.md.ui.base.BaseActivity;
import com.anooc.android.md.ui.util.ActivityUtils;
import com.anooc.android.md.util.HandlerUtils;

public class LaunchActivity extends BaseActivity implements Runnable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        HandlerUtils.postDelayed(this, 1000);
    }

    @Override
    public void run() {
        if (ActivityUtils.isAlive(this)) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

}
