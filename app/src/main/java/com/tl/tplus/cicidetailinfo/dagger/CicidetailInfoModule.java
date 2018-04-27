package com.tl.tplus.cicidetailinfo.dagger;

import com.tl.tplus.base.scope.FragmentScoped;
import com.tl.tplus.cicidetailinfo.api.CicidetailInfoApiService;
import com.tl.tplus.cicidetailinfo.mvp.CicidetailInfoContract;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2018/3/7.
 */
@Module
public class CicidetailInfoModule {
  private CicidetailInfoContract.IView mIView;

  public CicidetailInfoModule(CicidetailInfoContract.IView mIView) {
    this.mIView = mIView;
  }

  @FragmentScoped
  @Provides
  CicidetailInfoContract.IView providesCicidetailContractIView() {
    return mIView;
  }

  @FragmentScoped
  @Provides
  CicidetailInfoApiService providesCicidetailContractApiService(Retrofit retrofit) {
    return retrofit.create(CicidetailInfoApiService.class);
  }
}
