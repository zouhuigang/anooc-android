package com.anooc.android.md.model.entity;

import android.support.annotation.StringRes;

import com.anooc.android.md.R;

public enum TabType {

    all(R.string.tab_all),

    good(R.string.tab_good),

    share(R.string.tab_share),

    ask(R.string.tab_ask),

    job(R.string.tab_job),

    cloud_notes(R.string.tab_cloud_notes);


    @StringRes
    private int nameId;

    TabType(@StringRes int nameId) {
        this.nameId = nameId;
    }

    @StringRes
    public int getNameId() {
        return nameId;
    }

}
