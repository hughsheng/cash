package com.tl.tplus.util;


import android.Manifest;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.gson.Gson;
import com.tl.tplus.base.api.bean.RequestBean;
import com.tl.tplus.base.app.CashApplication;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/22.
 *
 */

public class CommonUtil {

  public static String TAG = "ApkTool";
  public static List<String> mLocalInstallApps = null;
  public static String[] PERMISSION = {Manifest.permission.READ_PHONE_STATE};

  public static String RequestSignData(RequestBean requestBean){
    String timestamp="" + System.currentTimeMillis() / 1000;  //时间戳
    String data= new Gson().toJson(requestBean);
   String sign=md5(md5(ConstanceValue.KEY + "*|*" + data +"@!@"+ timestamp));
   return sign;
  }

  public static String md5(String string) {
    MessageDigest md5 = null;
    try {
      md5 = MessageDigest.getInstance("MD5");
      byte[] bytes = md5.digest(string.getBytes());
      String result = "";
      for (byte b : bytes) {
        String temp = Integer.toHexString(b & 0xff);
        if (temp.length() == 1) {
          temp = "0" + temp;
        }
        result += temp;
      }
      return result;
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return "";
  }

  public static List<String> scanLocalInstallAppList(PackageManager packageManager) {
    List<String> myAppInfos = new ArrayList<>();
    try {
      List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
      for (int i = 0; i < packageInfos.size(); i++) {
        PackageInfo packageInfo = packageInfos.get(i);
        //过滤掉系统app
            if ((ApplicationInfo.FLAG_SYSTEM & packageInfo.applicationInfo.flags) != 0) {
                continue;
            }
        if (packageInfo.applicationInfo.loadIcon(packageManager) == null) {
          continue;
        }
        myAppInfos.add(packageInfo.packageName);
      }
    }catch (Exception e){
      Log.e(TAG,"===============获取应用包信息失败");
    }
    return myAppInfos;
  }

  /**
   * 把价格字符串改为每隔3个加.的格式
   * @param price
   * @return
   */
  public static String getSortPrice(String price){
    String reversePrice= new StringBuffer(price).reverse().toString();
    StringBuffer buffer=new StringBuffer(reversePrice);
    for(int i=3;i<buffer.length();i+=4){
      buffer.insert(i,".");
    }

    return buffer.reverse().toString();
  }

  /**
   * 判断手机是否安装gp商店
   *
   * @param context
   * @return
   */
  public static boolean isMobile_spExist(Context context) {
    PackageManager manager = context.getPackageManager();
    List<PackageInfo> pkgList = manager.getInstalledPackages(0);
    for (int i = 0; i < pkgList.size(); i++) {
      PackageInfo pI = pkgList.get(i);
      if (pI.packageName.equalsIgnoreCase("com.android.vending"))
        return true;
    }
    return false;
  }

  /**
   * 动态判断是否有手机权限
   * @param permission
   * @return
   */
  public static boolean isLacksOfPermission(String permission) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      return ContextCompat.checkSelfPermission(
          CashApplication.getInstance().getApplicationContext(), permission) == PackageManager
          .PERMISSION_DENIED;
    }
    return false;
  }

}
