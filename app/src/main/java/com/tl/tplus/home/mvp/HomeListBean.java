package com.tl.tplus.home.mvp;

import com.tl.tplus.base.api.bean.BaseResultBean;

import java.util.List;

/**
 * Created by sztangli on 2018-3-1.
 */

public class HomeListBean extends BaseResultBean {


  private List<DataBean> data;

  public List<DataBean> getData() {
    return data;
  }

  public void setData(List<DataBean> data) {
    this.data = data;
  }

  private int loadType;//获取数据类型0:刷新 1:加载更多(非接口返回字段)

  public int getLoadType() {
    return loadType;
  }

  public void setLoadType(int loadType) {
    this.loadType = loadType;
  }

  public static class DataBean {
    /**
     * pid : 5 商品id
     * product_name : Rupiah Plus 商品名称
     * tags : <font size='14' color='#333333'>Dapatkan dana tunai dengan cepat tanpa ribet!</font> 标签
     * type : 1 产品类型 1 app 2web
     * official : 0  官方认证 0未认证 1 已认证
     * interest_rate : 1.00 利率
     * interest_rate_unit : hari  利率单位
     * loan_time_day : 2  放款时间
     * price_min : 600000.00  最小房贷金额
     * price_max : 1500000.00 最大放款金额
     * price_unit : 1000000   放款金额单位
     * icon : http://cdnoss.cashcash  产品图标
     * .id/cashcash/product/icon/2017-12/a33d04c22be309166467f07b06f1d55b.jpg
     * total_score : 4.55  总评分
     * review : Persen approval 92% / Kalau telah berumur 18 tahun boleh pinjam  小编评价
     * package_name : com.yinshan.program.banda  应用包名
     * cap_limit : 0
     * cap_status : 0
     * price_new : 1.500.000  当前放款金额
     * loan_time_day_unit : hari 贷款时间当天单位
     * tags_type : 1  标签类型 0全部，1产品，2活动与攻略
     * tags_title : Tips   标签标题
     * price_unit_text : Kuota(juta Rp)  价格单位文本
     */

    private String pid;
    private String product_name;
    private String tags;
    private String type;
    private String official;
    private String interest_rate;
    private String interest_rate_unit;
    private String loan_time_day;
    private String price_min;
    private String price_max;
    private String price_unit;
    private String icon;
    private String total_score;
    private String review;
    private String package_name;
    private String cap_limit;
    private int cap_status;
    private String price_new;
    private String loan_time_day_unit;
    private int tags_type;
    private String tags_title;
    private String price_unit_text;
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

    public String getPrice_unit() {
      return price_unit;
    }

    public void setPrice_unit(String price_unit) {
      this.price_unit = price_unit;
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

    public String getCap_limit() {
      return cap_limit;
    }

    public void setCap_limit(String cap_limit) {
      this.cap_limit = cap_limit;
    }

    public int getCap_status() {
      return cap_status;
    }

    public void setCap_status(int cap_status) {
      this.cap_status = cap_status;
    }

    public String getPrice_new() {
      return price_new;
    }

    public void setPrice_new(String price_new) {
      this.price_new = price_new;
    }

    public String getLoan_time_day_unit() {
      return loan_time_day_unit;
    }

    public void setLoan_time_day_unit(String loan_time_day_unit) {
      this.loan_time_day_unit = loan_time_day_unit;
    }

    public int getTags_type() {
      return tags_type;
    }

    public void setTags_type(int tags_type) {
      this.tags_type = tags_type;
    }

    public String getTags_title() {
      return tags_title;
    }

    public void setTags_title(String tags_title) {
      this.tags_title = tags_title;
    }

    public String getPrice_unit_text() {
      return price_unit_text;
    }

    public void setPrice_unit_text(String price_unit_text) {
      this.price_unit_text = price_unit_text;
    }
  }
}
