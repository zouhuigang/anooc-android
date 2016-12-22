package com.anooc.android.md.presenter.contract;

import android.support.annotation.NonNull;

public interface ITopicHeaderPresenter {

    void collectTopicAsyncTask(@NonNull String topicId);

    void decollectTopicAsyncTask(@NonNull String topicId);

}
