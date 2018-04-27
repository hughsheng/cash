package com.tl.tplus.cicidetail.mvp;


import com.tl.tplus.base.api.bean.BaseResultBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/2.
 *
 */

public class CicidetailBean extends BaseResultBean {

  /**
   * data : {"pid":"3","product_name":"my home credit","tags":"7","type":"1",
   * "interest_rate":"3.17","icon":"http://cdnoss.cashcash
   * .id/cashcash/product/icon/2017-12/182b520a55f77e6b51fb8ace4b694642.png",
   * "pass_rate_score":"4.50","dunning_score":"4.50","total_score":"4.50","review":"Angsuran
   * panjang / Tiga langkah pinjam uang","jump_url":"https://play.google
   * .com/store/apps/details?id=id.co.myhomecredit","version":"1","package_name":"id.co
   * .myhomecredit","cap_limit":"45","version_compatible":1,"promote_video_type":1,"card":"1",
   * "ares":"Balikpapan,Bandung,Banjarmasin,Batam,Denpasar,Jabodetabek,Makassar,Malang,Manado,
   * Medan,Palembang,Pekanbaru,Pontianak,Semarang,Surabaya,Yogyakarta","borrowing_type":[],
   * "credit_price_type":"1","down_payment_rate":["10.00","20.00","30.00"],
   * "price":[{"down_payment_rate":["10.00","20.00","30.00"],"num":"0","price_min":"745000.00",
   * "price_max":"10000000.00","time":[{"time_min":"360","time_max":"360"},{"time_min":"450",
   * "time_max":"450"},{"time_min":"540","time_max":"540"}]}],"interest_algorithm":[{"num":"0",
   * "price_min":"745000.00","price_max":"10000000.00","time_min":"360","time_max":"540",
   * "down_payment_rate":"10.00","free_type":"1","free":"0.00","rate":"40.00",
   * "rate_unit":"default"},{"num":"0","price_min":"745000.00","price_max":"10000000.00",
   * "time_min":"360","time_max":"540","down_payment_rate":"20.00","free_type":"1","free":"0.00",
   * "rate":"48.00","rate_unit":"default"},{"num":"0","price_min":"745000.00",
   * "price_max":"10000000.00","time_min":"360","time_max":"540","down_payment_rate":"30.00",
   * "free_type":"1","free":"0.00","rate":"57.00","rate_unit":"default"}],"activity_list":[],
   * "details_info":[{"content":" Kartu Bank / KTP / No HP "},{"content":" Minimun 21 tahun 19
   * tahun bila sudah menikah "},{"content":" Penghasilan tetap "},{"content":" Area jangkauan:
   * Jabodetabek, Bandung, Makassar, Surabaya, Yogyakarta, Semarang, Malang, Denpasar, Pekanbaru,
   * Medan, Batam, Palembang, Banjarmasin, Pontianak, Manado, dan Balikpapan"}],
   * "review_status":0,"cap_status":0}
   */

  private DataBean data;

  public DataBean getData() {
    return data;
  }

  public void setData(DataBean data) {
    this.data = data;
  }

  public static class DataBean {
    /**
     * pid : 3
     * product_name : my home credit
     * tags : 7
     * type : 1
     * interest_rate : 3.17
     * icon : http://cdnoss.cashcash
     * .id/cashcash/product/icon/2017-12/182b520a55f77e6b51fb8ace4b694642.png
     * pass_rate_score : 4.50
     * dunning_score : 4.50
     * total_score : 4.50
     * review : Angsuran panjang / Tiga langkah pinjam uang
     * jump_url : https://play.google.com/store/apps/details?id=id.co.myhomecredit
     * version : 1
     * package_name : id.co.myhomecredit
     * cap_limit : 45
     * version_compatible : 1
     * promote_video_type : 1
     * card : 1
     * ares : Balikpapan,Bandung,Banjarmasin,Batam,Denpasar,Jabodetabek,Makassar,Malang,Manado,
     * Medan,Palembang,Pekanbaru,Pontianak,Semarang,Surabaya,Yogyakarta
     * borrowing_type : []
     * credit_price_type : 1
     * down_payment_rate : ["10.00","20.00","30.00"]
     * price : [{"down_payment_rate":["10.00","20.00","30.00"],"num":"0","price_min":"745000.00",
     * "price_max":"10000000.00","time":[{"time_min":"360","time_max":"360"},{"time_min":"450",
     * "time_max":"450"},{"time_min":"540","time_max":"540"}]}]
     * interest_algorithm : [{"num":"0","price_min":"745000.00","price_max":"10000000.00",
     * "time_min":"360","time_max":"540","down_payment_rate":"10.00","free_type":"1",
     * "free":"0.00","rate":"40.00","rate_unit":"default"},{"num":"0","price_min":"745000.00",
     * "price_max":"10000000.00","time_min":"360","time_max":"540","down_payment_rate":"20.00",
     * "free_type":"1","free":"0.00","rate":"48.00","rate_unit":"default"},{"num":"0",
     * "price_min":"745000.00","price_max":"10000000.00","time_min":"360","time_max":"540",
     * "down_payment_rate":"30.00","free_type":"1","free":"0.00","rate":"57.00",
     * "rate_unit":"default"}]
     * activity_list : []
     * details_info : [{"content":" Kartu Bank / KTP / No HP "},{"content":" Minimun 21 tahun 19
     * tahun bila sudah menikah "},{"content":" Penghasilan tetap "},{"content":" Area jangkauan:
     * Jabodetabek, Bandung, Makassar, Surabaya, Yogyakarta, Semarang, Malang, Denpasar,
     * Pekanbaru, Medan, Batam, Palembang, Banjarmasin, Pontianak, Manado, dan Balikpapan"}]
     * review_status : 0
     * cap_status : 0
     */

    private String pid;
    private String product_name;
    private String tags;
    private String type;
    private String interest_rate;
    private String icon;
    private String pass_rate_score;
    private String dunning_score;
    private String total_score;
    private String review;
    private String jump_url;
    private String version;
    private String package_name;
    private String cap_limit;
    private int version_compatible;
    private int promote_video_type;
    private String card;
    private String ares;
    private String credit_price_type;
    private int review_status;
    private int cap_status;
    private List<String> borrowing_type;
    private List<String> down_payment_rate;
    private List<PriceBean> price;
    private List<InterestAlgorithmBean> interest_algorithm;
    private List<?> activity_list;
    private List<DetailsInfoBean> details_info;

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

    public String getInterest_rate() {
      return interest_rate;
    }

    public void setInterest_rate(String interest_rate) {
      this.interest_rate = interest_rate;
    }

    public String getIcon() {
      return icon;
    }

    public void setIcon(String icon) {
      this.icon = icon;
    }

    public String getPass_rate_score() {
      return pass_rate_score;
    }

    public void setPass_rate_score(String pass_rate_score) {
      this.pass_rate_score = pass_rate_score;
    }

    public String getDunning_score() {
      return dunning_score;
    }

    public void setDunning_score(String dunning_score) {
      this.dunning_score = dunning_score;
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

    public String getJump_url() {
      return jump_url;
    }

    public void setJump_url(String jump_url) {
      this.jump_url = jump_url;
    }

    public String getVersion() {
      return version;
    }

    public void setVersion(String version) {
      this.version = version;
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

    public int getVersion_compatible() {
      return version_compatible;
    }

    public void setVersion_compatible(int version_compatible) {
      this.version_compatible = version_compatible;
    }

    public int getPromote_video_type() {
      return promote_video_type;
    }

    public void setPromote_video_type(int promote_video_type) {
      this.promote_video_type = promote_video_type;
    }

    public String getCard() {
      return card;
    }

    public void setCard(String card) {
      this.card = card;
    }

    public String getAres() {
      return ares;
    }

    public void setAres(String ares) {
      this.ares = ares;
    }

    public String getCredit_price_type() {
      return credit_price_type;
    }

    public void setCredit_price_type(String credit_price_type) {
      this.credit_price_type = credit_price_type;
    }

    public int getReview_status() {
      return review_status;
    }

    public void setReview_status(int review_status) {
      this.review_status = review_status;
    }

    public int getCap_status() {
      return cap_status;
    }

    public void setCap_status(int cap_status) {
      this.cap_status = cap_status;
    }

    public List<String> getBorrowing_type() {
      return borrowing_type;
    }

    public void setBorrowing_type(List<String> borrowing_type) {
      this.borrowing_type = borrowing_type;
    }

    public List<String> getDown_payment_rate() {
      return down_payment_rate;
    }

    public void setDown_payment_rate(List<String> down_payment_rate) {
      this.down_payment_rate = down_payment_rate;
    }

    public List<PriceBean> getPrice() {
      return price;
    }

    public void setPrice(List<PriceBean> price) {
      this.price = price;
    }

    public List<InterestAlgorithmBean> getInterest_algorithm() {
      return interest_algorithm;
    }

    public void setInterest_algorithm(List<InterestAlgorithmBean> interest_algorithm) {
      this.interest_algorithm = interest_algorithm;
    }

    public List<?> getActivity_list() {
      return activity_list;
    }

    public void setActivity_list(List<?> activity_list) {
      this.activity_list = activity_list;
    }

    public List<DetailsInfoBean> getDetails_info() {
      return details_info;
    }

    public void setDetails_info(List<DetailsInfoBean> details_info) {
      this.details_info = details_info;
    }

    public static class PriceBean {
      /**
       * down_payment_rate : ["10.00","20.00","30.00"]
       * num : 0
       * price_min : 745000.00
       * price_max : 10000000.00
       * time : [{"time_min":"360","time_max":"360"},{"time_min":"450","time_max":"450"},
       * {"time_min":"540","time_max":"540"}]
       */

      private String num;
      private String price_min;
      private String price_max;
      private List<String> down_payment_rate;
      private List<TimeBean> time;

      public String getNum() {
        return num;
      }

      public void setNum(String num) {
        this.num = num;
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

      public List<String> getDown_payment_rate() {
        return down_payment_rate;
      }

      public void setDown_payment_rate(List<String> down_payment_rate) {
        this.down_payment_rate = down_payment_rate;
      }

      public List<TimeBean> getTime() {
        return time;
      }

      public void setTime(List<TimeBean> time) {
        this.time = time;
      }

      public static class TimeBean {
        /**
         * time_min : 360
         * time_max : 360
         */

        private String time_min;
        private String time_max;

        public String getTime_min() {
          return time_min;
        }

        public void setTime_min(String time_min) {
          this.time_min = time_min;
        }

        public String getTime_max() {
          return time_max;
        }

        public void setTime_max(String time_max) {
          this.time_max = time_max;
        }
      }
    }

    public static class InterestAlgorithmBean {
      /**
       * num : 0
       * price_min : 745000.00
       * price_max : 10000000.00
       * time_min : 360
       * time_max : 540
       * down_payment_rate : 10.00
       * free_type : 1
       * free : 0.00
       * rate : 40.00
       * rate_unit : default
       */

      private String num;
      private String price_min;
      private String price_max;
      private String time_min;
      private String time_max;
      private String down_payment_rate;
      private String free_type;
      private String free;
      private String rate;
      private String rate_unit;

      public String getNum() {
        return num;
      }

      public void setNum(String num) {
        this.num = num;
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

      public String getTime_min() {
        return time_min;
      }

      public void setTime_min(String time_min) {
        this.time_min = time_min;
      }

      public String getTime_max() {
        return time_max;
      }

      public void setTime_max(String time_max) {
        this.time_max = time_max;
      }

      public String getDown_payment_rate() {
        return down_payment_rate;
      }

      public void setDown_payment_rate(String down_payment_rate) {
        this.down_payment_rate = down_payment_rate;
      }

      public String getFree_type() {
        return free_type;
      }

      public void setFree_type(String free_type) {
        this.free_type = free_type;
      }

      public String getFree() {
        return free;
      }

      public void setFree(String free) {
        this.free = free;
      }

      public String getRate() {
        return rate;
      }

      public void setRate(String rate) {
        this.rate = rate;
      }

      public String getRate_unit() {
        return rate_unit;
      }

      public void setRate_unit(String rate_unit) {
        this.rate_unit = rate_unit;
      }
    }

    public static class DetailsInfoBean {
      /**
       * content :  Kartu Bank / KTP / No HP
       */

      private String content;

      public String getContent() {
        return content;
      }

      public void setContent(String content) {
        this.content = content;
      }
    }
  }
}
