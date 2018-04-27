package com.tl.tplus.instanllapp;

import android.os.Bundle;

import com.tl.tplus.base.activity.ActivityUtils;
import com.tl.tplus.base.activity.BaseToolBarActivity;
import com.tl.tplus.instanllapp.dagger.InstallModule;
import com.tl.tplus.instanllapp.mvp.InstallPresenter;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/3/10.
 *
 */

public class InstallActivity extends BaseToolBarActivity {

  @Inject
  InstallPresenter mPresenter;

  private InstallFragment mFragment;

  @Override
  public int getLayoutResId() {
    return com.tl.tplus.R.layout.activity_base;
  }

  @Override
  public void initFragment(Bundle savedInstanceState) {
    setTitleCenter("Pinjaman Saya");
    mFragment = InstallFragment.newInstance();
    ActivityUtils.addFragmentToActivity(fragmentManager, mFragment, com.tl.tplus.R.id.container,
        InstallFragment.TAG);
    getApiServiceComponent().installComponent(new InstallModule(mFragment)).inject(this);
  }
}
