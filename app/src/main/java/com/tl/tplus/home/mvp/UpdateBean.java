package com.tl.tplus.home.mvp;

import com.tl.tplus.base.api.bean.BaseResultBean;

/**
 * Created by sztangli on 2018-3-21.
 *
 */

public class UpdateBean extends BaseResultBean {

  /**
   * data : {"f_title":"Update versi V 1.1.5.1 ：","f_desc":"1. Tips pinjaman pilihan, bantu kamu
   * tingkatkan persentase lolos pinjaman\n\n2. Survey dan Saran, bantu kamu untuk lebih mudah
   * pahami syarat pinjaman\n\n3. Pilihan kata kunci，bantu kamu untuk lebih cepat temukan produk
   * pinjaman terbaik","f_type":2,"f_version_no":"1.1.5.1","f_url":"https://play.google
   * .com/store/apps/details?id=com.firestorm.sea.cashcash"}
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
     * f_title : Update versi V 1.1.5.1 ：
     * f_desc : 1. Tips pinjaman pilihan, bantu kamu tingkatkan persentase lolos pinjaman

     2. Survey dan Saran, bantu kamu untuk lebih mudah pahami syarat pinjaman

     3. Pilihan kata kunci，bantu kamu untuk lebih cepat temukan produk pinjaman terbaik
     * f_type : 2
     * f_version_no : 1.1.5.1
     * f_url : https://play.google.com/store/apps/details?id=com.firestorm.sea.cashcash
     */

    private String f_title; //标题
    private String f_desc;  //描述
    private int f_type;     //类型 0:无更新 1：强制更新 2:可选更新 3:检测更新
    private String f_version_no; //版本号
    private String f_url;

    public String getF_title() {
      return f_title;
    }

    public void setF_title(String f_title) {
      this.f_title = f_title;
    }

    public String getF_desc() {
      return f_desc;
    }

    public void setF_desc(String f_desc) {
      this.f_desc = f_desc;
    }

    public int getF_type() {
      return f_type;
    }

    public void setF_type(int f_type) {
      this.f_type = f_type;
    }

    public String getF_version_no() {
      return f_version_no;
    }

    public void setF_version_no(String f_version_no) {
      this.f_version_no = f_version_no;
    }

    public String getF_url() {
      return f_url;
    }

    public void setF_url(String f_url) {
      this.f_url = f_url;
    }
  }
}
