package com.anooc.android.md.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.anooc.android.md.R;
import com.anooc.android.md.ui.base.StatusBarActivity;
import com.anooc.android.md.ui.listener.NavigationFinishClickListener;
import com.anooc.android.md.ui.util.ThemeUtils;
import com.anooc.android.md.ui.util.ToastUtils;
import com.anooc.android.md.util.ResUtils;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LicenseActivity extends StatusBarActivity {

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.tv_license)
    protected TextView tvLicense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeUtils.configThemeBeforeOnCreate(this, R.style.AppThemeLight, R.style.AppThemeDark);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license);
        ButterKnife.bind(this);

        toolbar.setNavigationOnClickListener(new NavigationFinishClickListener(this));

        try {
            tvLicense.setText(ResUtils.getRawString(this, R.raw.open_source));
        } catch (IOException e) {
            tvLicense.setText(null);
            ToastUtils.with(this).show("资源读取失败");
        }
    }

}
