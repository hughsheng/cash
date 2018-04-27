package com.tl.tplus.cicidetail.mvp;

import com.tl.tplus.base.api.bean.BaseResultBean;

import java.util.List;

/**
 * Created by sztangli on 2018-3-7.
 *
 */

public class CicidetailReviewBean extends BaseResultBean {

  /**
   * data : {"google_score":"4.20","google_ranking":"8","xb_review":"Angsuran panjang / Tiga
   * langkah pinjam uang","user_review":[{"nickname":"A Google user","head_img":"https://scontent
   * .xx.fbcdn.net/v/t1.0-1/c0.2.50.50/p50x50/20842244_1412642885490722_7514672985599343228_n
   * .jpg?oh=f63e293271bd296dbed930e9bff5859f&oe=5A7D559A","score":"5.00","content":"proses
   * kredit pling cpt ,pkonya top deh home credit","tag":"0","sort":"99","uptime":"2018-01-31
   * 19:06:20","review_date":"October 26, 2017","file":[]},{"nickname":"aris manutik",
   * "head_img":"https://scontent.xx.fbcdn
   * .net/v/t1.0-1/c0.0.50.50/p50x50/18342268_1731145713567548_7570546893567230950_n
   * .jpg?_nc_eui2=v1
   * %3AAeHkwxIsqkqfk2QY24j8H0C416F08cvpGSTDpD9fxIkumUg6cUzYwtU2FqJ4eTI6T9Eux0otVpnzaNcPQSs5Rjgk
   * &oh=2f39d91669a6f5c7d82bdb6d68c6db32&oe=5A97026A","score":"5.00","content":"Pelayanan sangat
   * memuaskan, sangat sopan terhadap orang yang ingin mengajukan kredit","tag":"0","sort":"99",
   * "uptime":"2018-01-31 21:22:06","review_date":"October 26, 2017","file":[]},{"nickname":"Dewi
   * Rha","head_img":"https://scontent.xx.fbcdn
   * .net/v/t1.0-1/p50x50/14045795_862243063907477_575466710499382576_n
   * .jpg?oh=f613d086d8d0276c27827ab97b8574a5&oe=5A3DB499","score":"1.00","content":"Pelunasan 6
   * bulan sekaligus dibulan april...eeehhh udah 4 hari ini ditelp mati2an kata nya masi ada
   * tunggakan. Tiap minta penjelasan ga digubris...ngotot suruh bayar ajj.","tag":"0",
   * "sort":"99","uptime":"2017-11-28 16:44:42","review_date":"October 25, 2017","file":[]},
   * {"nickname":"feri zukarlia","head_img":"https://z-p3-scontent.xx.fbcdn
   * .net/v/t1.0-1/c74.165.466.466/s50x50/528476_365321150212327_600548633_n
   * .jpg?oh=2bfca8cc8b4fafa24ee276185d71f801&oe=5A9FE27D","score":"5.00","content":"Selama
   * credit gak ada masalah... Asalkan kita tahu prosedur nya.","tag":"0","sort":"99",
   * "uptime":"2018-01-31 21:25:25","review_date":"October 25, 2017","file":[]},
   * {"nickname":"Subur Supriyadi","head_img":"https://scontent.xx.fbcdn
   * .net/v/t1.0-1/p50x50/20708240_345187615902243_8203180788036363580_n
   * .jpg?oh=2bc002f6f73ebcf13f4e77d2ae88a31b&oe=5A6A8DE5","score":"5.00","content":"Terima Kasih
   * home credit. Saya bisa kredit hp buat anak saya","tag":"0","sort":"99","uptime":"2017-11-28
   * 16:43:28","review_date":"October 23, 2017","file":[]},{"nickname":"Agus Wijaya",
   * "head_img":"https://scontent.xx.fbcdn
   * .net/v/t1.0-1/c0.0.50.50/p50x50/21232134_1532652626815601_1880951202138662753_n
   * .jpg?oh=f647f337b7b01aa2801d1f2722285f1d&oe=5A839145","score":"1.00","content":"ribet,
   * progress pembayaran bagus.. ttp aj ditlp terus, kelewat lebay lah. ganggu bgt privasi !!!",
   * "tag":"0","sort":"99","uptime":"2017-11-28 16:45:04","review_date":"October 16, 2017",
   * "file":[]},{"nickname":"Utha Vr","head_img":"https://scontent.xx.fbcdn
   * .net/v/t1.0-1/p50x50/17203247_109453452921625_1997430703863099094_n
   * .jpg?oh=74ea88b6fec7a816816dcf1d55142c11&oe=5A7AB7E8","score":"3.00","content":"Lebh ri
   * perbagus jaringan, biar msknya g lemot","tag":"0","sort":"99","uptime":"2017-11-28
   * 16:43:58","review_date":"October 11, 2017","file":[]},{"nickname":"mastur safei",
   * "head_img":"https://scontent.xx.fbcdn
   * .net/v/t1.0-1/c118.328.304.304/s50x50/19656926_1863711523883895_6814715772370677924_n
   * .jpg?_nc_eui2=v1
   * %3AAeEtO5Wsj9XdtPiKz1VHVJdEDlrfpWqlEyz0A0x4NSCI69nFujNBLG2yvYRjo9exdZ3zMYFyn40ScteuQcI5IHdT
   * &oh=7a2bbe7efa1daf0b92fa91f36d84be4d&oe=5A843637","score":"3.00","content":"Kenapa ya
   * setelah diperbarui ko lama bukanya","tag":"0","sort":"99","uptime":"2017-11-28 16:44:19",
   * "review_date":"October 06, 2017","file":[]}]}
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
     * google_score : 4.20
     * google_ranking : 8
     * xb_review : Angsuran panjang / Tiga langkah pinjam uang
     * user_review : [{"nickname":"A Google user","head_img":"https://scontent.xx.fbcdn
     * .net/v/t1.0-1/c0.2.50.50/p50x50/20842244_1412642885490722_7514672985599343228_n
     * .jpg?oh=f63e293271bd296dbed930e9bff5859f&oe=5A7D559A","score":"5.00","content":"proses
     * kredit pling cpt ,pkonya top deh home credit","tag":"0","sort":"99","uptime":"2018-01-31
     * 19:06:20","review_date":"October 26, 2017","file":[]},{"nickname":"aris manutik",
     * "head_img":"https://scontent.xx.fbcdn
     * .net/v/t1.0-1/c0.0.50.50/p50x50/18342268_1731145713567548_7570546893567230950_n
     * .jpg?_nc_eui2=v1
     * %3AAeHkwxIsqkqfk2QY24j8H0C416F08cvpGSTDpD9fxIkumUg6cUzYwtU2FqJ4eTI6T9Eux0otVpnzaNcPQSs5Rjgk&oh=2f39d91669a6f5c7d82bdb6d68c6db32&oe=5A97026A","score":"5.00","content":"Pelayanan sangat memuaskan, sangat sopan terhadap orang yang ingin mengajukan kredit","tag":"0","sort":"99","uptime":"2018-01-31 21:22:06","review_date":"October 26, 2017","file":[]},{"nickname":"Dewi Rha","head_img":"https://scontent.xx.fbcdn.net/v/t1.0-1/p50x50/14045795_862243063907477_575466710499382576_n.jpg?oh=f613d086d8d0276c27827ab97b8574a5&oe=5A3DB499","score":"1.00","content":"Pelunasan 6 bulan sekaligus dibulan april...eeehhh udah 4 hari ini ditelp mati2an kata nya masi ada tunggakan. Tiap minta penjelasan ga digubris...ngotot suruh bayar ajj.","tag":"0","sort":"99","uptime":"2017-11-28 16:44:42","review_date":"October 25, 2017","file":[]},{"nickname":"feri zukarlia","head_img":"https://z-p3-scontent.xx.fbcdn.net/v/t1.0-1/c74.165.466.466/s50x50/528476_365321150212327_600548633_n.jpg?oh=2bfca8cc8b4fafa24ee276185d71f801&oe=5A9FE27D","score":"5.00","content":"Selama credit gak ada masalah... Asalkan kita tahu prosedur nya.","tag":"0","sort":"99","uptime":"2018-01-31 21:25:25","review_date":"October 25, 2017","file":[]},{"nickname":"Subur Supriyadi","head_img":"https://scontent.xx.fbcdn.net/v/t1.0-1/p50x50/20708240_345187615902243_8203180788036363580_n.jpg?oh=2bc002f6f73ebcf13f4e77d2ae88a31b&oe=5A6A8DE5","score":"5.00","content":"Terima Kasih home credit. Saya bisa kredit hp buat anak saya","tag":"0","sort":"99","uptime":"2017-11-28 16:43:28","review_date":"October 23, 2017","file":[]},{"nickname":"Agus Wijaya","head_img":"https://scontent.xx.fbcdn.net/v/t1.0-1/c0.0.50.50/p50x50/21232134_1532652626815601_1880951202138662753_n.jpg?oh=f647f337b7b01aa2801d1f2722285f1d&oe=5A839145","score":"1.00","content":"ribet, progress pembayaran bagus.. ttp aj ditlp terus, kelewat lebay lah. ganggu bgt privasi !!!","tag":"0","sort":"99","uptime":"2017-11-28 16:45:04","review_date":"October 16, 2017","file":[]},{"nickname":"Utha Vr","head_img":"https://scontent.xx.fbcdn.net/v/t1.0-1/p50x50/17203247_109453452921625_1997430703863099094_n.jpg?oh=74ea88b6fec7a816816dcf1d55142c11&oe=5A7AB7E8","score":"3.00","content":"Lebh ri perbagus jaringan, biar msknya g lemot","tag":"0","sort":"99","uptime":"2017-11-28 16:43:58","review_date":"October 11, 2017","file":[]},{"nickname":"mastur safei","head_img":"https://scontent.xx.fbcdn.net/v/t1.0-1/c118.328.304.304/s50x50/19656926_1863711523883895_6814715772370677924_n.jpg?_nc_eui2=v1%3AAeEtO5Wsj9XdtPiKz1VHVJdEDlrfpWqlEyz0A0x4NSCI69nFujNBLG2yvYRjo9exdZ3zMYFyn40ScteuQcI5IHdT&oh=7a2bbe7efa1daf0b92fa91f36d84be4d&oe=5A843637","score":"3.00","content":"Kenapa ya setelah diperbarui ko lama bukanya","tag":"0","sort":"99","uptime":"2017-11-28 16:44:19","review_date":"October 06, 2017","file":[]}]
     */

    private String google_score;
    private String google_ranking;
    private String xb_review;
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

    public List<UserReviewBean> getUser_review() {
      return user_review;
    }

    public void setUser_review(List<UserReviewBean> user_review) {
      this.user_review = user_review;
    }

    public static class UserReviewBean {
      /**
       * nickname : A Google user
       * head_img : https://scontent.xx.fbcdn
       * .net/v/t1.0-1/c0.2.50.50/p50x50/20842244_1412642885490722_7514672985599343228_n
       * .jpg?oh=f63e293271bd296dbed930e9bff5859f&oe=5A7D559A
       * score : 5.00
       * content : proses kredit pling cpt ,pkonya top deh home credit
       * tag : 0
       * sort : 99
       * uptime : 2018-01-31 19:06:20
       * review_date : October 26, 2017
       * file : []
       */

      private String nickname;
      private String head_img;
      private String score;
      private String content;
      private String tag;
      private String sort;
      private String uptime;
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

      public String getSort() {
        return sort;
      }

      public void setSort(String sort) {
        this.sort = sort;
      }

      public String getUptime() {
        return uptime;
      }

      public void setUptime(String uptime) {
        this.uptime = uptime;
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
