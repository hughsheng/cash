package com.tl.tplus.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by sztangli on 2018-2-28.
 */

public class HomePagerFragmentadapter extends FragmentPagerAdapter {
  private List<Fragment> mFragments;

  public HomePagerFragmentadapter(FragmentManager fm, List<Fragment> mFragments) {
    super(fm);
    this.mFragments = mFragments;
  }

  @Override
  public Fragment getItem(int position) {//必须实现
    return mFragments.get(position);
  }

  @Override
  public int getCount() {//必须实现
    return mFragments.size();
  }

  @Override
  public CharSequence getPageTitle(int position) {//选择性实现
    return mFragments.get(position).getClass().getSimpleName();
  }
}

