package com.tl.tplus.strategy.dagger;

import com.tl.tplus.base.scope.FragmentScoped;
import com.tl.tplus.strategy.StrategyActivity;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2018/3/10.
 */
@FragmentScoped
@Subcomponent(modules = StrategyModule.class)
public interface StrategyComponent {
  void inject(StrategyActivity strategyActivity);
}
