package com.tl.tplus.util;

import com.tl.tplus.home.mvp.FilterListBean;
import com.tl.tplus.home.mvp.HomeBannerBean;

import java.util.List;

/**
 * Created by sztangli on 2018-2-26.
 * 用于保存一下基本信息
 */

//    com.tl.bostunai
//    com.tl.pinjamanduit
//    com.tl.sakupinjaman
//    com.tl.pinjamaja
//    com.tl.cashmarket
//    com.tl.danakilat
//    com.tl.danapintar
//    com.tl.kreditcepat
//    com.tl.kreditkilat
//    com.tl.danasegar
//    com.tl.tokokredit
//    com.tl.tplus

public final class ConstanceValue {

  public static String PACKAGENAME="com.tl.tplus";

  public static String APPNAME="Tunai Plus";

  public static  String GAID="0";      //广告ID

  public static String AND_ID="0";    //设备ID

  public static String IMEI="0";      //手机imei

  public static String SN="611A3CR322495";        //手机sn号

  public static String MODEL="0";     //手机型号

  public static String BRAND="0";     //手机品牌

  public static final String APP_TYPE="android"; //app类型

  public static final String CHANNEL="app"; //app渠道

  public static final int VERSION=17; //服务器版本号

  public static final int APP_VERSION=20;//app版本号

  public static final String TIME_MIN="time_min"; //最短借贷时间

  public static final String TIME_MAX="time_max"; //最长借贷时间

  public static final String TIMETYPE="time_type";//时间类型

  public static String COMMREQUESTBEAN; //公共请求基础bean

  public static FilterListBean FILTERLISTBEAN=null;//筛选接口列表数据

  public static List<String> INSTALLLIST=null;//已安装app列表

  public static String GUID="";//guid

  public static String FIRSTINSTALL="first_install";//是否第一次安装

  public static List<HomeBannerBean.DataBean.FilterWordsBean> TYPEBEAN=null;

  public static final String KEY="zmaoniany@mjb@tao!cashcash96300"; //接口密钥
}
