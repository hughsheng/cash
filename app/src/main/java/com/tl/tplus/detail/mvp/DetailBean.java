package com.tl.tplus.detail.mvp;


import com.tl.tplus.base.api.bean.BaseResultBean;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2018/3/2.
 *
 */

public class DetailBean extends BaseResultBean {

  /**
   * data : {"pid":"5","product_name":"Rupiah Plus","tags":"","type":"1","interest_rate":"1.00",
   * "interest_rate_unit":"days","loan_time_day":"2","icon":"http://cdnoss.cashcash
   * .id/cashcash/product/icon/2017-12/a33d04c22be309166467f07b06f1d55b.jpg",
   * "pass_rate_score":"4.55","speed_score":"4.55","dunning_score":"4.55","total_score":"4.55",
   * "review":"Persen approval 92% / Kalau telah berumur 18 tahun boleh pinjam",
   * "jump_url":"https://app.appsflyer.com/com.yinshan.program
   * .banda?pid=cashcash_int&af_click_lookback=7d&clickid=&android_id=&advertising_id=&imei
   * =&af_siteid=","version":"1","package_name":"com.yinshan.program.banda","cap_limit":"0",
   * "version_compatible":1,"":0,"card":"1","ares":"Bali,32 Kabupaten dan Kota tersebar di Pulau
   * Sumatera,Jawa,Sulawesi","borrowing_type":[],"price":[{"num":"0","price":"600000.00",
   * "time":[{"time_min":"7","time_max":"7"},{"time_min":"14","time_max":"14"}]},{"num":"0",
   * "price":"1200000.00","time":[{"time_min":"7","time_max":"7"},{"time_min":"14",
   * "time_max":"14"}]}],"interest_algorithm":[{"num":"0","price_min":"600000.00",
   * "price_max":"1200000.00","time_min":"7","time_max":"14","free_type":"1","free":"0.00",
   * "rate":"1.00","rate_unit":"days"}],"activity_list":[{"title":"Dapatkan dana tunai dengan
   * cepat tanpa ribet!","description":"","tags":["Tingkat keberhasilan pinjaman tinggi"],
   * "type":"2","url":"http://cdnoss.cashcash
   * .id/cashcash_test/product/raiders/2018-01/4d3022581e99d3599354297f41af6bbd.html",
   * "icon":"http://cdnoss.cashcash
   * .id/cashcash_test/product/activity/icon/2018-01/f06364b7b61307d901af9d66a7b3ce10.png"}],
   * "details_info":[{"content":" Tepat 18 tahun"},{"content":" Kartu Bank / KTP / No HP"},
   * {"content":" Kartu karyawan / Pendapatan stabil"},{"content":" Area jangkauan: 32 Kabupaten
   * dan Kota tersebar di Pulau Sumatera, Jawa, Bali dan Sulawesi."}],"review_status":0,
   * "cap_status":0}
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
     * pid : 5
     * product_name : Rupiah Plus
     * tags :
     * type : 1
     * interest_rate : 1.00
     * interest_rate_unit : days
     * loan_time_day : 2
     * icon : http://cdnoss.cashcash
     * .id/cashcash/product/icon/2017-12/a33d04c22be309166467f07b06f1d55b.jpg
     * pass_rate_score : 4.55
     * speed_score : 4.55
     * dunning_score : 4.55
     * total_score : 4.55
     * review : Persen approval 92% / Kalau telah berumur 18 tahun boleh pinjam
     * jump_url : https://app.appsflyer.com/com.yinshan.program
     * .banda?pid=cashcash_int&af_click_lookback=7d&clickid=&android_id=&advertising_id=&imei
     * =&af_siteid=
     * version : 1
     * package_name : com.yinshan.program.banda
     * cap_limit : 0
     * version_compatible : 1
     *  : 0
     * card : 1
     * ares : Bali,32 Kabupaten dan Kota tersebar di Pulau Sumatera,Jawa,Sulawesi
     * borrowing_type : []
     * price : [{"num":"0","price":"600000.00","time":[{"time_min":"7","time_max":"7"},
     * {"time_min":"14","time_max":"14"}]},{"num":"0","price":"1200000.00",
     * "time":[{"time_min":"7","time_max":"7"},{"time_min":"14","time_max":"14"}]}]
     * interest_algorithm : [{"num":"0","price_min":"600000.00","price_max":"1200000.00",
     * "time_min":"7","time_max":"14","free_type":"1","free":"0.00","rate":"1.00",
     * "rate_unit":"days"}]
     * activity_list : [{"title":"Dapatkan dana tunai dengan cepat tanpa ribet!",
     * "description":"","tags":["Tingkat keberhasilan pinjaman tinggi"],"type":"2",
     * "url":"http://cdnoss.cashcash
     * .id/cashcash_test/product/raiders/2018-01/4d3022581e99d3599354297f41af6bbd.html",
     * "icon":"http://cdnoss.cashcash
     * .id/cashcash_test/product/activity/icon/2018-01/f06364b7b61307d901af9d66a7b3ce10.png"}]
     * details_info : [{"content":" Tepat 18 tahun"},{"content":" Kartu Bank / KTP / No HP"},
     * {"content":" Kartu karyawan / Pendapatan stabil"},{"content":" Area jangkauan: 32
     * Kabupaten dan Kota tersebar di Pulau Sumatera, Jawa, Bali dan Sulawesi."}]
     * review_status : 0
     * cap_status : 0
     */

    private String pid;
    private String product_name;
    private String tags;
    private String type;
    private String interest_rate;
    private String interest_rate_unit;
    private String loan_time_day;
    private String icon;
    private String pass_rate_score;
    private String speed_score;
    private String dunning_score;
    private String total_score;
    private String review;
    private String jump_url;
    private String version;
    private String package_name;
    private String cap_limit;
    private int version_compatible;
    @SerializedName("")
    private int _$90; // FIXME check this code
    private String card;
    private String ares;
    private int review_status;
    private int cap_status;
    private List<String> borrowing_type;
    private List<PriceBean> price;
    private List<InterestAlgorithmBean> interest_algorithm;
    private List<ActivityListBean> activity_list;
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

    public String getSpeed_score() {
      return speed_score;
    }

    public void setSpeed_score(String speed_score) {
      this.speed_score = speed_score;
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

    public int get_$90() {
      return _$90;
    }

    public void set_$90(int _$90) {
      this._$90 = _$90;
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

    public List<ActivityListBean> getActivity_list() {
      return activity_list;
    }

    public void setActivity_list(List<ActivityListBean> activity_list) {
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
       * num : 0
       * price : 600000.00
       * time : [{"time_min":"7","time_max":"7"},{"time_min":"14","time_max":"14"}]
       */

      private String num;
      private String price;
      private List<TimeBean> time;

      public String getNum() {
        return num;
      }

      public void setNum(String num) {
        this.num = num;
      }

      public String getPrice() {
        return price;
      }

      public void setPrice(String price) {
        this.price = price;
      }

      public List<TimeBean> getTime() {
        return time;
      }

      public void setTime(List<TimeBean> time) {
        this.time = time;
      }

      public static class TimeBean {
        /**
         * time_min : 7
         * time_max : 7
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
       * price_min : 600000.00
       * price_max : 1200000.00
       * time_min : 7
       * time_max : 14
       * free_type : 1
       * free : 0.00
       * rate : 1.00
       * rate_unit : days
       */

      private String num;
      private String price_min;
      private String price_max;
      private String time_min;
      private String time_max;
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

    public static class ActivityListBean {
      /**
       * title : Dapatkan dana tunai dengan cepat tanpa ribet!
       * description :
       * tags : ["Tingkat keberhasilan pinjaman tinggi"]
       * type : 2
       * url : http://cdnoss.cashcash.id/cashcash_test/product/raiders/2018-01/4d3022581e99d3599354297f41af6bbd.html
       * icon : http://cdnoss.cashcash.id/cashcash_test/product/activity/icon/2018-01/f06364b7b61307d901af9d66a7b3ce10.png
       */

      private String title;
      private String description;
      private String type;
      private String url;
      private String icon;
      private List<String> tags;

      public String getTitle() {
        return title;
      }

      public void setTitle(String title) {
        this.title = title;
      }

      public String getDescription() {
        return description;
      }

      public void setDescription(String description) {
        this.description = description;
      }

      public String getType() {
        return type;
      }

      public void setType(String type) {
        this.type = type;
      }

      public String getUrl() {
        return url;
      }

      public void setUrl(String url) {
        this.url = url;
      }

      public String getIcon() {
        return icon;
      }

      public void setIcon(String icon) {
        this.icon = icon;
      }

      public List<String> getTags() {
        return tags;
      }

      public void setTags(List<String> tags) {
        this.tags = tags;
      }
    }

    public static class DetailsInfoBean {
      /**
       * content :  Tepat 18 tahun
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
