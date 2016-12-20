package org.cnodejs.android.md.presenter.implement;

import android.app.Activity;
import android.support.annotation.NonNull;

import org.cnodejs.android.md.model.api.ApiClient;

import org.cnodejs.android.md.model.api.DefaultCallback;
import org.cnodejs.android.md.model.entity.Md;
import org.cnodejs.android.md.model.entity.Result;


import org.cnodejs.android.md.presenter.contract.IMdPresenter;
import org.cnodejs.android.md.ui.view.MdView;

import java.util.List;

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
        ApiClient.service.getMdList(0,20,"his","").enqueue(new DefaultCallback<Result.Data<List<Md>>>(activity) {
            @Override
            public boolean onResultOk(int code, Headers headers, Result.Data<List<Md>> result) {
                mdView.onGetMdDataOk(result.getData());
                return false;
            }

            @Override
            public void onFinish() { mdView.onGetMdFinish();}

        });
    }

}
