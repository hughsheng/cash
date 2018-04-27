package com.tl.tplus.detailinfo.dagger;

import com.tl.tplus.base.scope.FragmentScoped;
import com.tl.tplus.detailinfo.api.DetailInfoApiService;
import com.tl.tplus.detailinfo.mvp.DetailInfoContract;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2018/3/7.
 *
 */
@Module
public class DetailInfoModule {
  private DetailInfoContract.IView mIView;

  public DetailInfoModule(DetailInfoContract.IView mIView) {
    this.mIView = mIView;
  }

  @FragmentScoped
  @Provides
  DetailInfoContract.IView providesDetailContractIView() {
    return mIView;
  }

  @FragmentScoped
  @Provides
  DetailInfoApiService providesDetailContractApiService(Retrofit retrofit) {
    return retrofit.create(DetailInfoApiService.class);
  }
}
