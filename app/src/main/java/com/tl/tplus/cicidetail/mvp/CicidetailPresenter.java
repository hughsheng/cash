package com.tl.tplus.cicidetail.mvp;

import com.tl.tplus.base.api.bean.ErrorBean;
import com.tl.tplus.base.mvp.HttpDelegate;
import com.tl.tplus.base.rx.ResultSubscriber;
import com.tl.tplus.base.rx.SchedulersCompat;
import com.tl.tplus.cicidetail.api.CicidetailApiService;

import java.util.ArrayList;
import java.util.Collections;
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

public class CicidetailPresenter extends HttpDelegate implements CicidetailContract.Presenter {

  public static final String FREE = "free";
  public static final String FREETYPE = "free_type";
  public static final String RATE = "rate";
  public static final String RATEUNIT = "rate_unit";
  public static final int FREEPERCET = 1;
  public static final int FREENUM = 2;
  public static final String FIRSTRATE = "first_rate";
  public static final String PRICEMIN = "price_min";
  public static final String PRICEMAX = "price_max";

  CicidetailContract.IView mIView;
  CicidetailApiService cicidetailApiService;


  @Inject
  public CicidetailPresenter(CicidetailContract.IView mIView, CicidetailApiService
      cicidetailApiService) {
    this.mIView = mIView;
    this.cicidetailApiService = cicidetailApiService;
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
    Subscription subscription = cicidetailApiService.getDetailData(data)
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
            CicidetailBean cicidetailBean = (CicidetailBean) object;
            mIView.showUi(cicidetailBean);
          }
        });
    addSubscription(subscription);
  }

  @Override
  public void getProsesListData(String data) {
    Subscription subscription = cicidetailApiService.getProsesListData(data)
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
            CicidetailProsesBean prosesBean = (CicidetailProsesBean) object;
            mIView.showPrses(prosesBean);
          }
        });
    addSubscription(subscription);
  }

  @Override
  public void getReviewListData(String data) {
    Subscription subscription = cicidetailApiService.getReviewListData(data)
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
            CicidetailReviewBean reviewBean = (CicidetailReviewBean) object;
            mIView.showReview(reviewBean);
          }
        });
    addSubscription(subscription);
  }

  @Override
  public void productStatistics(String data) {
    Subscription subscription = cicidetailApiService.productStatistics(data)
            .compose(SchedulersCompat.applyIoSchedulers())  //以第一个订阅的线程为准
            .doOnSubscribe(new Action0() {         //在执行前,执行更新对话框(一最近的线程为主,更新界面)
              @Override
              public void call() {
//                mIView.showLoading();
              }
            })
            .subscribeOn(AndroidSchedulers.mainThread())
            .doOnTerminate(new Action0() {         //无论正常回调还是异常信息,都会调用
              @Override
              public void call() {
//                mIView.hideLoading();
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
                CicidetailReviewBean reviewBean = (CicidetailReviewBean) object;
                mIView.showReview(reviewBean);
              }
            });
    addSubscription(subscription);
  }

  @Override
  public List<String> getFirstRateList(String type, List<CicidetailBean.DataBean.PriceBean>
      priceBeanList) {

    List<String> firstRateList = new ArrayList<>();

    if (type == null) {
      type = "0";
    }

    if (priceBeanList != null && priceBeanList.size() > 0) {
      for (CicidetailBean.DataBean.PriceBean priceBean : priceBeanList) {
        if (type.equals(priceBean.getNum())) {
          firstRateList.addAll(priceBean.getDown_payment_rate());
        }
      }


      return null;
    }

    return null;
  }


  /**
   * 获取借贷金额
   *
   * @param borrowType    借贷类型
   * @param priceBeanList 借贷数据集合
   * @return
   */
  @Override
  public Map<String, String> getBorrowPriceList(String borrowType, String firstRate,
                                                List<CicidetailBean.DataBean
                                                    .PriceBean>
                                                    priceBeanList) {
    if (borrowType == null) {
      borrowType = "0";
    }
    Map<String, String> priceArea = new HashMap<>();
    if (priceBeanList != null && priceBeanList.size() > 0) {
      for (CicidetailBean.DataBean.PriceBean priceBean : priceBeanList) {
        if (borrowType.equals(priceBean.getNum()) && priceBean.getDown_payment_rate().contains
            (firstRate)) {
          priceArea.put("price_min", priceBean.getPrice_min());
          priceArea.put("price_max", priceBean.getPrice_max());
        }
      }
      return priceArea;
    }


    return null;
  }

  /**
   * 获取借贷时间
   *
   * @param borrowType    借贷类型
   * @param priceBeanList 借贷数据集合
   * @return
   */
  @Override
  public List<String> getBorrowTime(String borrowType, String firstRate, String price,
                                    List<CicidetailBean
                                        .DataBean.PriceBean> priceBeanList) {

    List<String> borrowTime = new ArrayList<>();
    if (borrowType == null) {
      borrowType = "0";
    }
    for (CicidetailBean.DataBean.PriceBean priceBean : priceBeanList) {
      String price_max;
      if(priceBean.getPrice_max().equals("0.00")){
         price_max=Float.MAX_VALUE+"";
      }else {
        price_max=priceBean.getPrice_max();
      }
        float pricef=Float.valueOf(price);
        float priceMaxf=Float.valueOf(price_max);
        float priceMinf=Float.valueOf(priceBean.getPrice_min());

      if (borrowType.equals(priceBean.getNum()) && priceBean.getDown_payment_rate().contains
          (firstRate)&&pricef<=priceMaxf&&pricef>=priceMinf) {
        for (int i = 0; i < priceBean.getTime().size(); i++) {
          borrowTime.add(priceBean.getTime().get(i).getTime_min());
        }
      }
    }


    return null;
  }

  @Override
  public Map<String, String> getAlgorithm(String type, String price, String time, String firstRate,
                                          List<CicidetailBean.DataBean.InterestAlgorithmBean>
                                              dataList) {
    for (CicidetailBean.DataBean.InterestAlgorithmBean interestAlgorithmBean : dataList) {

      if (type.equals(interestAlgorithmBean.getNum()) && isEqual(firstRate, interestAlgorithmBean
          .getDown_payment_rate()) && inPriceOrTimeArea(price, interestAlgorithmBean
          .getPrice_min(), interestAlgorithmBean.getPrice_max()) && inTimeArea(time,
          interestAlgorithmBean.getTime_min(), interestAlgorithmBean.getTime_max())) {
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put(FREE, interestAlgorithmBean.getFree());
        dataMap.put(FREETYPE, interestAlgorithmBean.getFree_type());
        dataMap.put(RATE, interestAlgorithmBean.getRate());
        dataMap.put(RATEUNIT, interestAlgorithmBean.getRate_unit());
        dataMap.put(FIRSTRATE, interestAlgorithmBean.getDown_payment_rate());
        dataMap.put(PRICEMIN, interestAlgorithmBean.getPrice_min());
        dataMap.put(PRICEMAX, interestAlgorithmBean.getPrice_max());
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
    if ((int) goleMaxf == 0) {
      goleMaxf = Float.MAX_VALUE;
    }
    if (golef <= goleMaxf && golef >= goleMinf) {
      return true;
    } else
      return false;
  }


  private boolean inTimeArea(String time, String time_min, String time_max) {
    String rightTime;
    if(time.contains("Hari")){
      rightTime=time.substring(0,time.length()-5);
    }else {
      rightTime=time;
    }
    int gole = Integer.valueOf(rightTime);
    int min = Integer.valueOf(time_min);
    int max = Integer.valueOf(time_max);
    if (gole >= min && gole <= max) {
      return true;
    } else {
      return false;
    }
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


  private boolean isContain(String goal, List<String> list) {

    for (String s : list) {
      float rate = Float.valueOf(s);
      if (goal.equals((int) rate + "")) {
        return true;
      }
    }
    return false;
  }


  private boolean isEqual(String goal, String list) {
    float rate = Float.valueOf(list);
    if (goal.equals((int) rate + "")) {
      return true;
    }
    return false;
  }

  @Override
  public float getRate(String price, String rate, String time, String rateUnit) {
    float pricef = Float.valueOf(price);
    float timef;
    if (time.indexOf("Hari") > 0) {
      timef = Float.valueOf(time.substring(0, time.length() - 5));
    } else {
      timef = Float.valueOf(time);
    }
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

  //处理集合数据
  @Override
  public List<String> getClearList(List<String> list) {
    boolean isInt=!list.get(0).contains(".");
    List<String> noRepeatList=new ArrayList<>();
    List<Integer> integerList=new ArrayList<>();
    List<String> resultList=new ArrayList<>();


    if(isInt){
      //去重
      for(String s:list){
        if(!noRepeatList.contains(s)){
          noRepeatList.add(s);
        }
      }
    //排序
     for(String s:noRepeatList){
        integerList.add(Integer.valueOf(s));
     }

      Collections.sort(integerList);
    //结果
      for(Integer i:integerList){
        resultList.add(i+"");
      }

      return resultList;
    }else {

      //去重
      for(String s:list){
        if(!noRepeatList.contains(s)){
          noRepeatList.add(s);
        }
      }
      //排序
      for(String s:noRepeatList){
        float f=Float.valueOf(s);
        integerList.add((int)f);
      }

      Collections.sort(integerList);
      //结果
      for(Integer i:integerList){
        resultList.add(i+"");
      }

      return resultList;

    }

  }

}
