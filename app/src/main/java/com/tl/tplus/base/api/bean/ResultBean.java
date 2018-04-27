package com.tl.tplus.base.api.bean;

/**
 * Created by linfp on 2016/6/28.
 * 封装的结果对象
 */
public class ResultBean<T> extends BaseResultBean {

  private T data;

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
