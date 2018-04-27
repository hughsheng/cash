package com.tl.tplus.detail.dagger;

import com.tl.tplus.base.scope.FragmentScoped;
import com.tl.tplus.detail.DetailActivity;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2018/3/2.
 */
@FragmentScoped
@Subcomponent(modules = DetailModule.class)
public interface DetailComponent {
    void inject(DetailActivity detailActivity);
}
