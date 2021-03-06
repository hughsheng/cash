package com.tl.tplus.detailinfo.mvp;

import com.tl.tplus.base.api.bean.ErrorBean;
import com.tl.tplus.base.mvp.HttpDelegate;
import com.tl.tplus.base.rx.ResultSubscriber;
import com.tl.tplus.base.rx.SchedulersCompat;
import com.tl.tplus.detailinfo.api.DetailInfoApiService;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;

/**
 * Created by Administrator on 2018/3/7.
 */

public class DetailInfoPresenter extends HttpDelegate implements DetailInfoContract.Presenter {

  DetailInfoContract.IView mIView;
  DetailInfoApiService detailInfoApiService;

  @Inject
  public DetailInfoPresenter(DetailInfoContract.IView mIView, DetailInfoApiService
      detailInfoApiService) {
    this.mIView = mIView;
    this.detailInfoApiService = detailInfoApiService;
  }

  @Inject
  void setupPresenter() {
    mIView.setPresenter(this);
  }


  @Override
  public void unBindPresent() {

  }

  @Override
  public void getUIData(String data) {
    Subscription subscription = detailInfoApiService.getDetailInfoData(data)
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
            DetailInfoBean detailInfoBean = (DetailInfoBean) object;
            mIView.showUi(detailInfoBean);
          }
        });
    addSubscription(subscription);
  }
}
