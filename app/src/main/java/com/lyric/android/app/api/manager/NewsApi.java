package com.lyric.android.app.api.manager;

import com.lyric.android.app.api.Api;

/**
 * @author ganyu
 * @description 资讯信息相关API
 * @time 2016/1/27 15:36
 */
public class NewsApi extends Api {
    private static NewsApi sInstance;

    private NewsApi() {
    }

    public static synchronized NewsApi getInstance() {
        if (sInstance == null) {
            sInstance = new NewsApi();
        }
        return sInstance;
    }
}
