package com.tl.tplus.detailinfo.dagger;

import com.tl.tplus.base.scope.FragmentScoped;
import com.tl.tplus.detailinfo.DetailInfoActivity;


import dagger.Subcomponent;

/**
 * Created by Administrator on 2018/3/7.
 *
 */
@FragmentScoped
@Subcomponent(modules = DetailInfoModule.class)
public interface DetailInfoComponent {
  void inject(DetailInfoActivity detailActivity);
}
