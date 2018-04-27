package com.tl.tplus.cicidetail.dagger;

import com.tl.tplus.base.scope.FragmentScoped;
import com.tl.tplus.cicidetail.api.CicidetailApiService;
import com.tl.tplus.cicidetail.mvp.CicidetailContract;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2018/3/2.
 */
@Module
public class CicidetailModule {

    private CicidetailContract.IView mIView;

    public CicidetailModule(CicidetailContract.IView mIView) {
        this.mIView = mIView;
    }

    @FragmentScoped
    @Provides
    CicidetailContract.IView providesDetailContractIView() {
        return mIView;
    }

    @FragmentScoped
    @Provides
    CicidetailApiService providesDetailApiService(Retrofit retrofit) {
        return retrofit.create(CicidetailApiService.class);
    }
}
