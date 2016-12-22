package com.anooc.android.md.presenter.contract;

public interface IMdPresenter {

        void onLoadMoreGetMdDataAsyncTask(int recoid);
        void OnRefreshGetMdDataAsyncTask(int recoid);
}
