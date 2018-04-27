package com.tl.tplus.filter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.tl.tplus.R;
import com.tl.tplus.base.activity.ActivityUtils;
import com.tl.tplus.base.activity.BaseToolBarActivity;
import com.tl.tplus.dialogfragment.FilterDialogAFragment;
import com.tl.tplus.dialogfragment.FilterDialogFragment;
import com.tl.tplus.filter.dagger.FilterModule;
import com.tl.tplus.filter.mvp.FilterPresenter;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/3/10.
 *
 */

public class FilterActivity extends BaseToolBarActivity implements FilterDialogAFragment
    .FilterDialogAFragmentListener {

  @Inject
  FilterPresenter mPresenter;

  public static final int RESULT = 0x111;
  private FilterFragment mFragment;
  private FilterDialogAFragment filterDialogAFragment;

  @Override
  public int getLayoutResId() {
    return com.tl.tplus.R.layout.activity_base;
  }

  @Override
  public void initFragment(Bundle savedInstanceState) {
 //   titleCenter.setText("BUNGA RENDAH");
    titleCenter.setTextColor(Color.BLACK);
    toolbar.setBackgroundColor(Color.WHITE);
    toolbar.setNavigationIcon(R.mipmap.back_black);
    titleRight.setVisibility(View.VISIBLE);
    titleRight.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (filterDialogAFragment == null) {
          filterDialogAFragment = FilterDialogAFragment.newInstance();
        }
        if (!filterDialogAFragment.isAdded()) {
          filterDialogAFragment.show(fragmentManager, FilterDialogFragment.TAG);
        }
      }
    });
    Intent intent = getIntent();
    String money_id = intent.getStringExtra(FilterFragment.MONEYID);
    String time_id = intent.getStringExtra(FilterFragment.TIMEID);
    String address_id = intent.getStringExtra(FilterFragment.ADDRESSID);
    String interest_id = intent.getStringExtra(FilterFragment.INTERESTID);
    mFragment = FilterFragment.newInstance(money_id, time_id, address_id, interest_id);
    ActivityUtils.addFragmentToActivity(fragmentManager, mFragment, com.tl.tplus.R.id.container,
        FilterFragment.TAG);
    getApiServiceComponent().filterComponent(new FilterModule(mFragment)).inject(this);
  }

  @Override
  public void showFilterList(Map<String, String> selectedData) {
    mFragment.showFilterList(selectedData);
  }
}
