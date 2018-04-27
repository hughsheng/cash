package com.tl.tplus.home.mvp;

import com.tl.tplus.base.api.bean.BaseResultBean;

import java.util.List;

/**
 * Created by Administrator on 2018/2/22.
 *
 */

public class HomeBannerBean extends BaseResultBean {

  /**
   * data : {"banner":[{"banner_id":"8","img":"http://cdnoss.cashcash
   * .id/cashcash/banner/2017-12/5c0a17994c9b23ac74de382f93c104e8.jpg","type":"2","pid":"36",
   * "url":"","sort":"10","ctime":"2017-12-29 10:55:43"},{"banner_id":"7","img":"http://cdnoss
   * .cashcash.id/cashcash/banner/2017-12/2f31431ce60508e50acf432637ebb09c.jpg","type":"2",
   * "pid":"19","url":"","sort":"11","ctime":"2017-12-29 10:54:06"}],"filter_words":[{"id":"2",
   * "type":"1","name":"BUNGA RENDAH","function":"getInterestLow"},{"id":"3","type":"1",
   * "name":"PENCAIRAN CEPAT","function":"getLoansFast"},{"id":"4","type":"1","name":"PINJANMAN
   * TINGGI","function":"getAmountHigh"}]}
   */

  private DataBean data;

  public DataBean getData() {
    return data;
  }

  public void setData(DataBean data) {
    this.data = data;
  }

  public static class DataBean {
    private List<BannerBean> banner;
    private List<FilterWordsBean> filter_words;
    private int bannerType;

    public int getBannerType() {
      return bannerType;
    }

    public void setBannerType(int bannerType) {
      this.bannerType = bannerType;
    }

    public List<BannerBean> getBanner() {
      return banner;
    }

    public void setBanner(List<BannerBean> banner) {
      this.banner = banner;
    }

    public List<FilterWordsBean> getFilter_words() {
      return filter_words;
    }

    public void setFilter_words(List<FilterWordsBean> filter_words) {
      this.filter_words = filter_words;
    }

    public static class BannerBean {
      /**
       * banner_id : 8
       * img : http://cdnoss.cashcash
       * .id/cashcash/banner/2017-12/5c0a17994c9b23ac74de382f93c104e8.jpg
       * type : 2
       * pid : 36
       * url :
       * sort : 10
       * ctime : 2017-12-29 10:55:43
       */

      private String banner_id;
      private String img;
      private String type;
      private String pid;
      private String url;
      private String sort;
      private String ctime;

      public String getBanner_id() {
        return banner_id;
      }

      public void setBanner_id(String banner_id) {
        this.banner_id = banner_id;
      }

      public String getImg() {
        return img;
      }

      public void setImg(String img) {
        this.img = img;
      }

      public String getType() {
        return type;
      }

      public void setType(String type) {
        this.type = type;
      }

      public String getPid() {
        return pid;
      }

      public void setPid(String pid) {
        this.pid = pid;
      }

      public String getUrl() {
        return url;
      }

      public void setUrl(String url) {
        this.url = url;
      }

      public String getSort() {
        return sort;
      }

      public void setSort(String sort) {
        this.sort = sort;
      }

      public String getCtime() {
        return ctime;
      }

      public void setCtime(String ctime) {
        this.ctime = ctime;
      }
    }

    public static class FilterWordsBean {
      /**
       * id : 2
       * type : 1
       * name : BUNGA RENDAH
       * function : getInterestLow
       */

      private String id;
      private String type;
      private String name;
      private String function;

      public String getId() {
        return id;
      }

      public void setId(String id) {
        this.id = id;
      }

      public String getType() {
        return type;
      }

      public void setType(String type) {
        this.type = type;
      }

      public String getName() {
        return name;
      }

      public void setName(String name) {
        this.name = name;
      }

      public String getFunction() {
        return function;
      }

      public void setFunction(String function) {
        this.function = function;
      }
    }
  }
}
