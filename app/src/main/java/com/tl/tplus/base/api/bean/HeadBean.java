package com.tl.tplus.base.api.bean;

/**
 * Created by lfp on 2016/11/28.
 * 头部信息
 */

public class HeadBean {


  /**
   * app_type : android
   * app_version : 15 App版本号
   * banner_type : 1
   * channel : APP 接口渠道 html
   * device_info : {"and_id":"1234567890",安卓ID
   * "gaid":"zxcvbnmasdfghjkl",Gaid
   * "imei":"aaaaaaaaa",手机imei
   * "sn":"bbbbbbbbb",手机sn号
   * "model":"M1",手机型号
   * "brand":"oppo"}手机品牌
   * pid : 124
   * position : 123.123456,234.567892
   * version : 15 服务器端版本号
   * sign : 46c45c818ed99b2c9fe7289b666322cc 签名
   * timestamp : 1516878359 时间戳
   */

  private String app_type;
  private int app_version;
  private int banner_type;
  private String channel;
  private DeviceInfoBean device_info;
  private int pid;
  private String position;
  private int version;
  private String sign;
  private int timestamp;

  public String getApp_type() {
    return app_type;
  }

  public void setApp_type(String app_type) {
    this.app_type = app_type;
  }

  public int getApp_version() {
    return app_version;
  }

  public void setApp_version(int app_version) {
    this.app_version = app_version;
  }

  public int getBanner_type() {
    return banner_type;
  }

  public void setBanner_type(int banner_type) {
    this.banner_type = banner_type;
  }

  public String getChannel() {
    return channel;
  }

  public void setChannel(String channel) {
    this.channel = channel;
  }

  public DeviceInfoBean getDevice_info() {
    return device_info;
  }

  public void setDevice_info(DeviceInfoBean device_info) {
    this.device_info = device_info;
  }

  public int getPid() {
    return pid;
  }

  public void setPid(int pid) {
    this.pid = pid;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }

  public int getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(int timestamp) {
    this.timestamp = timestamp;
  }

  public static class DeviceInfoBean {
    /**
     * and_id : 1234567890
     * gaid : zxcvbnmasdfghjkl
     * imei : aaaaaaaaa
     * sn : bbbbbbbbb
     * model : M1
     * brand : oppo
     */

    private String and_id;
    private String gaid;
    private String imei;
    private String sn;
    private String model;
    private String brand;

    public String getAnd_id() {
      return and_id;
    }

    public void setAnd_id(String and_id) {
      this.and_id = and_id;
    }

    public String getGaid() {
      return gaid;
    }

    public void setGaid(String gaid) {
      this.gaid = gaid;
    }

    public String getImei() {
      return imei;
    }

    public void setImei(String imei) {
      this.imei = imei;
    }

    public String getSn() {
      return sn;
    }

    public void setSn(String sn) {
      this.sn = sn;
    }

    public String getModel() {
      return model;
    }

    public void setModel(String model) {
      this.model = model;
    }

    public String getBrand() {
      return brand;
    }

    public void setBrand(String brand) {
      this.brand = brand;
    }
  }
}
