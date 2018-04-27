package com.tl.tplus.cicidetail.api;

import com.tl.tplus.cicidetail.mvp.CicidetailBean;
import com.tl.tplus.cicidetail.mvp.CicidetailProsesBean;
import com.tl.tplus.cicidetail.mvp.CicidetailReviewBean;
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

public interface CicidetailApiService {

    String DATA = "data";

    @FormUrlEncoded
    @POST(HttpUtils.PRODUCT_INSTALLMENT_GET_INFO)
    Observable<CicidetailBean> getDetailData(
        @Field(DATA) String data);//获取详情页数据

    @FormUrlEncoded
    @POST(HttpUtils.PRODUCT_INSTALLMENT_GET_PROCEDURE)
    Observable<CicidetailProsesBean> getProsesListData(
        @Field(DATA) String data);//获取详情页数据

    @FormUrlEncoded
    @POST(HttpUtils.PRODUCT_INSTALLMENT_GET_REVIEW_LIST)
    Observable<CicidetailReviewBean> getReviewListData(
        @Field(DATA) String data);//获取详情页数据

    @FormUrlEncoded
    @POST(HttpUtils.PRODUCT_STATISTICS)
    Observable<DetailReviewBean> productStatistics(
            @Field(DATA) String data);//产品点击
}