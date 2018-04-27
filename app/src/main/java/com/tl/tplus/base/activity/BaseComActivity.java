package com.tl.tplus.base.activity;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.tl.tplus.base.api.ApiServiceComponent;
import com.tl.tplus.base.app.CashApplication;
import com.tl.tplus.util.ActivityManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/2/23.
 * 全局activity的基础类管理
 */

public abstract class BaseComActivity extends AppCompatActivity{

  protected CashApplication cashApplication;
  protected FragmentManager fragmentManager;
  protected Unbinder unbinder;
  protected Window window;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    cashApplication=CashApplication.getInstance();
    fragmentManager=getSupportFragmentManager();
    window=getWindow();
    if (Build.VERSION_CODES.LOLLIPOP <= Build.VERSION.SDK_INT) {
      //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    }
    setContentView(getLayoutResId());
    ActivityUtils.addActivity(this.getClass().getSimpleName(), this);
    unbinder = ButterKnife.bind(this);

    ActivityManager.getAppManager().addActivity(this);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    ActivityUtils.removeActivity(this.getClass().getSimpleName());
    unbinder.unbind();
    unbinder = null;
  }

  protected ApiServiceComponent getApiServiceComponent() {
    return cashApplication.getApiServiceComponent();
  }

  @Override
  public Resources getResources() {
    Resources res = super.getResources();
    Configuration config = new Configuration();
    config.setToDefaults();
    res.updateConfiguration(config, res.getDisplayMetrics());
    return res;
  }

  public View getRootView() {
    return ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
  }

  /**
   * 显示提示信息

   * @param message (提示信息)
   */
  public void showSnackBarTip(String message) {
    Snackbar.make(getRootView(), message, Snackbar.LENGTH_SHORT).show();
  }

  /**
   * 显示提示信息
   *
   * @param errCode (编码)
   * @param resId   (提示信息)
   */
  public void showSnackBarTip(int errCode, int resId) {
    Snackbar.make(getRootView(), resId, Snackbar.LENGTH_SHORT).show();
  }

  public void showToastTip(int resId) {
    Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show();
  }

  public void showToastTip(String message) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }

  public void showSnackbarTipAction(int mesId, int actionId, View.OnClickListener clickListener) {
    Snackbar.make(getRootView(), mesId, Snackbar.LENGTH_SHORT)
        .setAction(actionId, clickListener)
        .setDuration(Snackbar.LENGTH_LONG)
        .show();
  }

  public abstract int getLayoutResId();

  public abstract void initFragment(Bundle savedInstanceState);

}
