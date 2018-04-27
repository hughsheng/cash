package com.tl.tplus.instanllapp.api;


import com.tl.tplus.instanllapp.mvp.InstallBean;
import com.tl.tplus.util.HttpUtils;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2018/3/10.
 *
 */

public interface InstallApiService {
  String DATA = "data";

  @FormUrlEncoded
  @POST(HttpUtils.PRODUCT_INSTALLED_GET_LIST)
  Observable<InstallBean> getFilterListData(
      @Field(DATA) String data);//获取详情页详细数据
}
