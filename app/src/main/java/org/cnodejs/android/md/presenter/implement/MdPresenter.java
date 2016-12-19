package org.cnodejs.android.md.presenter.implement;

import android.app.Activity;
import android.support.annotation.NonNull;

import org.cnodejs.android.md.model.api.ApiClient;
import org.cnodejs.android.md.model.api.ApiDefine;
import org.cnodejs.android.md.model.api.DefaultCallback;
import org.cnodejs.android.md.model.entity.Md;
import org.cnodejs.android.md.model.entity.Result;
import org.cnodejs.android.md.model.storage.LoginShared;
import org.cnodejs.android.md.presenter.contract.IMdPresenter;
import org.cnodejs.android.md.ui.view.MdView;

import okhttp3.Headers;

public class MdPresenter implements IMdPresenter {

    private final Activity activity;
    private final MdView mdView;

    public MdPresenter(@NonNull Activity activity, @NonNull MdView mdView) {
        this.activity = activity;
        this.mdView = mdView;
    }

    @Override
    public void getMdAsyncTask() {
        ApiClient.service.getMdList(LoginShared.getAccessToken(activity), ApiDefine.MD_RENDER).enqueue(new DefaultCallback<Result.Data<Md>>(activity) {

            @Override
            public boolean onResultOk(int code, Headers headers, Result.Data<Md> result) {
                mdView.onGetMessagesOk(result.getData());
                return false;
            }

            @Override
            public void onFinish() {
                mdView.onGetTopicFinish();
            }

        });
    }

}
