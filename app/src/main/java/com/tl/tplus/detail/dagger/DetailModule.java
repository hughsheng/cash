package com.tl.tplus.detail.dagger;

import com.tl.tplus.base.scope.FragmentScoped;
import com.tl.tplus.detail.api.DetailApiService;
import com.tl.tplus.detail.mvp.DetailContract;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2018/3/2.
 */
@Module
public class DetailModule {

    private DetailContract.IView mIView;

    public DetailModule(DetailContract.IView mIView) {
        this.mIView = mIView;
    }

    @FragmentScoped
    @Provides
    DetailContract.IView providesDetailContractIView() {
        return mIView;
    }

    @FragmentScoped
    @Provides
    DetailApiService providesDetailApiService(Retrofit retrofit) {
        return retrofit.create(DetailApiService.class);
    }
}
