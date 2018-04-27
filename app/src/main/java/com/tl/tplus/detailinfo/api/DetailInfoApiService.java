package com.tl.tplus.detailinfo.api;


import com.tl.tplus.detailinfo.mvp.DetailInfoBean;
import com.tl.tplus.util.HttpUtils;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2018/3/7.
 *
 */

public interface DetailInfoApiService {

  String DATA = "data";

  @FormUrlEncoded
  @POST(HttpUtils.PRODUCT_CASH_CREDIT_GET_DETAILS)
  Observable<DetailInfoBean> getDetailInfoData(
      @Field(DATA) String data);//获取详情页详细数据
}
