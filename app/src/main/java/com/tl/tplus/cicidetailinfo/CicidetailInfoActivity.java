package com.tl.tplus.cicidetailinfo;

import android.content.Intent;
import android.os.Bundle;

import com.tl.tplus.base.activity.ActivityUtils;
import com.tl.tplus.base.activity.BaseToolBarActivity;
import com.tl.tplus.cicidetailinfo.dagger.CicidetailInfoModule;
import com.tl.tplus.cicidetailinfo.mvp.CicidetailInfoPresenter;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/3/7.
 *
 */

public class CicidetailInfoActivity extends BaseToolBarActivity {

  @Inject
  CicidetailInfoPresenter mPresenter;

  private CicidetailInfoFragment mFragment;

  @Override
  public int getLayoutResId() {
    return com.tl.tplus.R.layout.activity_base;
  }

  @Override
  public void initFragment(Bundle savedInstanceState) {
    Intent intent = getIntent();
    String pid = intent.getStringExtra(CicidetailInfoFragment.PID);
    String name=intent.getStringExtra(CicidetailInfoFragment.NAME);
    setTitleCenter(name);
    mFragment = CicidetailInfoFragment.newInstance(pid);
    ActivityUtils.addFragmentToActivity(fragmentManager, mFragment, com.tl.tplus.R.id.container,
        CicidetailInfoFragment.TAG);
    getApiServiceComponent().ciciDetailInfoComponent(new CicidetailInfoModule(mFragment)).inject
        (this);
  }
}
