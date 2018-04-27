package com.tl.tplus.cicidetailinfo.dagger;

import com.tl.tplus.base.scope.FragmentScoped;
import com.tl.tplus.cicidetailinfo.CicidetailInfoActivity;


import dagger.Subcomponent;

/**
 * Created by Administrator on 2018/3/7.
 *
 */
@FragmentScoped
@Subcomponent(modules = CicidetailInfoModule.class)
public interface CicidetailInfoComponent {
  void inject(CicidetailInfoActivity cicidetailInfoActivity);
}
