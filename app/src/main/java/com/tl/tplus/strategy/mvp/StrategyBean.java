package com.tl.tplus.strategy.mvp;

import com.tl.tplus.base.api.bean.BaseResultBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/10.
 */

public class StrategyBean extends BaseResultBean {

  private List<DataBean> data;

  public List<DataBean> getData() {
    return data;
  }

  public void setData(List<DataBean> data) {
    this.data = data;
  }

  public static class DataBean {
    /**
     * pid : 19
     * icon : http://cdnoss.cashcash
     * .id/cashcash/product/icon/2017-12/f0c4aa64d0b342fd9ff71013c098254c.png
     * product_name : Tangbull
     * price_max : 1500000.00
     * interest_rate : 0.70
     * interest_rate_unit : Hari
     * loan_time_day : 1
     * total_score : 4.73
     * package_name : com.tangbull.phonerepayment
     */

    private String pid;
    private String icon;
    private String product_name;
    private String price_max;
    private String interest_rate;
    private String interest_rate_unit;
    private String loan_time_day;
    private String total_score;
    private String package_name;
    private boolean installed;

    public boolean isInstalled() {
      return installed;
    }

    public void setInstalled(boolean installed) {
      this.installed = installed;
    }

    public String getPid() {
      return pid;
    }

    public void setPid(String pid) {
      this.pid = pid;
    }

    public String getIcon() {
      return icon;
    }

    public void setIcon(String icon) {
      this.icon = icon;
    }

    public String getProduct_name() {
      return product_name;
    }

    public void setProduct_name(String product_name) {
      this.product_name = product_name;
    }

    public String getPrice_max() {
      return price_max;
    }

    public void setPrice_max(String price_max) {
      this.price_max = price_max;
    }

    public String getInterest_rate() {
      return interest_rate;
    }

    public void setInterest_rate(String interest_rate) {
      this.interest_rate = interest_rate;
    }

    public String getInterest_rate_unit() {
      return interest_rate_unit;
    }

    public void setInterest_rate_unit(String interest_rate_unit) {
      this.interest_rate_unit = interest_rate_unit;
    }

    public String getLoan_time_day() {
      return loan_time_day;
    }

    public void setLoan_time_day(String loan_time_day) {
      this.loan_time_day = loan_time_day;
    }

    public String getTotal_score() {
      return total_score;
    }

    public void setTotal_score(String total_score) {
      this.total_score = total_score;
    }

    public String getPackage_name() {
      return package_name;
    }

    public void setPackage_name(String package_name) {
      this.package_name = package_name;
    }
  }
}
