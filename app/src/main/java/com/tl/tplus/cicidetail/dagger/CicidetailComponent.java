package com.tl.tplus.cicidetail.dagger;

import com.tl.tplus.base.scope.FragmentScoped;
import com.tl.tplus.cicidetail.CicidetailActivity;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2018/3/2.
 */
@FragmentScoped
@Subcomponent(modules = CicidetailModule.class)
public interface CicidetailComponent {
    void inject(CicidetailActivity cicidetailActivity);
}
