package com.tl.tplus.base.app;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.multidex.MultiDexApplication;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsLogger;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.gson.Gson;
import com.squareup.leakcanary.LeakCanary;
import com.tl.tplus.BuildConfig;
import com.tl.tplus.base.api.ApiServiceComponent;
import com.tl.tplus.base.api.ApiServiceModule;
import com.tl.tplus.base.api.DaggerApiServiceComponent;
import com.tl.tplus.base.api.bean.ErrorBean;
import com.tl.tplus.base.api.bean.RequestBean;
import com.tl.tplus.evenbus.HomeBus;
import com.tl.tplus.util.ConstanceValue;
import com.tl.tplus.util.HttpUtils;
import com.tl.tplus.util.NetWorkUtils;
import com.tl.tplus.util.SharedPreferencesUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/2/14.
 * 初始化全局单例管理类
 */

public class

CashApplication extends MultiDexApplication {

  private static CashApplication applicaiton;
  private static int REQUEST_READ_PHONE_STATE = 1;
  private SharedPreferencesUtils sharedPreferencesUtils;
  private ApiServiceComponent apiServiceComponent;
  private int width = 0, height = 0;
  private String versionName;
  private int versionCode;
  private Subscription subscription;
  public static Typeface TypeRoboto;


  public static CashApplication getInstance() {
    return applicaiton;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    applicaiton = this;
    sharedPreferencesUtils = SharedPreferencesUtils.getInstance(this);
    apiServiceComponent = DaggerApiServiceComponent.builder()
        .appModule(new AppModule(getApplicationContext()))
        .apiServiceModule(new ApiServiceModule(HttpUtils.BASE_URL))
        .build();
    initBaseRequestBean();
    setFont();
    initAppsflyer();
    initFacebook();
    initAltamob();
  }

  /**
   * Facebook
   */
  private void initFacebook() {
    FacebookSdk.sdkInitialize(getApplicationContext());
    AppEventsLogger.activateApp(this);

    // 启用调试记录
    FacebookSdk.setIsDebugEnabled(true);
    FacebookSdk.addLoggingBehavior(LoggingBehavior.APP_EVENTS);
  }

  private void initAppsflyer() {
    AppsFlyerLib.getInstance().startTracking(this, "DbyCWpY9BNde7soACQz7Zi");
    /**
     #AppsFlyer: registerConversionListener implements the collection of attribution (conversion) data
     Please refer to this documentation to view all the available attribution parameters:
     https://support.appsflyer.com/hc/en-us/articles/207032096-Accessing-AppsFlyer-Attribution-Conversion-Data-from-the-SDK-Deferred-Deeplinking
     */
    AppsFlyerLib.getInstance().registerConversionListener(this, new AppsFlyerConversionListener() {
      @Override
      public void onInstallConversionDataLoaded(Map<String, String> conversionData) {
        for (String attrName : conversionData.keySet()) {
          //LogUtil.e(AppsFlyerLib.LOG_TAG + ": attribute: " + attrName + " = " + conversionData.get(attrName));
        }
        //SCREEN VALUES//
        final String install_type = "Install Type: " + conversionData.get("af_status");
        final String media_source = "Media Source: " + conversionData.get("media_source");
        final String install_time = "Install Time(GMT): " + conversionData.get("install_time");
        final String click_time = "Click Time(GMT): " + conversionData.get("click_time");
      }

      @Override
      public void onInstallConversionFailure(String errorMessage) {
        //  LogUtil.e(AppsFlyerLib.LOG_TAG + ": error getting conversion data: " + errorMessage);
      }

      @Override
      public void onAppOpenAttribution(Map<String, String> conversionData) {
      }

      @Override
      public void onAttributionFailure(String errorMessage) {
        // LogUtil.e(AppsFlyerLib.LOG_TAG + ": error onAttributionFailure : " + errorMessage);
      }
    });
  }

  /**
   * Altamob
   */
  private void initAltamob() {
    com.mobi.sdk.ADSDK.getInstance(this.getApplicationContext()).init();
  }

  /**
   * 获取手机屏幕宽高
   */
  private void initProperty() {
    DisplayMetrics metric = new DisplayMetrics();
    WindowManager wm = (WindowManager) getApplicationContext()
        .getSystemService(Context.WINDOW_SERVICE);
    wm.getDefaultDisplay().getMetrics(metric);
    width = metric.widthPixels; // 屏幕宽度（像素）
    height = metric.heightPixels; // 屏幕高度（像素）

//        FontsOverride.setDefaultFont(getApplicationContext(), "MONOSPACE", "fonts/msyh.ttf");
  }

  /**
   * 设置全局字体
   */
  private void setFont() {
    TypeRoboto = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
    try {
      Field field = Typeface.class.getDeclaredField("MONOSPACE");
      field.setAccessible(true);
      field.set(null, TypeRoboto);
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
  }


  /**
   * 获取手机信息
   */
  private void getPhoneInfo() {
    final TelephonyManager tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);

    subscription = Observable.just("")
        .subscribeOn(AndroidSchedulers.mainThread())
        .observeOn(Schedulers.io())
        .subscribe(new Action1<String>() {
          @Override
          public void call(String s) {
            Info adInfo = null;
            try {
              adInfo = AdvertisingIdClient.getAdvertisingIdInfo(getApplicationContext());
            } catch (IOException e) {

            } catch (GooglePlayServicesNotAvailableException e) {
               int i=0;
            } catch (GooglePlayServicesRepairableException e) {
              e.printStackTrace();
            }
            if(adInfo!=null){
              ConstanceValue.GAID = adInfo.getId() != null ? adInfo.getId() : "";
              boolean isLAT = adInfo.isLimitAdTrackingEnabled();
            }

//            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission
//                .READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//              EventBus.getDefault().post(new ErrorBean());
//            }

//            ConstanceValue.AND_ID = Settings.Secure.getString(getApplicationContext()
//                    .getContentResolver(),
//                Settings.Secure.ANDROID_ID);

//            ConstanceValue.IMEI = tm.getDeviceId() != null ? tm.getDeviceId() : "";
            ConstanceValue.SN = getAndroidOsSystemProperties("ro.boot.serialno");
            ConstanceValue.MODEL = android.os.Build.MODEL;
            ConstanceValue.BRAND = android.os.Build.MANUFACTURER;

            RequestBean commonRequestBean = new RequestBean();
            commonRequestBean.setApp_type(ConstanceValue.APP_TYPE);
            commonRequestBean.setApp_version(getVersionCode());
            commonRequestBean.setApp_package(ConstanceValue.PACKAGENAME);
            commonRequestBean.setChannel(ConstanceValue.CHANNEL);
            commonRequestBean.setVersion(ConstanceValue.VERSION);
            RequestBean.DeviceInfoEntity entity = new RequestBean.DeviceInfoEntity();
            entity.setAnd_id(ConstanceValue.AND_ID);
            entity.setGaid(ConstanceValue.GAID);
            entity.setImei(ConstanceValue.IMEI);
            entity.setSn(ConstanceValue.SN);
            entity.setModel(ConstanceValue.MODEL);
            entity.setBrand(ConstanceValue.BRAND);
            commonRequestBean.setDevice_info(entity);
            Gson gson=new Gson();
            ConstanceValue.COMMREQUESTBEAN=gson.toJson(commonRequestBean);
            EventBus.getDefault().post(new HomeBus());
          }
        });
  }

  /**
   * 初始化基础请求bean,需要参加签名的公共变量
   *
   * @return
   */
  private void initBaseRequestBean() {
    getPhoneInfo();
  }

  public int getWidth() {
    if (width == 0) {
      initProperty();
    }
    return width;
  }

  public int getHeight() {
    if (height == 0) {
      initProperty();
    }
    return height;
  }

  /**
   * 获取手机sn号
   *
   * @param key
   * @return
   */
  public static String getAndroidOsSystemProperties(String key) {
    String ret;
    try {
      Method systemProperties_get = Class.forName("android.os.SystemProperties").getMethod("get",
          String.class);
      if ((ret = (String) systemProperties_get.invoke(null, key)) != null)
        return ret;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

    return "";
  }

  public String getAppVersion() {
    if (versionName == null) {
      getAppVersionName(this);
    }
    return versionName;
  }

  public int getVersionCode() {
    if (versionCode == 0) {
      getAppVersionName(this);
    }
    return this.versionCode;
  }

  /**
   * 返回当前程序版本名
   */
  public void getAppVersionName(Context context) {
    String version_name = "";
    try {
      // ---get the package info---
      PackageManager pm = context.getPackageManager();
      PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
      this.versionCode = pi.versionCode;
      version_name = pi.versionName;
      if (version_name == null || version_name.length() <= 0) {
        versionName = "";
      }
    } catch (Exception e) {
    }
    this.versionName = version_name;
  }

  /**
   * 开启后台服务器 (采用IntentService服务形式加载)
   *
   * @param action (动作)
   * @param bundle (绑定数据)
   */
  public void startBackService(String action, Bundle bundle) {
//    Intent backService = new Intent(this, BackService.class);
//    backService.setAction(action);
//    if (bundle != null) {
//      backService.putExtras(bundle);
//    }
//    startService(backService);
  }

  private void initDebug() {
    ButterKnife.setDebug(BuildConfig.DEBUG);
//        Stetho.initializeWithDefaults(this);
    if (BuildConfig.DEBUG) {         //调试模式下,开启严格模式检测
      StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()         //将对当前线程应用该策略
          .detectDiskReads()         //监控磁盘读
          .detectDiskWrites()        //监控磁盘写
          .detectNetwork()           //监控网络访问
          .detectAll()               //检测当前线程所有函数
          .penaltyLog()              //表示将警告输出到LogCat，你也可以使用其他或增加新的惩罚（penalty）函数
//                    .penaltyDeath()            //检测到不合法,杀死进程,自动退出app
          .build());
      LeakCanary.install(this);
    } else {
//      Beta.autoCheckUpgrade = false;
//      Bugly.init(getApplicationContext(), "900037914", false);
    }
  }

  public ApiServiceComponent getApiServiceComponent() {
    return apiServiceComponent;
  }

  /**
   * 采用andriod本身数据格式缓存数据
   * 后期可以更改成其他缓存
   */
  public void saveCacheData(String key, Object data) {
    saveCacheData(SharedPreferencesUtils.SP_NAME, key, data);
  }

  private void saveCacheData(final String fileName, final String key, final Object defaultObject) {
    sharedPreferencesUtils.saveData(fileName, key, defaultObject);
  }

  public Object getCacheData(String key, Object defaultObject) {
    return getCacheData(SharedPreferencesUtils.SP_NAME, key, defaultObject);
  }

  private Object getCacheData(final String fileName, final String key, final Object defaultObject) {
    return sharedPreferencesUtils.getData(fileName, key, defaultObject);
  }

  public void saveCacheListData(String key, final List<Map<String, String>> dataList) {
    saveCacheListData(SharedPreferencesUtils.SP_NAME, key, dataList);
  }

  private void saveCacheListData(final String fileName, final String key, final List<Map<String, String>> dataList) {
    sharedPreferencesUtils.saveListData(fileName, key, dataList);
  }

  public List<Map<String, String>> getCacheListData(final String key) {
    return getCacheListData(SharedPreferencesUtils.SP_NAME, key);
  }

  private List<Map<String, String>> getCacheListData(final String fileName, final String key) {
    return sharedPreferencesUtils.getListData(fileName, key);
  }

  public void removeListData(final String key) {
    sharedPreferencesUtils.removeListData(SharedPreferencesUtils.SP_NAME, key);
  }

  public void saveCacheStringListData(String key, final List<String> dataList) {
    sharedPreferencesUtils.saveStringListData(SharedPreferencesUtils.SP_NAME, key, dataList);
  }

  public List<String> getCacheStringListData(final String key) {
    return sharedPreferencesUtils.getStringListData(SharedPreferencesUtils.SP_NAME, key);
  }

  public void saveMapData(String key, Map<String, String> mapData) {
    sharedPreferencesUtils.saveMapData(SharedPreferencesUtils.SP_NAME, key, mapData);
  }

  public Map<String, String> getMapData(String key) {
    return sharedPreferencesUtils.getMapData(SharedPreferencesUtils.SP_NAME, key);
  }

  /**
   * 网络相关
   *
   * @return true表示已连接, false表示未连接
   */
  public boolean isContented() {
    return NetWorkUtils.isNetworkAvailable(getApplicationContext());
  }

  public String getNetWorkIp() {
    String ip;
    if (NetWorkUtils.isWifi(getApplicationContext())) {
      ip = NetWorkUtils.getWifiIp(getApplicationContext());
    } else {
      ip = NetWorkUtils.getGPRSIpAddress();
    }
    return ip;
  }
}
