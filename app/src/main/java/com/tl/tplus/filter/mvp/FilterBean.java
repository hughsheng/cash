package com.tl.tplus.filter.mvp;

import com.tl.tplus.base.api.bean.BaseResultBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/10.
 */

public class FilterBean extends BaseResultBean {

  /**
   * code : 0
   * message : success
   * data : [{"product_id":"19","product_name":"Tangbull","icon":"http://cdnoss.cashcash
   * .id/cashcash/product/icon/2017-12/f0c4aa64d0b342fd9ff71013c098254c.png",
   * "price_min":"500000.00","price_max":"1500000.00","interest_rate":"0.70",
   * "interest_rate_unit":"hari","total_score":"4.73","time_day_min":"7","time_day_max":"14",
   * "package_name":"com.tangbull.phonerepayment"},{"product_id":"26","product_name":"dana
   * cepat","icon":"http://cdnoss.cashcash
   * .id/cashcash/product/icon/2017-12/f23d554ed336bb45669fd856805827db.png",
   * "price_min":"500000.00","price_max":"1000000.00","interest_rate":"0.70",
   * "interest_rate_unit":"hari","total_score":"4.60","time_day_min":"7","time_day_max":"14",
   * "package_name":"com.money.credit"},{"product_id":"27","product_name":"pinjam uang",
   * "icon":"http://cdnoss.cashcash
   * .id/cashcash/product/icon/2017-12/6a4a1b61e00be25ea120530bd425de90.png",
   * "price_min":"500000.00","price_max":"1000000.00","interest_rate":"0.70",
   * "interest_rate_unit":"hari","total_score":"4.50","time_day_min":"7","time_day_max":"30",
   * "package_name":"com.pinjamuang.loan"},{"product_id":"35","product_name":"RajaUang",
   * "icon":"http://cdnoss.cashcash
   * .id/cashcash/product/icon/2017-12/602de02eedfb31634ef46ffe97b09aa2.png",
   * "price_min":"600000.00","price_max":"2000000.00","interest_rate":"1.00",
   * "interest_rate_unit":"hari","total_score":"4.07","time_day_min":"7","time_day_max":"7",
   * "package_name":"com.rajauang.android"},{"product_id":"37","product_name":"KTA KILAT",
   * "icon":"http://cdnoss.cashcash
   * .id/cashcash/product/icon/2017-12/114145be809859737f98c7006fde9bb4.png",
   * "price_min":"500000.00","price_max":"1000000.00","interest_rate":"0.70",
   * "interest_rate_unit":"hari","total_score":"4.43","time_day_min":"7","time_day_max":"14",
   * "package_name":"com.ktakilat.loan"},{"product_id":"40","product_name":"Pinjaman Rp",
   * "icon":"http://cdnoss.cashcash
   * .id/cashcash/product/icon/2017-12/9af65c4f5930896981d548133f6c360f.png",
   * "price_min":"1000000.00","price_max":"1000000.00","interest_rate":"0.65",
   * "interest_rate_unit":"hari","total_score":"4.13","time_day_min":"7","time_day_max":"7",
   * "package_name":"com.mobanker.pinjamanrp"},{"product_id":"42","product_name":"UangUang",
   * "icon":"http://cdnoss.cashcash
   * .id/cashcash_test/product/icon/2018-01/8dc87c067afd9a15554df39ced0f5afb.png",
   * "price_min":"600000.00","price_max":"1000000.00","interest_rate":"1.00",
   * "interest_rate_unit":"hari","total_score":"3.73","time_day_min":"7","time_day_max":"14",
   * "package_name":"com.uanguang"}]
   */

  private List<DataBean> data;

  public List<DataBean> getData() {
    return data;
  }

  public void setData(List<DataBean> data) {
    this.data = data;
  }

  public static class DataBean {
    /**
     * product_id : 19
     * product_name : Tangbull
     * icon : http://cdnoss.cashcash
     * .id/cashcash/product/icon/2017-12/f0c4aa64d0b342fd9ff71013c098254c.png
     * price_min : 500000.00
     * price_max : 1500000.00
     * interest_rate : 0.70
     * interest_rate_unit : hari
     * total_score : 4.73
     * time_day_min : 7
     * time_day_max : 14
     * package_name : com.tangbull.phonerepayment
     */

    private String product_id;
    private String product_name;
    private String icon;
    private String price_min;
    private String price_max;
    private String interest_rate;
    private String interest_rate_unit;
    private String total_score;
    private String time_day_min;
    private String time_day_max;
    private String package_name;
    private boolean installed;
    private int cap_status;

    public int getCap_status() {
      return cap_status;
    }

    public void setCap_status(int cap_status) {
      this.cap_status = cap_status;
    }

    public boolean isInstalled() {
      return installed;
    }

    public void setInstalled(boolean instanlled) {
      this.installed = instanlled;
    }

    public String getProduct_id() {
      return product_id;
    }

    public void setProduct_id(String product_id) {
      this.product_id = product_id;
    }

    public String getProduct_name() {
      return product_name;
    }

    public void setProduct_name(String product_name) {
      this.product_name = product_name;
    }

    public String getIcon() {
      return icon;
    }

    public void setIcon(String icon) {
      this.icon = icon;
    }

    public String getPrice_min() {
      return price_min;
    }

    public void setPrice_min(String price_min) {
      this.price_min = price_min;
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

    public String getTotal_score() {
      return total_score;
    }

    public void setTotal_score(String total_score) {
      this.total_score = total_score;
    }

    public String getTime_day_min() {
      return time_day_min;
    }

    public void setTime_day_min(String time_day_min) {
      this.time_day_min = time_day_min;
    }

    public String getTime_day_max() {
      return time_day_max;
    }

    public void setTime_day_max(String time_day_max) {
      this.time_day_max = time_day_max;
    }

    public String getPackage_name() {
      return package_name;
    }

    public void setPackage_name(String package_name) {
      this.package_name = package_name;
    }
  }
}
