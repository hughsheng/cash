package com.tl.tplus.strategy;

import android.content.Intent;
import android.os.Bundle;

import com.tl.tplus.R;
import com.tl.tplus.base.activity.ActivityUtils;
import com.tl.tplus.base.activity.BaseToolBarActivity;
import com.tl.tplus.strategy.dagger.StrategyModule;
import com.tl.tplus.strategy.mvp.StrategyPresenter;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/3/10.
 *
 */

public class StrategyActivity extends BaseToolBarActivity {

  @Inject
  StrategyPresenter mPresenter;

  private StrategyFragment mFragment;

  @Override
  public int getLayoutResId() {
    return R.layout.activity_base;
  }

  @Override
  public void initFragment(Bundle savedInstanceState) {
    Intent intent = getIntent();
    setTitleCenter(intent.getStringExtra(StrategyFragment.NAME));
    String fw_id = intent.getStringExtra(StrategyFragment.FWID);
    String fw_type = intent.getStringExtra(StrategyFragment.FWTYPY);
    mFragment = StrategyFragment.newInstance(fw_id, fw_type);
    ActivityUtils.addFragmentToActivity(fragmentManager, mFragment, R.id.container,
        StrategyFragment.TAG);
    getApiServiceComponent().strategyComponent(new StrategyModule(mFragment)).inject(this);
  }
}
