package com.tl.tplus.detail.mvp;

import com.tl.tplus.base.mvp.BasePresenter;
import com.tl.tplus.base.mvp.BaseView;

import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2018/3/2.
 *
 */

public interface DetailContract {
    interface IView extends BaseView<Presenter> {

        void showUi(DetailBean detailBean);

        void hideLoading();

        void showLoading();

        void showPrses(DetailProsesBean prosesBean);

        void showReview(DetailReviewBean reviewBean);
    }

    interface Presenter extends BasePresenter {

        void getDetailData(String data);

        void getProsesListData(String data);

        void getReviewListData(String data);

        void productStatistics(String data);

        List<String> getBorrowPriceList(String borrowType, List<DetailBean.DataBean.PriceBean>
            priceBeanList);

        Map<String, Integer> getBorrowTime(String borrowType, String borrowNum, List<DetailBean
            .DataBean.PriceBean> priceBeanList);

        Map<String, String> getAlgorithm(String type, String price, String time, List<DetailBean
            .DataBean
            .InterestAlgorithmBean> dataList);

        boolean inPriceOrTimeArea(String gole, String areaMin, String areaMax);

        float getTotalFree(String price, String free, String freeType);

        float getRate(String price, String rate, String time, String rateUnit);
    }
}
