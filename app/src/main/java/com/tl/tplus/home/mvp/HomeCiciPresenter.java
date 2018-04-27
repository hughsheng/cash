package com.tl.tplus.home.mvp;

import com.tl.tplus.base.api.bean.ErrorBean;
import com.tl.tplus.base.mvp.HttpDelegate;
import com.tl.tplus.base.rx.ResultSubscriber;
import com.tl.tplus.base.rx.SchedulersCompat;
import com.tl.tplus.home.api.HomeApiService;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;

/**
 * Created by sztangli on 2018-3-2.
 *
 */

public class HomeCiciPresenter extends HttpDelegate implements HomeCiciContract.Presenter {

  private final HomeCiciContract.IView mIView;
  private final HomeApiService homeApiService;

  @Inject
  public HomeCiciPresenter(HomeCiciContract.IView mIView, HomeApiService homeApiService) {
    this.mIView = mIView;
    this.homeApiService = homeApiService;
  }

  @Inject
  void setupPresenter() {
    mIView.setPresenter(this);
  }

  @Override
  public void unBindPresent() {

  }

  @Override
  public void getCiciDataList(String data, final int loadType) {
    Subscription subscription = homeApiService.getCiciDataList(data)
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
            HomeCiciListBean homeCiciListBean = (HomeCiciListBean) object;
            homeCiciListBean.setLoadType(loadType);
              mIView.showList(homeCiciListBean);
          }
        });
    addSubscription(subscription);
  }

}
