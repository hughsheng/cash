package com.tl.tplus.cicidetailinfo.mvp;

import com.tl.tplus.base.api.bean.BaseResultBean;

/**
 * Created by Administrator on 2018/3/7.
 */

public class CicidetailInfoBean extends BaseResultBean {

  private DataBean data;

  public DataBean getData() {
    return data;
  }

  public void setData(DataBean data) {
    this.data = data;
  }

  public static class DataBean {
    /**
     * area : Bali,32 Kabupaten dan Kota tersebar di Pulau Sumatera,Jawa,Sulawesi
     * loan_approach : Bank card
     * loan_cycle : 7,14
     * loan_time_text : 1-2 day
     * pass_rate : 92.00
     * interest_rate_text : 1% / days
     * default_rate_text : 1,2% ( one week )
     2% ( after one week )
     * repayment_type : M-Banking,E-Banking,ATM Transfer,Alfamart
     * repayment_methods_text : Transfer ke Virtual account number
     Alfamart => kasir => nomor transaksi
     * repayment_confirmation_time : 10 - 15 menit
     * repayment_describe :
     * dunning_way : Telepon,Door to door
     * dunning_effort : middle
     * promote_video_url : https://www.youtube.com/watch?v=oS9Gqi480CA
     * promote_video_desc :
     * resubmit_time_text : 7 days
     * company_name : PT. Digital Synergy Technology
     * news : Rupiahplus in the second half of 2017, Complete millions of dollars in Pre-A round
     * of financing
     */

    private String area;
    private String loan_approach;
    private String loan_cycle;
    private String loan_time_text;
    private String pass_rate;
    private String interest_rate_text;
    private String default_rate_text;
    private String repayment_type;
    private String repayment_methods_text;
    private String repayment_confirmation_time;
    private String repayment_describe;
    private String dunning_way;
    private String dunning_effort;
    private String promote_video_url;
    private String promote_video_desc;
    private String resubmit_time_text;
    private String company_name;
    private String news;

    public String getArea() {
      return area;
    }

    public void setArea(String area) {
      this.area = area;
    }

    public String getLoan_approach() {
      return loan_approach;
    }

    public void setLoan_approach(String loan_approach) {
      this.loan_approach = loan_approach;
    }

    public String getLoan_cycle() {
      return loan_cycle;
    }

    public void setLoan_cycle(String loan_cycle) {
      this.loan_cycle = loan_cycle;
    }

    public String getLoan_time_text() {
      return loan_time_text;
    }

    public void setLoan_time_text(String loan_time_text) {
      this.loan_time_text = loan_time_text;
    }

    public String getPass_rate() {
      return pass_rate;
    }

    public void setPass_rate(String pass_rate) {
      this.pass_rate = pass_rate;
    }

    public String getInterest_rate_text() {
      return interest_rate_text;
    }

    public void setInterest_rate_text(String interest_rate_text) {
      this.interest_rate_text = interest_rate_text;
    }

    public String getDefault_rate_text() {
      return default_rate_text;
    }

    public void setDefault_rate_text(String default_rate_text) {
      this.default_rate_text = default_rate_text;
    }

    public String getRepayment_type() {
      return repayment_type;
    }

    public void setRepayment_type(String repayment_type) {
      this.repayment_type = repayment_type;
    }

    public String getRepayment_methods_text() {
      return repayment_methods_text;
    }

    public void setRepayment_methods_text(String repayment_methods_text) {
      this.repayment_methods_text = repayment_methods_text;
    }

    public String getRepayment_confirmation_time() {
      return repayment_confirmation_time;
    }

    public void setRepayment_confirmation_time(String repayment_confirmation_time) {
      this.repayment_confirmation_time = repayment_confirmation_time;
    }

    public String getRepayment_describe() {
      return repayment_describe;
    }

    public void setRepayment_describe(String repayment_describe) {
      this.repayment_describe = repayment_describe;
    }

    public String getDunning_way() {
      return dunning_way;
    }

    public void setDunning_way(String dunning_way) {
      this.dunning_way = dunning_way;
    }

    public String getDunning_effort() {
      return dunning_effort;
    }

    public void setDunning_effort(String dunning_effort) {
      this.dunning_effort = dunning_effort;
    }

    public String getPromote_video_url() {
      return promote_video_url;
    }

    public void setPromote_video_url(String promote_video_url) {
      this.promote_video_url = promote_video_url;
    }

    public String getPromote_video_desc() {
      return promote_video_desc;
    }

    public void setPromote_video_desc(String promote_video_desc) {
      this.promote_video_desc = promote_video_desc;
    }

    public String getResubmit_time_text() {
      return resubmit_time_text;
    }

    public void setResubmit_time_text(String resubmit_time_text) {
      this.resubmit_time_text = resubmit_time_text;
    }

    public String getCompany_name() {
      return company_name;
    }

    public void setCompany_name(String company_name) {
      this.company_name = company_name;
    }

    public String getNews() {
      return news;
    }

    public void setNews(String news) {
      this.news = news;
    }
  }
}
