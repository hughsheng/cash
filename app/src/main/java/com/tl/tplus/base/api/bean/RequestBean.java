package com.tl.tplus.base.api.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by lfp on 2016/11/14.
 * 请求bean
 */

public class RequestBean {
  @JSONField(ordinal = 1)
  String address_id;              //用户选择的address_id (非必含参数,不参加签名)
  //String api_channel;           //接口渠道html (必含参数,参加签名)
  @JSONField(ordinal = 2)
  String app_package;             //app包名
  @JSONField(ordinal = 3)
  String app_type;                //app类型：android(必含参数,参加签名)
  @JSONField(ordinal = 4)
  Integer app_version;                //app版本号 (必含参数,参加签名)
  @JSONField(ordinal = 5)
  Integer banner_type;                //获取的banner类型，1现金贷类型，2分期类型，0 全部类型(非必含参数,不参加签名)
  @JSONField(ordinal = 6)
  String channel;                 //渠道：app(必含参数,参加签名)
  @JSONField(ordinal = 7)
  DeviceInfoEntity device_info;  //设备信息(必含参数,参加签名)
  @JSONField(ordinal = 8)
  String fw_id;                  //获取删选select的key值(参加签名)
  @JSONField(ordinal = 9)
  String fw_type;                //1获取现金贷删选产品信息 2获取分期贷删选产品信息(参加签名)
  @JSONField(ordinal = 10)
  String guid;                   //guid
  @JSONField(ordinal = 11)
  String interest_id;            //用户选择的interest_id (非必含参数,不参加签名)
  @JSONField(ordinal = 12)
  String limit;                  //列表一页数据条数,默认为10(非必含参数,不参加签名)
  @JSONField(ordinal = 13)
  String money_id;               //用户选择的money_id (非必含参数,不参加签名)
  @JSONField(ordinal = 14)
  List<String> package_name;
  @JSONField(ordinal = 15)
  Integer pid;                       //产品id(参加签名)
  @JSONField(ordinal = 16)
  String position;               //位置
  @JSONField(ordinal = 17)
  Integer version;                   //服务器端版本号 (必含参数,参加签名)
  @JSONField(ordinal = 18)
  String sign;                   //签名
  @JSONField(ordinal = 19)
  String start;                  //列表数据起始位置,默认为0(非必含参数,不参加签名)
  @JSONField(ordinal = 20)
  String time_id;                //用户选择的time_id(非必含参数,不参加签名)
  @JSONField(ordinal = 21)
  Long timestamp;                 //时间戳(必含参数,参加签名)
  @JSONField(ordinal = 22)
  String product_type;                 //商品类型
  @JSONField(ordinal = 23)
  String click_type;                 // 点击类型

  public List<String> getPackage_name() {
    return package_name;
  }

  public void setPackage_name(List<String> package_name) {
    this.package_name = package_name;
  }

  public String getApp_package() {
    return app_package;
  }

  public void setApp_package(String app_package) {
    this.app_package = app_package;
  }

  public String getAddress_id() {
    return address_id;
  }

  public void setAddress_id(String address_id) {
    this.address_id = address_id;
  }

  public String getApp_type() {
    return app_type;
  }

  public void setApp_type(String app_type) {
    this.app_type = app_type;
  }

  public Integer getApp_version() {
    return app_version;
  }

  public void setApp_version(Integer app_version) {
    this.app_version = app_version;
  }

  public Integer getBanner_type() {
    return banner_type;
  }

  public void setBanner_type(Integer banner_type) {
    this.banner_type = banner_type;
  }

  public String getChannel() {
    return channel;
  }

  public void setChannel(String channel) {
    this.channel = channel;
  }

  public DeviceInfoEntity getDevice_info() {
    return device_info;
  }

  public void setDevice_info(DeviceInfoEntity device_info) {
    this.device_info = device_info;
  }

  public String getFw_id() {
    return fw_id;
  }

  public void setFw_id(String fw_id) {
    this.fw_id = fw_id;
  }

  public String getFw_type() {
    return fw_type;
  }

  public void setFw_type(String fw_type) {
    this.fw_type = fw_type;
  }

  public String getInterest_id() {
    return interest_id;
  }

  public void setInterest_id(String interest_id) {
    this.interest_id = interest_id;
  }

  public String getLimit() {
    return limit;
  }

  public void setLimit(String limit) {
    this.limit = limit;
  }

  public String getMoney_id() {
    return money_id;
  }

  public void setMoney_id(String money_id) {
    this.money_id = money_id;
  }

  public Integer getPid() {
    return pid;
  }

  public void setPid(Integer pid) {
    this.pid = pid;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }

  public String getStart() {
    return start;
  }

  public void setStart(String start) {
    this.start = start;
  }

  public String getTime_id() {
    return time_id;
  }

  public void setTime_id(String time_id) {
    this.time_id = time_id;
  }

  public Long getTimestamp() {
    return timestamp;
  }

    public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }

  public String getGuid() {
    return guid;
  }

  public void setGuid(String guid) {
    this.guid = guid;
  }

  public String getProduct_type() {
    return product_type;
  }

  public void setProduct_type(String product_type) {
    this.product_type = product_type;
  }

  public String getClick_type() {
    return click_type;
  }

  public void setClick_type(String click_type) {
    this.click_type = click_type;
  }

  public static class DeviceInfoEntity{
    String and_id;  //安卓ID (必含参数,参加签名)
    String gaid;    //Gaid (必含参数,参加签名)
    String imei;    //手机imei (非必含参数,不参加签名)
    String sn;      //手机sn号 (非必含参数,不参加签名)
    String model;   //手机型号 (非必含参数,不参加签名)
    String brand;   //手机品牌 (非必含参数,不参加签名)

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
