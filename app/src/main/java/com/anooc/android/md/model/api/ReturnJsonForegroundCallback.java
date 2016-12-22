package com.anooc.android.md.model.api;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.anooc.android.md.model.entity.ReturnJson;
import com.anooc.android.md.ui.util.ActivityUtils;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ReturnJsonForegroundCallback<T extends ReturnJson> implements Callback<T>, ReturnJsonCallbackLifecycle<T> {

    private final Activity activity;

    public ReturnJsonForegroundCallback(@NonNull Activity activity) {
        this.activity = activity;
    }

    @NonNull
    protected final Activity getActivity() {
        return activity;
    }

    @Override
    public final void onResponse(Call<T> call, Response<T> response) {
        if (ActivityUtils.isAlive(activity)) {
            boolean interrupt;
            if (response.isSuccessful()) {
                interrupt = onResultOk(response.code(), response.headers(), response.body());
            } else {
                interrupt = onResultError(response.code(), response.headers(), ReturnJson.buildError(response));
            }
            if (!interrupt) {
                onFinish();
            }
        }
    }

    @Override
    public final void onFailure(Call<T> call, Throwable t) {
        if (ActivityUtils.isAlive(activity)) {
            boolean interrupt;
            if (call.isCanceled()) {
                interrupt = onCallCancel();
            } else {
                interrupt = onCallException(t, ReturnJson.buildError(t));
            }
            if (!interrupt) {
                onFinish();
            }
        }
    }

    @Override
    public boolean onResultOk(int code, Headers headers, T result) {
        return false;
    }

    @Override
    public boolean onResultError(int code, Headers headers, ReturnJson.Error error) {
        return false;
    }

    @Override
    public boolean onCallCancel() {
        return false;
    }

    @Override
    public boolean onCallException(Throwable t, ReturnJson.Error error) {
        return false;
    }

    @Override
    public void onFinish() {}

}
