package com.anooc.android.md.model.api;

import com.anooc.android.md.model.entity.ReturnJson;

import okhttp3.Headers;

public interface ReturnJsonCallbackLifecycle<T extends ReturnJson> {

    boolean onResultOk(int code, Headers headers, T result);

    boolean onResultError(int code, Headers headers, ReturnJson.Error error);

    boolean onCallCancel();

    boolean onCallException(Throwable t, ReturnJson.Error error);

    void onFinish();

}
