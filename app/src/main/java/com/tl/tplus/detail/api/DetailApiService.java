package com.tl.tplus.detail.api;

import com.tl.tplus.detail.mvp.DetailBean;
import com.tl.tplus.detail.mvp.DetailProsesBean;
import com.tl.tplus.detail.mvp.DetailReviewBean;
import com.tl.tplus.util.HttpUtils;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2018/3/2.
 *
 */

public interface DetailApiService {

    String DATA = "data";

    @FormUrlEncoded
    @POST(HttpUtils.PRODUCT_CASH_CREDIT_GET_INFO)
    Observable<DetailBean> getDetailData(
        @Field(DATA) String data);//获取详情页数据

    @FormUrlEncoded
    @POST(HttpUtils.PRODUCT_CASH_CREDIT_GET_PROCEDURE)
    Observable<DetailProsesBean> getProsesListData(
        @Field(DATA) String data);//获取详情页数据

    @FormUrlEncoded
    @POST(HttpUtils.PRODUCT_CASH_CREDIT_GET_REVIEW_LIST)
    Observable<DetailReviewBean> getReviewListData(
        @Field(DATA) String data);//获取详情页数据

    @FormUrlEncoded
    @POST(HttpUtils.PRODUCT_STATISTICS)
    Observable<DetailReviewBean> productStatistics(
            @Field(DATA) String data);//产品点击
}