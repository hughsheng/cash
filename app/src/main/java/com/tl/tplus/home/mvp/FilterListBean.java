package com.tl.tplus.home.mvp;

import com.tl.tplus.base.api.bean.BaseResultBean;

import java.util.List;

/**
 * Created by sztangli on 2018-3-5.
 *
 */

public class FilterListBean extends BaseResultBean {

  /**
   * code : 0
   * message : success
   * data : {"select_money":[{"money_id":"1","value":"Rp 200.000-1.000.000"},{"money_id":"2",
   * "value":"Rp 1.001.000-2.000.000"},{"money_id":"3","value":"Rp 2.001.000-5.000.000"},
   * {"money_id":"4","value":"Diatas Rp 5.000.000"}],"select_time":[{"time_id":"1",
   * "value":"Dalam 10 hari"},{"time_id":"2","value":"11-20 hari"},{"time_id":"3","value":"21-30
   * hari"},{"time_id":"4","value":"Diatas 30 hari"}],"select_address":[{"address_id":"2",
   * "value":"Seluruh Indonesia"},{"address_id":"1","value":"Jakarta"},{"address_id":"12",
   * "value":"Surabaya"},{"address_id":"20","value":"Bandung"}],
   * "select_interests":[{"interest_id":"1","value":"Terpopuler"},{"interest_id":"2",
   * "value":"Bunga terendah"}]}
   */


  private DataBean data;

  public DataBean getData() {
    return data;
  }

  public void setData(DataBean data) {
    this.data = data;
  }

  public static class DataBean {
    private List<SelectMoneyBean> select_money;
    private List<SelectTimeBean> select_time;
    private List<SelectAddressBean> select_address;
    private List<SelectInterestsBean> select_interests;

    public List<SelectMoneyBean> getSelect_money() {
      return select_money;
    }

    public void setSelect_money(List<SelectMoneyBean> select_money) {
      this.select_money = select_money;
    }

    public List<SelectTimeBean> getSelect_time() {
      return select_time;
    }

    public void setSelect_time(List<SelectTimeBean> select_time) {
      this.select_time = select_time;
    }

    public List<SelectAddressBean> getSelect_address() {
      return select_address;
    }

    public void setSelect_address(List<SelectAddressBean> select_address) {
      this.select_address = select_address;
    }

    public List<SelectInterestsBean> getSelect_interests() {
      return select_interests;
    }

    public void setSelect_interests(List<SelectInterestsBean> select_interests) {
      this.select_interests = select_interests;
    }

    public static class SelectMoneyBean {
      /**
       * money_id : 1
       * value : Rp 200.000-1.000.000
       */

      private String money_id;
      private String value;

      public String getMoney_id() {
        return money_id;
      }

      public void setMoney_id(String money_id) {
        this.money_id = money_id;
      }

      public String getValue() {
        return value;
      }

      public void setValue(String value) {
        this.value = value;
      }
    }

    public static class SelectTimeBean {
      /**
       * time_id : 1
       * value : Dalam 10 hari
       */

      private String time_id;
      private String value;

      public String getTime_id() {
        return time_id;
      }

      public void setTime_id(String time_id) {
        this.time_id = time_id;
      }

      public String getValue() {
        return value;
      }

      public void setValue(String value) {
        this.value = value;
      }
    }

    public static class SelectAddressBean {
      /**
       * address_id : 2
       * value : Seluruh Indonesia
       */

      private String address_id;
      private String value;

      public String getAddress_id() {
        return address_id;
      }

      public void setAddress_id(String address_id) {
        this.address_id = address_id;
      }

      public String getValue() {
        return value;
      }

      public void setValue(String value) {
        this.value = value;
      }
    }

    public static class SelectInterestsBean {
      /**
       * interest_id : 1
       * value : Terpopuler
       */

      private String interest_id;
      private String value;

      public String getInterest_id() {
        return interest_id;
      }

      public void setInterest_id(String interest_id) {
        this.interest_id = interest_id;
      }

      public String getValue() {
        return value;
      }

      public void setValue(String value) {
        this.value = value;
      }
    }
  }
}
