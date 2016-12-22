package com.anooc.android.md.presenter.contract;

import android.support.annotation.NonNull;

import com.anooc.android.md.model.entity.TabType;

public interface IMainPresenter {

    void switchTab(@NonNull TabType tab);

    void refreshTopicListAsyncTask();

    void loadMoreTopicListAsyncTask(int page);

    void getUserAsyncTask();

    void getMessageCountAsyncTask();

}
