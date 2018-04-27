package com.tl.tplus.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;

/**
 * @author john lee
 * @date 2018/4/11
 */

public class IntentUtil {

    public static String getQueryParam(String url) {
        String query = "";
        String[] urlParts = url.split("\\?");
        if (urlParts.length > 1) {
            query = urlParts[1];
        }
        return query;
    }

    public static String getGooglePlayStoreUrl(Context context, String packageStr) {
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        Uri marketUri = Uri.parse("market://details?" + packageStr);
        Intent marketIntent = new Intent(Intent.ACTION_VIEW).setData(marketUri);
        if (marketIntent.resolveActivity(packageManager) != null) {
            return "market://details?" + packageStr;
        } else {
            return "https://play.google.com/store/apps/details?" + packageStr;
        }
    }

    /**
     * 打开系统自带的View跳转链接
     *
     * @param context
     * @param link
     */
    public static void startMarketView(Context context, String link) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(link);
        intent.setData(content_url);
        boolean flag = isLocalApp(context, "com.android.vending");
        if (flag) {
            intent.setPackage("com.android.vending");  //指定应用市场
        }
        context.startActivity(intent);
    }

    /**
     * 判断本地是否存在此包名的应用
     *
     * @param paramContext
     * @param appPkgName
     * @return
     */
    public static boolean isLocalApp(Context paramContext, String appPkgName) {
        if (null == appPkgName) {
            return false;
        }
        if(TextUtils.isEmpty(appPkgName)){
            return false;
        }
        boolean flag = false;
        try {
            if ((paramContext != null) && (!TextUtils.isEmpty(appPkgName))) {
                if (paramContext.getPackageManager().getLaunchIntentForPackage(appPkgName) != null) {
                    flag = true;
                } else {
                    flag = false;
                }
            }
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
}
