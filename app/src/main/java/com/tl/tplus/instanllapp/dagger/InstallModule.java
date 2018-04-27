package com.tl.tplus.instanllapp.dagger;

import com.tl.tplus.instanllapp.api.InstallApiService;
import com.tl.tplus.instanllapp.mvp.InstallContract;
import com.tl.tplus.base.scope.FragmentScoped;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2018/3/10.
 */
@Module
public class InstallModule {

  private InstallContract.IView mIView;

  public InstallModule(InstallContract.IView mIView) {
    this.mIView = mIView;
  }

  @FragmentScoped
  @Provides
  InstallContract.IView providesFilterContractIView(){
    return mIView;
  }

  @FragmentScoped
  @Provides
  InstallApiService providesFilterApiService(Retrofit retrofit){
    return retrofit.create(InstallApiService.class);
  }
}
