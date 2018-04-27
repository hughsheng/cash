package com.tl.tplus.home.api;

import com.tl.tplus.home.mvp.FilterListBean;
import com.tl.tplus.home.mvp.GuidBean;
import com.tl.tplus.home.mvp.HomeBannerBean;
import com.tl.tplus.home.mvp.HomeCiciListBean;
import com.tl.tplus.home.mvp.HomeListBean;
import com.tl.tplus.home.mvp.UpdateBean;
import com.tl.tplus.util.HttpUtils;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;


/**
 * Created by Administrator on 2018/2/22.
 * 首页数据api
 */

public interface HomeApiService {

  String DATA="data";

  @FormUrlEncoded
  @POST(HttpUtils.HOME_GET_BANNER)
  Observable<HomeBannerBean> getBanner(
      @Field(DATA) String data);//首页banner类型


  @FormUrlEncoded
  @POST(HttpUtils.PRODUCT_CASH_CREDIT_GET_LIST)
  Observable<HomeListBean> getDanaDataList(
      @Field(DATA) String data);//首页现金贷列表


  @FormUrlEncoded
  @POST(HttpUtils.PRODUCT_INSTALLMENT_GET_LIST)
  Observable<HomeCiciListBean> getCiciDataList(
      @Field(DATA) String data);//首页分期贷列表


  @FormUrlEncoded
  @POST(HttpUtils.FAST_SELECT_PRODUCT_SELECT_LIST)
  Observable<FilterListBean> getfilterData(
      @Field(DATA) String data);//首页分期贷列表


  @FormUrlEncoded
  @POST(HttpUtils.TOURIST_GET_USER_ID)
  Observable<GuidBean> getguid(
      @Field(DATA) String data);//获取用户guid


  @FormUrlEncoded
  @POST(HttpUtils.PACKAGE_UPLOAD_TOURIST_PACKAGE)
  Observable<GuidBean> rePortInstall(
      @Field(DATA) String data);//安装和卸载app时调用


  @FormUrlEncoded
  @POST(HttpUtils.VERSION_UPDATE_INDEX)
  Observable<UpdateBean> getNewVersion(
      @Field(DATA) String data);//获取更新版本信息

  @FormUrlEncoded
  @POST(HttpUtils.STATISTICS)
  Observable<UpdateBean> statisticsDayLive(
          @Field(DATA) String data);//统计日活

}
