package com.tl.tplus.detailinfo;

import android.content.Intent;
import android.os.Bundle;

import com.tl.tplus.base.activity.ActivityUtils;
import com.tl.tplus.base.activity.BaseToolBarActivity;
import com.tl.tplus.detailinfo.dagger.DetailInfoModule;
import com.tl.tplus.detailinfo.mvp.DetailInfoPresenter;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/3/7.
 *
 */

public class DetailInfoActivity extends BaseToolBarActivity {

  @Inject
  DetailInfoPresenter mPresenter;

  private DetailInfoFragment mFragment;

  @Override
  public int getLayoutResId() {
    return com.tl.tplus.R.layout.activity_base;
  }

  @Override
  public void initFragment(Bundle savedInstanceState) {
    Intent intent=getIntent();
    String pid=intent.getStringExtra(DetailInfoFragment.PID);
    String name=intent.getStringExtra(DetailInfoFragment.NAME);
    setTitleCenter(name);
    mFragment = DetailInfoFragment.newInstance(pid);
    ActivityUtils.addFragmentToActivity(fragmentManager, mFragment, com.tl.tplus.R.id.container,
        DetailInfoFragment.TAG);
    getApiServiceComponent().detailInfoComponent(new DetailInfoModule(mFragment)).inject(this);
  }
}
