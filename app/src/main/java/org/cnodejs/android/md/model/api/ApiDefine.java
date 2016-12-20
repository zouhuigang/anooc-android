package org.cnodejs.android.md.model.api;

import android.os.Build;

import org.cnodejs.android.md.BuildConfig;

public final class ApiDefine {

    private ApiDefine() {}
    //api http://www.anooc.com/api/anote/list?recoid=62&count=20&method=his&kw=
    public static final String WEB_ROOT = "http://www.anooc.com/";
    public static final String API_BASE_URL = WEB_ROOT + "api/";
    public static final String USER_PATH_PREFIX = "/user/";
    public static final String USER_LINK_URL_PREFIX = WEB_ROOT + USER_PATH_PREFIX;
    public static final String TOPIC_PATH_PREFIX = "/topic/";
    public static final String TOPIC_LINK_URL_PREFIX = WEB_ROOT + TOPIC_PATH_PREFIX;

    public static final String USER_AGENT = "CNodeMD/" + BuildConfig.VERSION_NAME + " (Android " + Build.VERSION.RELEASE + "; " + Build.MANUFACTURER + " - " + Build.MODEL + ")";

    public static final boolean MD_RENDER = true; // 使用服务端Markdown渲染可以轻微提升性能

}
