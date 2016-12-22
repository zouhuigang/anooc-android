package com.anooc.android.md.model.api;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.annotation.NonNull;

import com.anooc.android.md.R;

import com.anooc.android.md.model.entity.ReturnJson;
import com.anooc.android.md.ui.activity.LoginActivity;
import com.anooc.android.md.ui.dialog.AlertDialogUtils;
import com.anooc.android.md.ui.util.ToastUtils;

import okhttp3.Headers;

public class ReturnJsonCallBack<T extends ReturnJson> extends ReturnJsonForegroundCallback<T> {

    public ReturnJsonCallBack(@NonNull Activity activity) {
        super(activity);
    }

    @Override
    public final boolean onResultError(int code, Headers headers, ReturnJson.Error error) {
        if (code == 401) {
            return onResultAuthError(code, headers, error);
        } else {
            return onResultOtherError(code, headers, error);
        }
    }

    public boolean onResultAuthError(int code, Headers headers, ReturnJson.Error error) {
        AlertDialogUtils.createBuilderWithAutoTheme(getActivity())
                .setMessage(R.string.access_token_out_of_date)
                .setPositiveButton(R.string.relogin, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LoginActivity.startForResult(getActivity());
                    }

                })
                .setNegativeButton(R.string.cancel, null)
                .show();
        return false;
    }

    public boolean onResultOtherError(int code, Headers headers, ReturnJson.Error error) {
        ToastUtils.with(getActivity()).show(error.getErrorMessage());
        return false;
    }

    @Override
    public boolean onCallException(Throwable t, ReturnJson.Error error) {
        ToastUtils.with(getActivity()).show(error.getErrorMessage());
        return false;
    }

}
