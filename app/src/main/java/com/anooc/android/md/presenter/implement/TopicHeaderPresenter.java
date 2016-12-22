package com.anooc.android.md.presenter.implement;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.anooc.android.md.model.api.ApiClient;
import com.anooc.android.md.model.api.DefaultCallback;
import com.anooc.android.md.model.entity.Result;
import com.anooc.android.md.model.storage.LoginShared;
import com.anooc.android.md.presenter.contract.ITopicHeaderPresenter;
import com.anooc.android.md.ui.view.ITopicHeaderView;

import okhttp3.Headers;

public class TopicHeaderPresenter implements ITopicHeaderPresenter {

    private final Activity activity;
    private final ITopicHeaderView topicHeaderView;

    public TopicHeaderPresenter(@NonNull Activity activity, @NonNull ITopicHeaderView topicHeaderView) {
        this.activity = activity;
        this.topicHeaderView = topicHeaderView;
    }

    @Override
    public void collectTopicAsyncTask(@NonNull String topicId) {
        ApiClient.service.collectTopic(LoginShared.getAccessToken(activity), topicId).enqueue(new DefaultCallback<Result>(activity) {

            @Override
            public boolean onResultOk(int code, Headers headers, Result result) {
                topicHeaderView.onCollectTopicOk();
                return false;
            }

        });
    }

    @Override
    public void decollectTopicAsyncTask(@NonNull String topicId) {
        ApiClient.service.decollectTopic(LoginShared.getAccessToken(activity), topicId).enqueue(new DefaultCallback<Result>(activity) {

            @Override
            public boolean onResultOk(int code, Headers headers, Result result) {
                topicHeaderView.onDecollectTopicOk();
                return false;
            }

        });
    }

}
