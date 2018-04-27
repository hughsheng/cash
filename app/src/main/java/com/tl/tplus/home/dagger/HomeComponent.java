package com.tl.tplus.home.dagger;

import com.tl.tplus.base.scope.FragmentScoped;
import com.tl.tplus.home.MainActivity;

import dagger.Subcomponent;

/**
 * Created by sztangli on 2018-2-28.
 *
 */
@FragmentScoped
@Subcomponent(modules =HomePresenterModule.class)
public interface HomeComponent {
  void inject(MainActivity mainActivity);
}
