package com.tl.tplus.base.api.interceptor;


import com.tl.tplus.base.api.bean.HeadBean;
import com.tl.tplus.base.app.CashApplication;
import com.tl.tplus.util.MobileOnlyCode;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;



/**
 * Created by fangpenglin on 16/6/18.
 * 参数拦截器(添加共有参数)
 */
public class ParamsInterceptor implements Interceptor {

  private TreeMap<String, String> mMap;
  private CashApplication application;
  private HeadBean headBean;

  public ParamsInterceptor() {
    mMap = new TreeMap<>();
    application = CashApplication.getInstance();
  }

  public ParamsInterceptor(String key, String value) {
    this();
    mMap.put(key, value);
  }

  @SuppressWarnings("unused")
  public ParamsInterceptor(TreeMap<String, String> hashMap) {
    this();
    mMap.putAll(hashMap);
  }

  @Override
  public Response intercept(Chain chain) throws IOException {
    Request originalRequest = chain.request();
    HttpUrl originalHttpUrl = originalRequest.url();

    if (headBean == null) {
      headBean = new HeadBean();

    }

    StringBuilder head = new StringBuilder();
    String uuid = MobileOnlyCode.uuid(application);  //手机唯一码
    String time = "" + System.currentTimeMillis() / 1000;  //时间戳

    String ftlyApplication = headBean.toString();
    Map<String, String> content = new TreeMap<>();


    String reUrl = originalRequest.url().toString();
    boolean isMD5 = true;   //默认需要开启md5

    return chain.proceed(originalRequest);
  }
}
