package com.anooc.android.md.presenter.contract;

import android.support.annotation.NonNull;

import com.anooc.android.md.model.entity.TabType;

public interface ICreateTopicPresenter {

    void createTopicAsyncTask(@NonNull TabType tab, String title, String content);

}
