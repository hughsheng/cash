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
 * Created by sztangli on 2018-2-28.
 */

public class HomeDanaPresenter extends HttpDelegate implements HomeDanaContract.Presenter {


    private final HomeDanaContract.IView mIView;
    private final HomeApiService homeApiService;

    @Inject
    public HomeDanaPresenter(HomeDanaContract.IView mIView, HomeApiService homeApiService) {
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
    public void getBannerData(String data, final int type) {
        Subscription subscription = homeApiService.getBanner(data)
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
                        HomeBannerBean homeBannerBean = (HomeBannerBean) object;
                        homeBannerBean.getData().setBannerType(type);
                        mIView.showBanner(homeBannerBean);
                    }
                });
        addSubscription(subscription);
    }

    @Override
    public void getDanaDataList(String data) {
        Subscription subscription = homeApiService.getDanaDataList(data)
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
                        HomeListBean homeListBean = (HomeListBean) object;
                        mIView.showList(homeListBean);
                    }
                });
        addSubscription(subscription);
    }

    @Override
    public void getFilterDataList(String data) {
        Subscription subscription = homeApiService.getfilterData(data)
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
                        FilterListBean filterListBean = (FilterListBean) object;
                        mIView.saveFilterData(filterListBean);
                    }
                });
        addSubscription(subscription);
    }

    @Override
    public void getGuid(String data) {
        Subscription subscription = homeApiService.getguid(data)
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
                        GuidBean guidBean = (GuidBean) object;
                        mIView.saveGuid(guidBean);
                    }
                });
        addSubscription(subscription);
    }

    @Override
    public void rePortInstallApp(String data) {
        Subscription subscription = homeApiService.rePortInstall(data)
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
                        GuidBean guidBean = (GuidBean) object;
                        mIView.saveInstallState(guidBean);
                    }
                });
        addSubscription(subscription);
    }

    @Override
    public void getNewVersion(String data) {
        Subscription subscription = homeApiService.getNewVersion(data)
                .compose(SchedulersCompat.applyIoSchedulers())  //以第一个订阅的线程为准
                .doOnSubscribe(new Action0() {         //在执行前,执行更新对话框(一最近的线程为主,更新界面)
                    @Override
                    public void call() {
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(new Action0() {         //无论正常回调还是异常信息,都会调用
                    @Override
                    public void call() {
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
                        UpdateBean updateBean = (UpdateBean) object;
                        mIView.showNewVersion(updateBean);
                    }
                });
        addSubscription(subscription);
    }
}
