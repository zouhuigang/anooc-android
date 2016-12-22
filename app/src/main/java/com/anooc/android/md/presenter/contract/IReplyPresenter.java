package com.anooc.android.md.presenter.contract;

import android.support.annotation.NonNull;

import com.anooc.android.md.model.entity.Reply;

public interface IReplyPresenter {

    void upReplyAsyncTask(@NonNull Reply reply);

}
