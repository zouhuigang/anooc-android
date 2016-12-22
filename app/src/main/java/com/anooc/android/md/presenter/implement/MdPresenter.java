package com.anooc.android.md.presenter.implement;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.anooc.android.md.model.api.ApiClient;

import com.anooc.android.md.model.api.ReturnJsonCallBack;


import com.anooc.android.md.model.entity.MdList;
import com.anooc.android.md.model.entity.ReturnJson;
import com.anooc.android.md.presenter.contract.IMdPresenter;
import com.anooc.android.md.ui.view.MdView;
import com.anooc.android.md.ui.viewholder.LoadMoreFooter;

import okhttp3.Headers;
import retrofit2.Call;

public class MdPresenter implements IMdPresenter {

    private static final int count = 20;
    private final Activity activity;
    private final MdView mdView;
    private Call<ReturnJson.Data<MdList>> loadMoreCall = null;
    private Call<ReturnJson.Data<MdList>> refreshCall = null;

    public MdPresenter(@NonNull Activity activity, @NonNull MdView mdView) {
        this.activity = activity;
        this.mdView = mdView;
    }

    //下拉刷新
    @Override
    public void OnRefreshGetMdDataAsyncTask(int recoid) {
        if (refreshCall == null) {
            refreshCall = ApiClient.service.getMdList(recoid, count, "new", "心情随笔");
            refreshCall.enqueue(new ReturnJsonCallBack<ReturnJson.Data<MdList>>(activity) {
                @Override
                public boolean onResultOk(int code, Headers headers, ReturnJson.Data<MdList> result) {
                    if (result.getData().getList().size() > 0) {
                        mdView.onRefreshMdListOk(result.getData());
                    }
                    return false;
                    //mdView.onLoadMoreGetMdDataOk(result.getData());
                    //return false;
                }

                @Override
                public void onFinish() {
                    refreshCall = null;
                    mdView.onRefreshMdListFinish();
                }


            });
        }

    }



    //向下加载更多
    @Override
    public void onLoadMoreGetMdDataAsyncTask(int recoid) {
        if (loadMoreCall == null) {
            loadMoreCall = ApiClient.service.getMdList(recoid, count, "his", "心情随笔");
            loadMoreCall.enqueue(new ReturnJsonCallBack<ReturnJson.Data<MdList>>(activity) {
                @Override
                public boolean onResultOk(int code, Headers headers, ReturnJson.Data<MdList> result) {
                    if (result.getData().getList().size() > 0) {
                        mdView.onLoadMoreGetMdDataOk(result.getData());
                        mdView.onLoadMoreGetMdDataFinish(LoadMoreFooter.STATE_ENDLESS);
                    } else {
                        mdView.onLoadMoreGetMdDataFinish(LoadMoreFooter.STATE_FINISHED);
                    }
                    return false;
                    //mdView.onLoadMoreGetMdDataOk(result.getData());
                    //return false;
                }

                @Override
                public void onFinish() {
                    loadMoreCall = null;
                }


            });
        }

    }

}
