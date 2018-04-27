package com.tl.tplus.detail.dagger;

import com.tl.tplus.base.scope.FragmentScoped;
import com.tl.tplus.detail.DetailsWebActivity;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2018/3/2.
 */
@FragmentScoped
@Subcomponent(modules = DetailWebModule.class)
public interface DetailWebComponent {
    void inject(DetailsWebActivity detailsWebActivity);
}
