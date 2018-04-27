package com.tl.tplus.base.app;

import android.content.Context;

import com.tl.tplus.util.SharedPreferencesUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/2/15.
 * 全局对象提供类
 */
@Module
public class AppModule {

  private Context context;

  public AppModule(Context context) {
    this.context = context;
  }

  @Provides
  @Singleton
  Context providesContext() {
    return context;
  }

  @Provides
  @Singleton
  SharedPreferencesUtils providesSharedPreferencesUtils(Context context) {
    return SharedPreferencesUtils.getInstance(context);
  }
}
