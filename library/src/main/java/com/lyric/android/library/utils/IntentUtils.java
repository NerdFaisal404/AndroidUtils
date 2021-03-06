package com.lyric.android.library.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

/**
 * @author lyric
 * @description
 * @time 2016/6/6 14:14
 */
public class IntentUtils extends FileIntentUtils {

    private IntentUtils() {
    }

    /**
     * 跳转到拨号界面
     * @param context 上下文
     * @param phoneNumber 电话号码
     */
    public static void toCall(Context context, String phoneNumber) {
        Uri uri = Uri.parse("tel:" + phoneNumber);
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 跳转到短信发送界面
     * @param context 上下文
     * @param smsContent 短信内容
     */
    public static void smsTo(Context context , String smsContent) {
        smsTo(context, smsContent, "");
    }

    /**
     * 跳转到短信发送界面
     * @param context 上下文
     * @param smsContent 短信内容
     * @param phoneNumber 联系人号码
     */
    public static void smsTo(Context context , String smsContent, String phoneNumber) {
        Uri uri = Uri.parse("smsto:" + phoneNumber);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.putExtra("sms_body", smsContent);
        intent.setType("vnd.android-dir/mms-sms");
        intent.setData(uri);
        try {
            // 跳转到短信发送界面，此处需要捕捉异常
            context.startActivity(intent);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * 跳转到相关的应用程序
     * @param context 上下文
     * @param url 链接地址
     */
    public static void toUrl(Context context, String url) {
        if (context == null || TextUtils.isEmpty(url)) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        context.startActivity(intent);
    }

    /**
     * 跳转到邮件发送界面
     * @param context 上下文
     * @param emailAddress 邮箱地址
     */
    public static void toEmail(Context context, String emailAddress) {
        if (context == null || TextUtils.isEmpty(emailAddress)) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + emailAddress));
        context.startActivity(intent);
    }
}
