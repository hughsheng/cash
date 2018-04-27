package com.tl.tplus.cicidetailinfo.api;


import com.tl.tplus.cicidetailinfo.mvp.CicidetailInfoBean;
import com.tl.tplus.util.HttpUtils;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2018/3/7.
 *
 */

public interface CicidetailInfoApiService {

  String DATA = "data";

  @FormUrlEncoded
  @POST(HttpUtils.PRODUCT_INSTALLMENT_GET_DETAILS)
  Observable<CicidetailInfoBean> getDetailInfoData(
      @Field(DATA) String data);//获取详情页详细数据
}
