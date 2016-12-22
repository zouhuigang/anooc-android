package com.anooc.android.md.ui.listener;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.webkit.JavascriptInterface;

import com.anooc.android.md.R;
import com.anooc.android.md.model.entity.Reply;
import com.anooc.android.md.model.storage.LoginShared;
import com.anooc.android.md.model.util.EntityUtils;
import com.anooc.android.md.presenter.contract.IReplyPresenter;
import com.anooc.android.md.presenter.contract.ITopicHeaderPresenter;
import com.anooc.android.md.ui.activity.LoginActivity;
import com.anooc.android.md.ui.activity.UserDetailActivity;
import com.anooc.android.md.ui.util.ToastUtils;
import com.anooc.android.md.ui.view.ICreateReplyView;
import com.anooc.android.md.util.HandlerUtils;

public final class TopicJavascriptInterface {

    public static final String NAME = "topicBridge";

    private final Activity activity;
    private final ICreateReplyView createReplyView;
    private final ITopicHeaderPresenter topicHeaderPresenter;
    private final IReplyPresenter replyPresenter;

    public TopicJavascriptInterface(@NonNull Activity activity, @NonNull ICreateReplyView createReplyView, @NonNull ITopicHeaderPresenter topicHeaderPresenter, @NonNull IReplyPresenter replyPresenter) {
        this.activity = activity;
        this.createReplyView = createReplyView;
        this.topicHeaderPresenter = topicHeaderPresenter;
        this.replyPresenter = replyPresenter;
    }

    @JavascriptInterface
    public void collectTopic(String topicId) {
        if (LoginActivity.checkLogin(activity)) {
            topicHeaderPresenter.collectTopicAsyncTask(topicId);
        }
    }

    @JavascriptInterface
    public void decollectTopic(String topicId) {
        if (LoginActivity.checkLogin(activity)) {
            topicHeaderPresenter.decollectTopicAsyncTask(topicId);
        }
    }

    @JavascriptInterface
    public void upReply(String replyJson) {
        if (LoginActivity.checkLogin(activity)) {
            Reply reply = EntityUtils.gson.fromJson(replyJson, Reply.class);
            if (reply.getAuthor().getLoginName().equals(LoginShared.getLoginName(activity))) {
                ToastUtils.with(activity).show(R.string.can_not_up_yourself_reply);
            } else {
                replyPresenter.upReplyAsyncTask(reply);
            }
        }
    }

    @JavascriptInterface
    public void at(final String targetJson, final int targetPosition) {
        if (LoginActivity.checkLogin(activity)) {
            HandlerUtils.post(new Runnable() {

                @Override
                public void run() {
                    Reply target = EntityUtils.gson.fromJson(targetJson, Reply.class);
                    createReplyView.onAt(target, targetPosition);
                }

            });
        }
    }

    @JavascriptInterface
    public void openUser(String loginName) {
        UserDetailActivity.start(activity, loginName);
    }

}
