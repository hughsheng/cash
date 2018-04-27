package com.tl.tplus.service.api;


import com.tl.tplus.strategy.mvp.StrategyBean;
import com.tl.tplus.util.HttpUtils;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2018/3/10.
 *
 */

public interface ServiceApiService {
  String DATA = "data";

  @FormUrlEncoded
  @POST(HttpUtils.FILTER_WORDS_INDEX)
  Observable<StrategyBean> getFilterListData(
          @Field(DATA) String data);//获取详情页详细数据
}
