package com.tl.tplus.cicidetail.mvp;

import com.tl.tplus.base.mvp.BasePresenter;
import com.tl.tplus.base.mvp.BaseView;

import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2018/3/2.
 */

public interface CicidetailContract {
  interface IView extends BaseView<Presenter> {

    void showUi(CicidetailBean cicidetailBean);

    void hideLoading();

    void showLoading();

    void showPrses(CicidetailProsesBean prosesBean);

    void showReview(CicidetailReviewBean reviewBean);
  }

  interface Presenter extends BasePresenter {

    void getDetailData(String data);

    void getProsesListData(String data);

    void getReviewListData(String data);

    void productStatistics(String data);

    List<String> getFirstRateList(String type, List<CicidetailBean.DataBean
        .PriceBean>
        priceBeanList);

    Map<String, String> getBorrowPriceList(String borrowType, String firstRate,
                                           List<CicidetailBean.DataBean
                                               .PriceBean>
                                               priceBeanList);

    List<String> getBorrowTime(String borrowType, String firstRate, String price, List<CicidetailBean
        .DataBean.PriceBean> priceBeanList);

    Map<String, String> getAlgorithm(String type, String price, String time, String firstRate,
                                     List<CicidetailBean
                                         .DataBean
                                         .InterestAlgorithmBean> dataList);

    boolean inPriceOrTimeArea(String gole, String areaMin, String areaMax);

    float getTotalFree(String price, String free, String freeType);

    float getRate(String price, String rate, String time, String rateUnit);

    List<String> getClearList(List<String> list);

  }
}
