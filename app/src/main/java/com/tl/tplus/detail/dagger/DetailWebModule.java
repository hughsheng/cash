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
public class DetailWebModule {

    private DetailContract.IView mIView;

    public DetailWebModule(DetailContract.IView mIView) {
        this.mIView = mIView;
    }

    @FragmentScoped
    @Provides
    DetailContract.IView providesDetailWebContractIView() {
        return mIView;
    }

    @FragmentScoped
    @Provides
    DetailApiService providesDetailWebApiService(Retrofit retrofit) {
        return retrofit.create(DetailApiService.class);
    }
}
