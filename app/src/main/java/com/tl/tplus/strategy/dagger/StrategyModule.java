package com.tl.tplus.strategy.dagger;

import com.tl.tplus.base.scope.FragmentScoped;
import com.tl.tplus.strategy.api.StrategyApiService;
import com.tl.tplus.strategy.mvp.StrategyContract;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2018/3/10.
 */
@Module
public class StrategyModule {

  private StrategyContract.IView mIView;

  public StrategyModule(StrategyContract.IView mIView) {
    this.mIView = mIView;
  }

  @FragmentScoped
  @Provides
  StrategyContract.IView providesFilterContractIView(){
    return mIView;
  }

  @FragmentScoped
  @Provides
  StrategyApiService providesFilterApiService(Retrofit retrofit){
    return retrofit.create(StrategyApiService.class);
  }
}
