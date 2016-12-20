package org.cnodejs.android.md.ui.view;

import android.support.annotation.NonNull;

import org.cnodejs.android.md.model.entity.Md;

public interface MdView {
    void onGetMessagesOk(@NonNull Md md);
    void onGetMdFinish();

}
