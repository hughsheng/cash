package com.tl.tplus.home.mvp;

import com.tl.tplus.base.api.bean.BaseResultBean;

import java.util.List;

/**
 * Created by sztangli on 2018-3-2.
 */

public class HomeCiciListBean extends BaseResultBean {


  private List<DataBean> data;

  private int loadType;  //非接口返回

  public int getLoadType() {
    return loadType;
  }

  public void setLoadType(int loadType) {
    this.loadType = loadType;
  }
  public List<DataBean> getData() {
    return data;
  }

  public void setData(List<DataBean> data) {
    this.data = data;
  }

  public static class DataBean {
    /**
     * pid : 3      商品id
     * product_name : my home credit  商品名称
     * tags : <font size='14' color='#333333'>Elektronik</font>  标签
     * type : 1  产品类型 1 app 2web
     * official : 1  0  官方认证 0未认证 1 已认证
     * interest_rate : 3.17   最小分期率
     * staging_cycle_min : 12 最小分期周期
     * staging_cycle_max : 18 最大分期周期
     * down_payment_rate_min : 10.00   最小首付比率
     * down_payment_rate_max : 30.00   最大首付比率
     * icon : http://cdnoss.cashcash    图标
     * .id/cashcash/product/icon/2017-12/182b520a55f77e6b51fb8ace4b694642.png
     * total_score : 4.50  总评分
     * review : Angsuran panjang / Tiga langkah pinjam uang  小编评论
     * package_name : id.co.myhomecredit  应用包名
     * cap_status : 0
     * tags_title : Fitur  标签标题
     */

    private String pid;
    private String product_name;
    private String tags;
    private String type;
    private String official;
    private String interest_rate;
    private String staging_cycle_min;
    private String staging_cycle_max;
    private String down_payment_rate_min;
    private String down_payment_rate_max;
    private String icon;
    private String total_score;
    private String review;
    private String package_name;
    private int cap_status;
    private String tags_title;
    private boolean install;

    public boolean isInstall() {
      return install;
    }

    public void setInstall(boolean install) {
      this.install = install;
    }

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

    public String getTags() {
      return tags;
    }

    public void setTags(String tags) {
      this.tags = tags;
    }

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public String getOfficial() {
      return official;
    }

    public void setOfficial(String official) {
      this.official = official;
    }

    public String getInterest_rate() {
      return interest_rate;
    }

    public void setInterest_rate(String interest_rate) {
      this.interest_rate = interest_rate;
    }

    public String getStaging_cycle_min() {
      return staging_cycle_min;
    }

    public void setStaging_cycle_min(String staging_cycle_min) {
      this.staging_cycle_min = staging_cycle_min;
    }

    public String getStaging_cycle_max() {
      return staging_cycle_max;
    }

    public void setStaging_cycle_max(String staging_cycle_max) {
      this.staging_cycle_max = staging_cycle_max;
    }

    public String getDown_payment_rate_min() {
      return down_payment_rate_min;
    }

    public void setDown_payment_rate_min(String down_payment_rate_min) {
      this.down_payment_rate_min = down_payment_rate_min;
    }

    public String getDown_payment_rate_max() {
      return down_payment_rate_max;
    }

    public void setDown_payment_rate_max(String down_payment_rate_max) {
      this.down_payment_rate_max = down_payment_rate_max;
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

    public String getReview() {
      return review;
    }

    public void setReview(String review) {
      this.review = review;
    }

    public String getPackage_name() {
      return package_name;
    }

    public void setPackage_name(String package_name) {
      this.package_name = package_name;
    }

    public int getCap_status() {
      return cap_status;
    }

    public void setCap_status(int cap_status) {
      this.cap_status = cap_status;
    }

    public String getTags_title() {
      return tags_title;
    }

    public void setTags_title(String tags_title) {
      this.tags_title = tags_title;
    }
  }
}
