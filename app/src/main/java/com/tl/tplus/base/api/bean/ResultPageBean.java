package com.tl.tplus.base.api.bean;

/**
 * Created by linfp on 2016/6/28.
 * 封装的结果对象
 */
public class ResultPageBean<T> extends BaseResultBean {

  private T data;

  private PageBean page;

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public PageBean getPage() {
    return page;
  }

  public void setPage(PageBean page) {
    this.page = page;
  }
}
