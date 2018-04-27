package com.tl.tplus.home.dagger;

import com.tl.tplus.base.scope.FragmentScoped;
import com.tl.tplus.home.api.HomeApiService;
import com.tl.tplus.home.mvp.HomeCiciContract;
import com.tl.tplus.home.mvp.HomeDanaContract;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by sztangli on 2018-2-28.
 *
 */
@Module
public class HomePresenterModule {

  private final HomeDanaContract.IView mDanaIView;
  private final HomeCiciContract.IView mCiciIView;

  public HomePresenterModule(HomeDanaContract.IView mDanaIView,HomeCiciContract.IView mCiciIView) {
    this.mDanaIView = mDanaIView;
    this.mCiciIView=mCiciIView;
  }

  @FragmentScoped
  @Provides
  HomeDanaContract.IView provideHomeContractDanaIView(){return mDanaIView;}

  @FragmentScoped
  @Provides
  HomeCiciContract.IView provideHomeContractCiciIView(){return mCiciIView;}

  @FragmentScoped
  @Provides
  HomeApiService provideHomeApiService(Retrofit retrofit){
    return retrofit.create(HomeApiService.class);
  }

}
