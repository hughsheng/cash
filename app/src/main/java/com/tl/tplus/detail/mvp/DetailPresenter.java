package com.tl.tplus.detail.mvp;

import com.tl.tplus.base.api.bean.ErrorBean;
import com.tl.tplus.base.mvp.HttpDelegate;
import com.tl.tplus.base.rx.ResultSubscriber;
import com.tl.tplus.base.rx.SchedulersCompat;
import com.tl.tplus.detail.api.DetailApiService;
import com.tl.tplus.util.CommonUtil;
import com.tl.tplus.util.ConstanceValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;

/**
 * Created by Administrator on 2018/3/2.
 */

public class DetailPresenter extends HttpDelegate implements DetailContract.Presenter {

    public static final String FREE = "free";
    public static final String FREETYPE = "free_type";
    public static final String RATE = "rate";
    public static final String RATEUNIT = "rate_unit";
    public static final int FREEPERCET = 1;
    public static final int FREENUM = 2;

    DetailContract.IView mIView;
    DetailApiService detailApiService;


    @Inject
    public DetailPresenter(DetailContract.IView mIView, DetailApiService detailApiService) {
        this.mIView = mIView;
        this.detailApiService = detailApiService;
    }

    @Inject
    void setupPresenter() {
        mIView.setPresenter(this);
    }

    @Override
    public void unBindPresent() {

    }

    @Override
    public void getDetailData(String data) {
        Subscription subscription = detailApiService.getDetailData(data)
                .compose(SchedulersCompat.applyIoSchedulers())  //以第一个订阅的线程为准
                .doOnSubscribe(new Action0() {         //在执行前,执行更新对话框(一最近的线程为主,更新界面)
                    @Override
                    public void call() {
                        mIView.showLoading();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(new Action0() {         //无论正常回调还是异常信息,都会调用
                    @Override
                    public void call() {
                        mIView.hideLoading();
                    }
                })
                .subscribe(new ResultSubscriber<Object>() {
                    @Override
                    public void onError(ErrorBean bean) {
                        if (bean.getErrCode() == -1) {
                            mIView.onLoadFail(bean);
                        }
                    }

                    @Override
                    public void onData(Object object) {
                        DetailBean detailBean = (DetailBean) object;
                        mIView.showUi(detailBean);
                    }
                });
        addSubscription(subscription);
    }

    @Override
    public void getProsesListData(String data) {
        Subscription subscription = detailApiService.getProsesListData(data)
                .compose(SchedulersCompat.applyIoSchedulers())  //以第一个订阅的线程为准
                .doOnSubscribe(new Action0() {         //在执行前,执行更新对话框(一最近的线程为主,更新界面)
                    @Override
                    public void call() {
                        mIView.showLoading();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(new Action0() {         //无论正常回调还是异常信息,都会调用
                    @Override
                    public void call() {
                        mIView.hideLoading();
                    }
                })
                .subscribe(new ResultSubscriber<Object>() {
                    @Override
                    public void onError(ErrorBean bean) {
                        if (bean.getErrCode() == -1) {
                            mIView.onLoadFail(bean);
                        }
                    }

                    @Override
                    public void onData(Object object) {
                        DetailProsesBean prosesBean = (DetailProsesBean) object;
                        mIView.showPrses(prosesBean);
                    }
                });
        addSubscription(subscription);
    }

    @Override
    public void getReviewListData(String data) {
        Subscription subscription = detailApiService.getReviewListData(data)
                .compose(SchedulersCompat.applyIoSchedulers())  //以第一个订阅的线程为准
                .doOnSubscribe(new Action0() {         //在执行前,执行更新对话框(一最近的线程为主,更新界面)
                    @Override
                    public void call() {
                        mIView.showLoading();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(new Action0() {         //无论正常回调还是异常信息,都会调用
                    @Override
                    public void call() {
                        mIView.hideLoading();
                    }
                })
                .subscribe(new ResultSubscriber<Object>() {
                    @Override
                    public void onError(ErrorBean bean) {
                        if (bean.getErrCode() == -1) {
                            mIView.onLoadFail(bean);
                        }
                    }

                    @Override
                    public void onData(Object object) {
                        DetailReviewBean reviewBean = (DetailReviewBean) object;
                        mIView.showReview(reviewBean);
                    }
                });
        addSubscription(subscription);
    }


    @Override
    public void productStatistics(String data) {
        Subscription subscription = detailApiService.productStatistics(data)
                .compose(SchedulersCompat.applyIoSchedulers())  //以第一个订阅的线程为准
                .doOnSubscribe(new Action0() {         //在执行前,执行更新对话框(一最近的线程为主,更新界面)
                    @Override
                    public void call() {
//                        mIView.showLoading();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(new Action0() {         //无论正常回调还是异常信息,都会调用
                    @Override
                    public void call() {
//                        mIView.hideLoading();
                    }
                })
                .subscribe(new ResultSubscriber<Object>() {
                    @Override
                    public void onError(ErrorBean bean) {
                        if (bean.getErrCode() == -1) {
                            mIView.onLoadFail(bean);
                        }
                    }

                    @Override
                    public void onData(Object object) {
                    }
                });
        addSubscription(subscription);
    }

    /**
     * 获取借贷金额
     *
     * @param borrowType    借贷类型
     * @param priceBeanList 借贷数据集合
     * @return
     */
    @Override
    public List<String> getBorrowPriceList(String borrowType, List<DetailBean.DataBean.PriceBean>
            priceBeanList) {
        List<String> borrowPriceList = new ArrayList<>();
        for (DetailBean.DataBean.PriceBean priceBean : priceBeanList) {
            if (borrowType.equals(priceBean.getNum())) {
                float pricec = Float.valueOf(priceBean.getPrice());
                borrowPriceList.add("Rp " + CommonUtil.getSortPrice((int) pricec + ""));
            }
        }

        return borrowPriceList;
    }

    /**
     * 获取借贷时间
     *
     * @param borrowType    借贷类型
     * @param borrowNum     借贷金额
     * @param priceBeanList 借贷数据集合
     * @return
     */
    @Override
    public Map<String, Integer> getBorrowTime(String borrowType, String borrowNum, List<DetailBean
            .DataBean.PriceBean> priceBeanList) {
        Map<String, Integer> borrowTime = new HashMap<>();
        for (DetailBean.DataBean.PriceBean priceBean : priceBeanList) {
            float pricec = Float.valueOf(priceBean.getPrice());
            if (borrowType.equals(priceBean.getNum()) && borrowNum.equals((int) pricec + "")) {
                if (priceBean.getTime().size() == 1) {
                    int time_min = Integer.valueOf(priceBean.getTime().get(0).getTime_min());
                    int time_max = Integer.valueOf(priceBean.getTime().get(0).getTime_max());
                    borrowTime.put(ConstanceValue.TIME_MIN, time_min);
                    borrowTime.put(ConstanceValue.TIME_MAX, time_max);
                    borrowTime.put(ConstanceValue.TIMETYPE, 0);
                } else {
                    for (int i = 0; i < priceBean.getTime().size(); i++) {
                        borrowTime.put(i + "", Integer.valueOf(priceBean.getTime().get(i).getTime_min()));
                    }
                    borrowTime.put(ConstanceValue.TIMETYPE, 1);
                }

            }
        }

        return borrowTime;
    }

    @Override
    public Map<String, String> getAlgorithm(String type, String price, String time,
                                            List<DetailBean.DataBean.InterestAlgorithmBean>
                                                    dataList) {
        for (DetailBean.DataBean.InterestAlgorithmBean interestAlgorithmBean : dataList) {
            if (type.equals(interestAlgorithmBean.getNum()) && inPriceOrTimeArea(price,
                    interestAlgorithmBean.getPrice_min(), interestAlgorithmBean
                            .getPrice_max()) && inPriceOrTimeArea(time, interestAlgorithmBean.getTime_min(),
                    interestAlgorithmBean.getTime_max())) {
                Map<String, String> dataMap = new HashMap<>();
                dataMap.put(FREE, interestAlgorithmBean.getFree());
                dataMap.put(FREETYPE, interestAlgorithmBean.getFree_type());
                dataMap.put(RATE, interestAlgorithmBean.getRate());
                dataMap.put(RATEUNIT, interestAlgorithmBean.getRate_unit());
                return dataMap;
            }

        }

        return null;
    }

    @Override
    public boolean inPriceOrTimeArea(String gole, String areaMin, String areaMax) {
        float golef = Float.valueOf(gole);
        float goleMinf = Float.valueOf(areaMin);
        float goleMaxf = Float.valueOf(areaMax);
        if (golef <= goleMaxf && golef >= goleMinf) {
            return true;
        } else
            return false;
    }

    @Override
    public float getTotalFree(String price, String free, String freeType) {
        float pricef = Float.valueOf(price);
        int freeTypef = Integer.valueOf(freeType);
        float freef;
        float freeNum = 0;//手续费
        if (freeTypef == FREEPERCET) {
            freef = Float.valueOf(free) / 100;
            freeNum = pricef * freef;
        } else if (freeTypef == FREENUM) {
            freef = Float.valueOf(free);
            freeNum = freef;
        }

        return freeNum;
    }

    @Override
    public float getRate(String price, String rate, String time, String rateUnit) {
        float pricef = Float.valueOf(price);
        float timef = Float.valueOf(time);
        float ratef = Float.valueOf(rate) / 100;
        float rateNum = 0;
        if (rateUnit.equals("days")) {
            rateNum = pricef * ratef * timef;//日利息
        } else if (rateUnit.equals("month")) {
            rateNum = pricef * ratef * timef / 30;//月利息
        } else if (rateUnit.equals("default")) {
            rateNum = pricef * ratef;//总利息
        }

        return rateNum;
    }


}
