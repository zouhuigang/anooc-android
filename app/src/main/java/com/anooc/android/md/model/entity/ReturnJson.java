package com.anooc.android.md.model.entity;

import android.support.annotation.NonNull;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;

import com.anooc.android.md.model.util.EntityUtils;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.Response;

/**
 * Created by mdshi on 2016/12/21.
 *
 * {
 "data": {
 },
 "info": "获取数据成功",
 "status": 200
 }
 */

public class ReturnJson {
    public int status;
    public String info;

    public static class Data<T> extends ReturnJson {

        private T data;

        public T getData() {
            return data;
        }

    }

    public static class Error extends ReturnJson {

        @SerializedName("error_msg")
        private String errorMessage;

        public String getErrorMessage() {
            return errorMessage;
        }

    }

    public static ReturnJson.Error buildError(@NonNull Throwable t) {
        ReturnJson.Error error = new ReturnJson.Error();
        //error.success = false;
        if (t instanceof UnknownHostException || t instanceof ConnectException) {
            error.errorMessage = "网络无法连接";
        } else if (t instanceof SocketTimeoutException) {
            error.errorMessage = "网络访问超时";
        } else if (t instanceof JsonSyntaxException) {
            error.errorMessage = "响应数据格式错误";
        } else {
            error.errorMessage = "未知错误：" + t.getLocalizedMessage();
        }
        return error;
    }

    public static <T extends ReturnJson> ReturnJson.Error buildError(@NonNull Response<T> response) {
        try {
            return EntityUtils.gson.fromJson(response.errorBody().string(), ReturnJson.Error.class);
        } catch (IOException | JsonSyntaxException e) {
            ReturnJson.Error error = new ReturnJson.Error();
            switch (response.code()) {
                case 400:
                    error.errorMessage = "请求参数有误";
                    break;
                case 403:
                    error.errorMessage = "请求被拒绝";
                    break;
                case 404:
                    error.errorMessage = "资源未找到";
                    break;
                case 405:
                    error.errorMessage = "请求方式不被允许";
                    break;
                case 408:
                    error.errorMessage = "请求超时";
                    break;
                case 422:
                    error.errorMessage = "请求语义错误";
                    break;
                case 500:
                    error.errorMessage = "服务器逻辑错误";
                    break;
                case 502:
                    error.errorMessage = "服务器网关错误";
                    break;
                case 504:
                    error.errorMessage = "服务器网关超时";
                    break;
                default:
                    error.errorMessage = response.message();
                    break;
            }
            return error;
        }
    }


}
