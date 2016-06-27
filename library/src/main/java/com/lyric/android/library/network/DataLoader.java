package com.lyric.android.library.network;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * @author lyric
 * @description
 * @time 2016/6/22 14:01
 */
public class DataLoader<T> {
    private Method mMethod;
    private String mUrl;
    private Map<String, String> mParams;
    private Type mType;
    private ResponseCallback<T> mCallback;
    private Request<T> mRequest;

    public DataLoader(String url, Map<String, String> params, Type type, ResponseCallback<T> callback) {
        this(Method.POST, url, params, type, callback);
    }

    public DataLoader(Method method, String url, Map<String, String> params, Type type, ResponseCallback<T> callback) {
        this.mMethod = method;
        this.mUrl = url;
        this.mParams = params;
        this.mType = type;
        this.mCallback = callback;
    }

    public void load() {
        mRequest = new Request<>(mMethod, mUrl, mParams, mType, mCallback);
        mRequest.execute();
    }

    public void cancel() {
    }
}
