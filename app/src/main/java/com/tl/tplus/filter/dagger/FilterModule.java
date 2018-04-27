package com.tl.tplus.filter.dagger;

import com.tl.tplus.base.scope.FragmentScoped;
import com.tl.tplus.filter.api.FilterApiService;
import com.tl.tplus.filter.mvp.FilterContract;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2018/3/10.
 */
@Module
public class FilterModule {

  private FilterContract.IView mIView;

  public FilterModule(FilterContract.IView mIView) {
    this.mIView = mIView;
  }

  @FragmentScoped
  @Provides
  FilterContract.IView providesFilterContractIView(){
    return mIView;
  }

  @FragmentScoped
  @Provides
  FilterApiService providesFilterApiService(Retrofit retrofit){
    return retrofit.create(FilterApiService.class);
  }
}
