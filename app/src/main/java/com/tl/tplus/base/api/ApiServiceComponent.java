package com.tl.tplus.base.api;

import com.tl.tplus.base.app.AppModule;
import com.tl.tplus.cicidetail.dagger.CicidetailComponent;
import com.tl.tplus.cicidetail.dagger.CicidetailModule;
import com.tl.tplus.cicidetailinfo.dagger.CicidetailInfoComponent;
import com.tl.tplus.cicidetailinfo.dagger.CicidetailInfoModule;
import com.tl.tplus.detail.dagger.DetailComponent;
import com.tl.tplus.detail.dagger.DetailModule;
import com.tl.tplus.detail.dagger.DetailWebComponent;
import com.tl.tplus.detail.dagger.DetailWebModule;
import com.tl.tplus.detailinfo.dagger.DetailInfoComponent;
import com.tl.tplus.detailinfo.dagger.DetailInfoModule;
import com.tl.tplus.filter.dagger.FilterComponent;
import com.tl.tplus.filter.dagger.FilterModule;
import com.tl.tplus.home.dagger.HomeComponent;
import com.tl.tplus.home.dagger.HomePresenterModule;
import com.tl.tplus.instanllapp.dagger.InstallComponent;
import com.tl.tplus.instanllapp.dagger.InstallModule;
import com.tl.tplus.strategy.dagger.StrategyComponent;
import com.tl.tplus.strategy.dagger.StrategyModule;
import com.tl.tplus.util.SharedPreferencesUtils;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2018/2/15.
 * dagger2依赖注入管理类
 */

@Singleton
@Component(modules = {AppModule.class, ApiServiceModule.class})
public interface ApiServiceComponent {
  Retrofit getRetrofit();

  //首页banner
  HomeComponent homeComponent(HomePresenterModule homePresenterModule);

  //dana详情页
  DetailComponent detailComponent(DetailModule detailModule);

  //dana详情页
  DetailWebComponent detailWebComponent(DetailWebModule detailWebModule);

  //dana详情页详细
  DetailInfoComponent detailInfoComponent(DetailInfoModule detailInfoModule);

  //dana详情页
  CicidetailComponent detailComponent(CicidetailModule detailModule);

  //cici详情页详细
  CicidetailInfoComponent ciciDetailInfoComponent(CicidetailInfoModule cicidetailInfoModule);

  //缓存存储
  SharedPreferencesUtils getSharedPreferencesUtils();

  //filterList
  FilterComponent filterComponent(FilterModule filterModule);


  //strategyList
  StrategyComponent strategyComponent(StrategyModule strategyModule);

  //已安装列表
  InstallComponent installComponent(InstallModule installModule);

}
