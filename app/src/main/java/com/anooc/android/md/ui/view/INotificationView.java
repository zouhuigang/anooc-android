package com.anooc.android.md.ui.view;

import android.support.annotation.NonNull;

import com.anooc.android.md.model.entity.Notification;

public interface INotificationView {

    void onGetMessagesOk(@NonNull Notification notification);

    void onGetMessagesFinish();

    void onMarkAllMessageReadOk();

}
