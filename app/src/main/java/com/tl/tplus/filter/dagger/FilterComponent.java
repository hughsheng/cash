package com.tl.tplus.filter.dagger;

import com.tl.tplus.base.scope.FragmentScoped;
import com.tl.tplus.filter.FilterActivity;


import dagger.Subcomponent;

/**
 * Created by Administrator on 2018/3/10.
 */
@FragmentScoped
@Subcomponent(modules = FilterModule.class)
public interface FilterComponent {
  void inject(FilterActivity filterActivity);
}
