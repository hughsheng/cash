package com.tl.tplus.service;

import android.content.Intent;
import android.os.IBinder;

import com.alibaba.fastjson.JSON;
import com.tl.tplus.base.api.bean.ErrorBean;
import com.tl.tplus.base.api.bean.RequestBean;
import com.tl.tplus.base.app.CashApplication;
import com.tl.tplus.base.rx.ResultSubscriber;
import com.tl.tplus.base.rx.SchedulersCompat;
import com.tl.tplus.home.api.HomeApiService;
import com.tl.tplus.util.CommonUtil;
import com.tl.tplus.util.ConstanceValue;
import com.xdandroid.hellodaemon.AbsWorkService;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;

public class TraceService extends AbsWorkService {

    //是否 任务完成, 不再需要服务运行?
    public static boolean sShouldStopService;
    public static Disposable sDisposable;

    private CashApplication cashApplication;

    public static void stopService() {
        //我们现在不再需要服务运行了, 将标志位置为 true
        sShouldStopService = true;
        //取消对任务的订阅
        if (sDisposable != null) sDisposable.dispose();
        //取消 Job / Alarm / Subscription
        cancelJobAlarmSub();
    }

    /**
     * 是否 任务完成, 不再需要服务运行?
     *
     * @return 应当停止服务, true; 应当启动服务, false; 无法判断, 什么也不做, null.
     */
    @Override
    public Boolean shouldStopService(Intent intent, int flags, int startId) {
        return sShouldStopService;
    }

    @Override
    public void startWork(Intent intent, int flags, int startId) {
        statisticsDayLive();
        sDisposable = Observable
                .interval(4, TimeUnit.HOURS)
                //取消任务时取消定时唤醒
                .doOnDispose(() -> {
//                    cancelJobAlarmSub();
                })
                .subscribe(count -> {
                    statisticsDayLive();
                });

    }

    private void statisticsDayLive(){
        cashApplication=CashApplication.getInstance();
        HomeApiService homeApiService = cashApplication.getApiServiceComponent().getRetrofit()
                .create(HomeApiService.class);
        RequestBean requestBean = JSON.parseObject(ConstanceValue.COMMREQUESTBEAN, RequestBean
                .class);
        String sign = CommonUtil.RequestSignData(requestBean);
        requestBean.setSign(sign);
        requestBean.setTimestamp(System.currentTimeMillis() / 1000);
        String data = JSON.toJSONString(requestBean);

        Subscription subscription = homeApiService.statisticsDayLive(data)
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
                    }

                    @Override
                    public void onData(Object object) {
                    }
                });
    }

    @Override
    public void stopWork(Intent intent, int flags, int startId) {
        stopService();
    }

    /**
     * 任务是否正在运行?
     *
     * @return 任务正在运行, true; 任务当前不在运行, false; 无法判断, 什么也不做, null.
     */
    @Override
    public Boolean isWorkRunning(Intent intent, int flags, int startId) {
        //若还没有取消订阅, 就说明任务仍在运行.
        return sDisposable != null && !sDisposable.isDisposed();
    }

    @Override
    public IBinder onBind(Intent intent, Void v) {
        return null;
    }

    @Override
    public void onServiceKilled(Intent rootIntent) {

    }
}
