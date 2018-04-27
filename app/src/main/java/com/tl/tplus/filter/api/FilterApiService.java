package com.tl.tplus.filter.api;


import com.tl.tplus.filter.mvp.FilterBean;
import com.tl.tplus.util.HttpUtils;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2018/3/10.
 */

public interface FilterApiService {
  String DATA = "data";

  @FormUrlEncoded
  @POST(HttpUtils.FAST_SELECT_PRODUCT_SEARCH_LIST)
  Observable<FilterBean> getFilterListData(
      @Field(DATA) String data);//获取详情页详细数据
}
