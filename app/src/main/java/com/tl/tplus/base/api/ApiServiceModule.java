package com.tl.tplus.base.api;

import com.tl.tplus.base.api.interceptor.ParamsInterceptor;
import com.tl.tplus.base.api.interceptor.ResponseInterceptor;
import com.tl.tplus.base.app.CashApplication;


import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fangpenglin on 16/6/18.
 * 管理retrofit网络库的全局代理module
 */
@Module
public class ApiServiceModule {
  public static final String TAG = ApiServiceModule.class.getSimpleName();
  public static final int CACHE_TIME = 24 * 60 * 60;  //无网络保存缓存数据时间
  public static final String PAY = "pay_retrofit";
  /**
   * .
   * okhttp的一些参数设置常量(秒级单位)
   */
  private static final int READ_TIME = 30;
  private static final int WRITE_TIME = 30;
  private static final int CONNECT_TIME = 5;
  private static final int CACHE_SIZE = 1024 * 1024 * 50;  //50M
  private String mBaseUrl = "";

  public ApiServiceModule(String baseUrl) {
    this.mBaseUrl = baseUrl;
  }

  @Provides
  @Singleton
  protected Cache providesCache() {
    //添加缓存
    //getExternalCacheDir()
    File cacheFile = new File(CashApplication.getInstance().getExternalCacheDir(), "lfpCache");
    return new Cache(cacheFile, CACHE_SIZE);
  }

  @Provides
  @Singleton
  protected HttpLoggingInterceptor providesHttpLoggingInterceptor() {
    //配置日记拦截器
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

    return loggingInterceptor;
  }

  @Provides
  @Singleton
  protected ParamsInterceptor providesParamsInterceptor() {
    return new ParamsInterceptor();
  }

  @Provides
  @Singleton
  protected ResponseInterceptor providesResponseInterceptor() {
    return new ResponseInterceptor();
  }

//
//  @Provides
//  @Singleton
//  protected X509TrustManager providesTrustManagerForCertificates(TrustManagerFactory trustManagerFactory) {
//    // Use it to build an X509 trust manager.
//    try {
//      TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
//      if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
//        throw new IllegalStateException("Unexpected default trust managers:"
//            + Arrays.toString(trustManagers));
//      }
//      return (X509TrustManager) trustManagers[0];
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//    return null;
//  }

//  @Provides
//  @Singleton
//  protected SSLSocketFactory providesSSLSocketFactory(TrustManagerFactory trustManagerFactory) {
//    //Create an SSLContext that uses our TrustManager
//    try {
//      SSLContext sslContext = SSLContext.getInstance("TLS");
//      sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
//      return sslContext.getSocketFactory();
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//    return null;
//  }
//
//  @Provides
//  @Singleton
//  protected TrustManagerFactory providesTrustManagerFactory(KeyStore keyStore) {
//    try {
//      TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
//          TrustManagerFactory.getDefaultAlgorithm());
//      trustManagerFactory.init(keyStore);
//      return trustManagerFactory;
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//    return null;
//  }
//
//  @Provides
//  @Singleton
//  protected KeyStore providesNewEmptyKeyStore(Context context) {
//    try {
//      InputStream inputStream;
//      if (BuildConfig.DEBUG) {//chunaiji
//        inputStream = context.getAssets().open("cer/chunaiji.cer");   //测试服务器证书
//      } else {
//        inputStream = context.getAssets().open("cer/leyou.cer");      //正式环境证书
//      }
//
//      CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
//      Collection<? extends Certificate> certificates = certificateFactory.generateCertificates(inputStream);
//      if (certificates.isEmpty()) {
//        throw new IllegalArgumentException("expected non-empty set of trusted certificates");
//      }
//
//      if (inputStream != null) {
//        inputStream.close();
//      }
//      String password = "password";
//      KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType()); // 这里添加自定义的密码，默认
//      InputStream in = null; // By convention, 'null' creates an empty key store.
//      keyStore.load(in, null);
//
//      // Put the certificates a key store.
//      int index = 0;
//      for (Certificate certificate : certificates) {
//        String certificateAlias = Integer.toString(index++);
//        keyStore.setCertificateEntry(certificateAlias, certificate);
//      }
//      return keyStore;
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//    return null;
//  }

  @Provides
  @Singleton
  protected OkHttpClient providesOkHttpClient(
//      SSLSocketFactory sslSocketFactory,
//      X509TrustManager x509TrustManager,
                                              HttpLoggingInterceptor loggingInterceptor,
    ResponseInterceptor responseInterceptor
    //  , ParamsInterceptor paramsInterceptor, Cache cache
  ) {

    //配置拦截器
    return new OkHttpClient.Builder()
        .connectTimeout(CONNECT_TIME, TimeUnit.SECONDS)
        .readTimeout(READ_TIME, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIME, TimeUnit.SECONDS)
//        .sslSocketFactory(sslSocketFactory, x509TrustManager)
//        .cache(cache)
//        .addInterceptor(paramsInterceptor)
        .addNetworkInterceptor(responseInterceptor)
        .addNetworkInterceptor(loggingInterceptor)
        .build();
  }



  @Provides
  @Singleton
  protected Retrofit providesRetrofit(OkHttpClient okHttpClient) {
    return new Retrofit.Builder()
  //      .addConverterFactory(SimpleXmlConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //配置rxjava
        .baseUrl(mBaseUrl)     //配置基本地址
        .client(okHttpClient)  //配置客户端
        .build();
  }


}
