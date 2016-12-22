package com.anooc.android.md.presenter.implement;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.anooc.android.md.model.api.ApiClient;
import com.anooc.android.md.model.api.ApiDefine;
import com.anooc.android.md.model.api.DefaultCallback;
import com.anooc.android.md.model.entity.Notification;
import com.anooc.android.md.model.entity.Result;
import com.anooc.android.md.model.storage.LoginShared;
import com.anooc.android.md.presenter.contract.INotificationPresenter;
import com.anooc.android.md.ui.view.INotificationView;

import okhttp3.Headers;

public class NotificationPresenter implements INotificationPresenter {

    private final Activity activity;
    private final INotificationView notificationView;

    public NotificationPresenter(@NonNull Activity activity, @NonNull INotificationView notificationView) {
        this.activity = activity;
        this.notificationView = notificationView;
    }

    @Override
    public void getMessagesAsyncTask() {
        ApiClient.service.getMessages(LoginShared.getAccessToken(activity), ApiDefine.MD_RENDER).enqueue(new DefaultCallback<Result.Data<Notification>>(activity) {

            @Override
            public boolean onResultOk(int code, Headers headers, Result.Data<Notification> result) {
                notificationView.onGetMessagesOk(result.getData());
                return false;
            }

            @Override
            public void onFinish() {
                notificationView.onGetMessagesFinish();
            }

        });
    }

    @Override
    public void markAllMessageReadAsyncTask() {
        ApiClient.service.markAllMessageRead(LoginShared.getAccessToken(activity)).enqueue(new DefaultCallback<Result>(activity) {

            @Override
            public boolean onResultOk(int code, Headers headers, Result result) {
                notificationView.onMarkAllMessageReadOk();
                return false;
            }

        });
    }

}
