package com.tl.tplus.base.rx;

import com.tl.tplus.base.api.bean.ErrorBean;
import com.google.gson.JsonSyntaxException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by linfp on 2016/6/27.
 * 封装的订阅模式,只返回处理结果
 */
public abstract class ResultSubscriber<T> extends Subscriber<T> {
  /**
   * 可供子类复写
   * 不是必须实现的
   */
  @Override
  public void onCompleted() {

  }

  @Override
  public void onError(Throwable e) {
    e.printStackTrace();  //打印异常信息
    ErrorBean errorBean = new ErrorBean();
    if (e instanceof HttpException) {  //网络异常
      errorBean.setErrCode(-1);
      errorBean.setErrMessage("请确定网络通畅");
    } else if (e instanceof SocketTimeoutException) {
      errorBean.setErrCode(-1);
      errorBean.setErrMessage("链接超时,请稍后重试");
    } else if (e instanceof JsonSyntaxException) {  //解析异常
      errorBean.setErrCode(-1);
      errorBean.setErrMessage("请确定网络通畅");
    } else if (e instanceof ConnectException) {
      errorBean.setErrCode(-1);
      errorBean.setErrMessage("请确定网络通畅");
    } else {
      errorBean.setErrCode(-1);
      errorBean.setErrMessage("请确定网络通畅");
    }

    onError(errorBean);
  }

  @Override
  public void onNext(T t) {
    onData(t);
  }

  public abstract void onError(ErrorBean errorBean);

  public abstract void onData(T t);

}
