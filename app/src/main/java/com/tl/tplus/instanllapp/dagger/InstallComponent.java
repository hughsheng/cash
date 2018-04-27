package com.tl.tplus.instanllapp.dagger;

import com.tl.tplus.instanllapp.InstallActivity;
import com.tl.tplus.base.scope.FragmentScoped;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2018/3/10.
 */
@FragmentScoped
@Subcomponent(modules = InstallModule.class)
public interface InstallComponent {
  void inject(InstallActivity installActivity);
}
