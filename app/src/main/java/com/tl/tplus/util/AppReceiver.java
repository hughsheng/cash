package com.tl.tplus.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.widget.Toast;

import com.tl.tplus.evenbus.DanaBusBean;

import org.greenrobot.eventbus.EventBus;

public class AppReceiver extends BroadcastReceiver {
  private final String TAG = this.getClass().getSimpleName();

  @Override
  public void onReceive(Context context, Intent intent) {

    if (TextUtils.equals(intent.getAction(), Intent.ACTION_PACKAGE_ADDED)) {
      EventBus.getDefault().post(new DanaBusBean());
    } else if (TextUtils.equals(intent.getAction(), Intent.ACTION_PACKAGE_REMOVED)) {
      EventBus.getDefault().post(new DanaBusBean());
    }
  }

}
