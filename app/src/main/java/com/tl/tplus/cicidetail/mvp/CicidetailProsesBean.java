package com.tl.tplus.cicidetail.mvp;

import com.tl.tplus.base.api.bean.BaseResultBean;

import java.util.List;

/**
 * Created by sztangli on 2018-3-7.
 *
 */

public class CicidetailProsesBean extends BaseResultBean {

  /**
   * data : {"procedure":[{"step":"1","title":"memilih jumlah pinjaman","icon":"http://testfiles
   * .cashcash.id/uploads/product/cash_credit/procedure/icon/2017-11-25/1231231511584749.png"},
   * {"step":"2","title":"Mengisi informasi ","icon":"http://testfiles.cashcash
   * .id/uploads/product/cash_credit/procedure/icon/2017-11-25/1231231511584802.png"},
   * {"step":"3","title":"mengisi nomor rekening untuk ditransfer","icon":"http://testfiles
   * .cashcash.id/uploads/product/cash_credit/procedure/icon/2017-11-25/1231231511584866.png"},
   * {"step":"4","title":"memilih jumlah pinjaman","icon":"http://testfiles.cashcash
   * .id/uploads/product/cash_credit/procedure/icon/2017-11-25/1231231511584749.png"},
   * {"step":"5","title":"pinjaman masuk ke rek kita","icon":"http://testfiles.cashcash
   * .id/uploads/product/cash_credit/procedure/icon/2017-11-16/1231231510834454.png"}],
   * "audit_info":[{"title":"Informasi pribadi","describe":"nama,jenis kelamin,edukasi,no
   * ktp\nstatus perkawinan, jumlah anak,  nama Ibu \nprovinsi hunian, kota hunian, kecamatan
   * hunian, kelurahan hunian, alamat, durasi hunian","icon":"http://testfiles.cashcash
   * .id/uploads/product/cash_credit/audit_info/2017-11-16/1231231510834829.png","sort":"1"},
   * {"title":"Informasi pekerjaan","describe":"tipe pekerjaan, pendapatan bulanan\nnama
   * perusahaan,provinsi perusahaan, kota perusahaan, kecamatan perusahaan \nkelurahan
   * perusahaan, alamat perusahaan","icon":"http://testfiles.cashcash
   * .id/uploads/product/cash_credit/audit_info/2017-11-27/1231231511766901.png","sort":"2"},
   * {"title":"Hubungan sosial","describe":"2 orang hubungan sosial","icon":"http://testfiles
   * .cashcash.id/uploads/product/cash_credit/audit_info/2017-11-27/1231231511767106.png",
   * "sort":"3"},{"title":"Gambar","describe":" Foto KTP & Foto Kartu Pekerja ",
   * "icon":"http://testfiles.cashcash
   * .id/uploads/product/cash_credit/audit_info/2017-11-16/1231231510835449.png","sort":"4"}]}
   */

  private DataBean data;

  public DataBean getData() {
    return data;
  }

  public void setData(DataBean data) {
    this.data = data;
  }

  public static class DataBean {
    private List<ProcedureBean> procedure;
    private List<AuditInfoBean> audit_info;

    public List<ProcedureBean> getProcedure() {
      return procedure;
    }

    public void setProcedure(List<ProcedureBean> procedure) {
      this.procedure = procedure;
    }

    public List<AuditInfoBean> getAudit_info() {
      return audit_info;
    }

    public void setAudit_info(List<AuditInfoBean> audit_info) {
      this.audit_info = audit_info;
    }

    public static class ProcedureBean {
      /**
       * step : 1
       * title : memilih jumlah pinjaman
       * icon : http://testfiles.cashcash
       * .id/uploads/product/cash_credit/procedure/icon/2017-11-25/1231231511584749.png
       */

      private String step;
      private String title;
      private String icon;

      public String getStep() {
        return step;
      }

      public void setStep(String step) {
        this.step = step;
      }

      public String getTitle() {
        return title;
      }

      public void setTitle(String title) {
        this.title = title;
      }

      public String getIcon() {
        return icon;
      }

      public void setIcon(String icon) {
        this.icon = icon;
      }
    }

    public static class AuditInfoBean {
      /**
       * title : Informasi pribadi
       * describe : nama,jenis kelamin,edukasi,no ktp
       status perkawinan, jumlah anak,  nama Ibu
       provinsi hunian, kota hunian, kecamatan hunian, kelurahan hunian, alamat, durasi hunian
       * icon : http://testfiles.cashcash
       * .id/uploads/product/cash_credit/audit_info/2017-11-16/1231231510834829.png
       * sort : 1
       */

      private String title;
      private String describe;
      private String icon;
      private String sort;

      public String getTitle() {
        return title;
      }

      public void setTitle(String title) {
        this.title = title;
      }

      public String getDescribe() {
        return describe;
      }

      public void setDescribe(String describe) {
        this.describe = describe;
      }

      public String getIcon() {
        return icon;
      }

      public void setIcon(String icon) {
        this.icon = icon;
      }

      public String getSort() {
        return sort;
      }

      public void setSort(String sort) {
        this.sort = sort;
      }
    }
  }
}
