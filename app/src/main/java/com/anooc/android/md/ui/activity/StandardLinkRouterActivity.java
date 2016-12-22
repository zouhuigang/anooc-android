package com.anooc.android.md.ui.activity;

import android.os.Bundle;

import com.anooc.android.md.R;
import com.anooc.android.md.ui.base.BaseActivity;
import com.anooc.android.md.ui.util.Navigator;
import com.anooc.android.md.ui.util.ToastUtils;

public class StandardLinkRouterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!Navigator.openStandardLink(this, getIntent().getDataString())) {
            ToastUtils.with(this).show(R.string.invalid_link);
        }
        finish();
    }

}
