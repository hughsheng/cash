package com.tl.tplus.detail.mvp;

import com.tl.tplus.base.api.bean.BaseResultBean;

import java.util.List;

/**
 * Created by sztangli on 2018-3-7.
 *
 */

public class DetailReviewBean  extends BaseResultBean{

  private DataBean data;

  public DataBean getData() {
    return data;
  }

  public void setData(DataBean data) {
    this.data = data;
  }

  public static class DataBean {
    /**
     * review_org : ["2017-11-16","2017-11-14","2017-11-09","2017-11-09","2017-11-06",
     * "2017-11-02","2017-11-02","2017-11-01"]
     * google_score : 4.50
     * google_ranking : 5
     * xb_review : Persen approval 92% / Kalau telah berumur 18 tahun boleh pinjam
     * user_review : [{"nickname":"Raka cipta","head_img":"https://scontent.xx.fbcdn
     * .net/v/t1.0-1/p50x50/22195328_120618232022151_7138150790550753966_n
     * .jpg?oh=94d71eb1da76519b3f2e465284a17bba&oe=5A7C459E","score":"3.00","content":"Sampai
     * saat ini blum ada buktinya sma skali di klw anda bner bsa mmbantu.dan ingin mmbantu
     * orang\" yg sdng ksusahan tolong buktikan....Sy ggavprlu hrapan tpi bukti.smua orng pun pzt
     * brhrap sprti itu.","tag":"0","uptime":"2018-01-31 21:30:18","sort":"0",
     * "review_date":"November 09, 2017","file":[]},{"nickname":"ratna juwita",
     * "head_img":"https://scontent.xx.fbcdn
     * .net/v/t1.0-1/p50x50/20526054_461158664268202_4800594625999415787_n
     * .jpg?oh=cbbe9710cd42653935120db7211cfd75&oe=5A71BC3E","score":"5.00","content":"Cepeeet
     * bangeet","tag":"0","uptime":"2018-01-31 21:01:31","sort":"0","review_date":"November 06,
     * 2017","file":[]},{"nickname":"LEMOH SOGIL","head_img":"https://scontent.xx.fbcdn
     * .net/v/t1.0-1/p50x50/22141178_774625946050267_3673329086220782183_n
     * .jpg?oh=ef8870f1a253e72c526ef089f033168a&oe=5A7A09B6","score":"1.00","content":"2x
     * pengajuan.. Pake segala slip gaji dll ttep aja direject.. Pdhal cm mjem 1.2 doank..
     * Uninstall aja deh..","tag":"0","uptime":"2018-01-31 21:25:38","sort":"0",
     * "review_date":"November 02, 2017","file":[]},{"nickname":"prita dwi mayangsari",
     * "head_img":"https://scontent.xx.fbcdn
     * .net/v/t1.0-1/p50x50/21751775_101322123951254_7029500463274066505_n
     * .jpg?oh=9e4015a28ab5f51bd5a650b3bfaeb99d&oe=5A8013BC","score":"5.00","content":"Langsung
     * di konfirmasi dan langsung masuk dananya.sangat membantu.tq","tag":"1",
     * "uptime":"2017-11-23 15:58:33","sort":"99","review_date":"November 16, 2017","file":[]},
     * {"nickname":"Fanani Fanani","head_img":"https://scontent.xx.fbcdn
     * .net/v/t1.0-1/c8.0.50.50/p50x50/22195258_1880041678990361_449486454409113087_n
     * .jpg?_nc_eui2=v1%3AAeEBuVGbehlEPFasp_-b9oOyLZJiIaXtBnxtHKgOs4S_fE0_1_VgCgwdKIHbqh
     * -xHF9b6cOkE6XGAn_V6o5cAEeL&oh=c456941f75a5737edd4a9429db531143&oe=5A7F8D2F",
     * "score":"5.00","content":"Puasss. Cepat dan gak ribet. Pinjaman ke 2 gk sampe 20 menit sdh
     * cair..","tag":"1","uptime":"2017-11-21 16:07:30","sort":"99","review_date":"November 14,
     * 2017","file":[]},{"nickname":"Husnul chotimah","head_img":"https://scontent.xx.fbcdn
     * .net/v/t1.0-1/c15.0.50.50/p50x50/10354686_10150004552801856_220367501106153455_n
     * .jpg?oh=0498220bfacef58fe04df5f5c3bf0379&oe=5A75492F","score":"3.00","content":"Kok blom
     * cair ya.. Status masih first review dr sore kemarin.. Jam kerja nya kapan ya.. Aku kasih
     * bintang 3 dulu deh kalo bagus baru naikin reting","tag":"1","uptime":"2017-11-21
     * 16:07:30","sort":"99","review_date":"November 09, 2017","file":[]},{"nickname":"Bondan
     * Adisury","head_img":"https://scontent.xx.fbcdn
     * .net/v/t1.0-1/c15.0.50.50/p50x50/10354686_10150004552801856_220367501106153455_n
     * .jpg?oh=0498220bfacef58fe04df5f5c3bf0379&oe=5A75492F","score":"1.00","content":"Payah,
     * belom ada 5 menit tau2 status rejected.. Gembel","tag":"0","uptime":"2017-11-21 16:07:30",
     * "sort":"99","review_date":"November 02, 2017","file":[]},{"nickname":"chairul sahid",
     * "head_img":"https://scontent.xx.fbcdn
     * .net/v/t1.0-1/c0.0.50.50/p50x50/15747748_409376059394471_1931889524905043458_n
     * .jpg?oh=e30ed1902f5f496754b3a9c68191fc7f&oe=5A3D9ECF","score":"5.00","content":"Terima
     * kasih rupiah plus..sudah sangat banyak membantu..Semoga sukses slalu","tag":"0",
     * "uptime":"2017-11-21 16:07:30","sort":"99","review_date":"November 01, 2017","file":[]}]
     */

    private String google_score;
    private String google_ranking;
    private String xb_review;
    private List<String> review_org;
    private List<UserReviewBean> user_review;

    public String getGoogle_score() {
      return google_score;
    }

    public void setGoogle_score(String google_score) {
      this.google_score = google_score;
    }

    public String getGoogle_ranking() {
      return google_ranking;
    }

    public void setGoogle_ranking(String google_ranking) {
      this.google_ranking = google_ranking;
    }

    public String getXb_review() {
      return xb_review;
    }

    public void setXb_review(String xb_review) {
      this.xb_review = xb_review;
    }

    public List<String> getReview_org() {
      return review_org;
    }

    public void setReview_org(List<String> review_org) {
      this.review_org = review_org;
    }

    public List<UserReviewBean> getUser_review() {
      return user_review;
    }

    public void setUser_review(List<UserReviewBean> user_review) {
      this.user_review = user_review;
    }

    public static class UserReviewBean {
      /**
       * nickname : Raka cipta
       * head_img : https://scontent.xx.fbcdn
       * .net/v/t1.0-1/p50x50/22195328_120618232022151_7138150790550753966_n
       * .jpg?oh=94d71eb1da76519b3f2e465284a17bba&oe=5A7C459E
       * score : 3.00
       * content : Sampai saat ini blum ada buktinya sma skali di klw anda bner bsa mmbantu.dan
       * ingin mmbantu orang" yg sdng ksusahan tolong buktikan....Sy ggavprlu hrapan tpi bukti
       * .smua orng pun pzt brhrap sprti itu.
       * tag : 0
       * uptime : 2018-01-31 21:30:18
       * sort : 0
       * review_date : November 09, 2017
       * file : []
       */

      private String nickname;
      private String head_img;
      private String score;
      private String content;
      private String tag;
      private String uptime;
      private String sort;
      private String review_date;
      private List<?> file;

      public String getNickname() {
        return nickname;
      }

      public void setNickname(String nickname) {
        this.nickname = nickname;
      }

      public String getHead_img() {
        return head_img;
      }

      public void setHead_img(String head_img) {
        this.head_img = head_img;
      }

      public String getScore() {
        return score;
      }

      public void setScore(String score) {
        this.score = score;
      }

      public String getContent() {
        return content;
      }

      public void setContent(String content) {
        this.content = content;
      }

      public String getTag() {
        return tag;
      }

      public void setTag(String tag) {
        this.tag = tag;
      }

      public String getUptime() {
        return uptime;
      }

      public void setUptime(String uptime) {
        this.uptime = uptime;
      }

      public String getSort() {
        return sort;
      }

      public void setSort(String sort) {
        this.sort = sort;
      }

      public String getReview_date() {
        return review_date;
      }

      public void setReview_date(String review_date) {
        this.review_date = review_date;
      }

      public List<?> getFile() {
        return file;
      }

      public void setFile(List<?> file) {
        this.file = file;
      }
    }
  }
}
