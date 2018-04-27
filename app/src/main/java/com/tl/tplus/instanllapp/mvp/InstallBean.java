package com.tl.tplus.instanllapp.mvp;

import com.tl.tplus.base.api.bean.BaseResultBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/10.
 *
 */

public class InstallBean extends BaseResultBean {


  private List<DataBean> data;

  public List<DataBean> getData() {
    return data;
  }

  public void setData(List<DataBean> data) {
    this.data = data;
  }

  public static class DataBean {
    /**
     * pid : 2
     * product_name : Akulaku
     * icon : http://cdnoss.cashcash
     * .id/cashcash/product/icon/2017-12/3f7757f431e27b3b948fe0bf6722b8ef.png
     * total_score : 4.50
     * package_name : io.silvrr.installment
     * type : 2
     */

    private String pid;
    private String product_name;
    private String icon;
    private String total_score;
    private String package_name;
    private String type;

    public String getPid() {
      return pid;
    }

    public void setPid(String pid) {
      this.pid = pid;
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

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }
  }
}