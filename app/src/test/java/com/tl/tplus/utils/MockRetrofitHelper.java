package com.tl.tplus.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by linfp on 2016/7/1.
 * 测试类,测试网络数据
 */
public class MockRetrofitHelper {

  //    public static final String BASE_URL = "http://10.98.56.189/api/";  //香立服务器
  public static final String BASE_URL = "https://apis.cashcash.id/";  //测试服务器
  //    public static final String BASE_URL = "http://203.86.25.7/api/services/Assistant/";
  //    public static final String BASE_URL = "http://leyou.fangte.com/api/services/Assistant/";
  final int connectTimeout;
  final int readTimeout;
  final int writeTimeout;
  final String baseUrl;

  public MockRetrofitHelper(int readTime, int writeTime, int connectTime, String baseUrl) {
    this.readTimeout = readTime;
    this.writeTimeout = writeTime;
    this.connectTimeout = connectTime;
    this.baseUrl = baseUrl;
  }

  public <T> T create(Class<T> clazz) {
    OkHttpClient.Builder builder = new OkHttpClient.Builder();

    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

    builder.addInterceptor(loggingInterceptor);
    builder.connectTimeout(connectTimeout, TimeUnit.SECONDS);
    builder.readTimeout(readTimeout, TimeUnit.SECONDS);
    builder.writeTimeout(writeTimeout, TimeUnit.SECONDS);

    Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
        .client(builder.build())
        .addConverterFactory(GsonConverterFactory.create())   //配置数据解释器
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //配置rxjava
        .build();

    return retrofit.create(clazz);
  }

  public final static class Builder {
    private int readTime;

    private int writeTime;

    private int connectTime;

    private String baseUrl;

    public Builder() {
      readTime = 30;
      writeTime = 30;
      connectTime = 30;
      baseUrl = BASE_URL;
    }

    public Builder readTime(int readTime) {
      this.readTime = readTime;
      return this;
    }

    public Builder writeTime(int writeTime) {
      this.writeTime = writeTime;
      return this;
    }

    public Builder connectTime(int connectTime) {
      this.connectTime = connectTime;
      return this;
    }

    public Builder baseUrl(String baseUrl) {
      this.baseUrl = baseUrl;
      return this;
    }

    public MockRetrofitHelper builder() {
      if (writeTime <= 0) {
        writeTime = 30;
      }
      if (readTime <= 0) {
        readTime = 30;
      }
      if (connectTime <= 0) {
        connectTime = 30;
      }
      if (baseUrl == null || "".equals(baseUrl)) {
        baseUrl = BASE_URL;
      }
      return new MockRetrofitHelper(readTime, writeTime, connectTime, baseUrl);
    }
  }
}
