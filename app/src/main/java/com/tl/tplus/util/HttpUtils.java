package com.tl.tplus.util;

import com.tl.tplus.BuildConfig;

/**
 * Created by Administrator on 2018/2/15.
 * 网络接口管理类
 */

public class HttpUtils {

  public static final String BASE_URL = BuildConfig.SERVER_URL;

  //首页Banner
  public static final String  HOME_GET_BANNER="home/get-banner";

  //获取现金贷产品列表接口
  public static final String PRODUCT_CASH_CREDIT_GET_LIST = "product-cash-credit/get-list";

  //获取现金贷产品信息接口
  public static final String PRODUCT_CASH_CREDIT_GET_INFO = "product-cash-credit/get-info";

  //获取现金贷详细信息接口
  public static final String PRODUCT_CASH_CREDIT_GET_DETAILS = "product-cash-credit/get-details";

  //获取现金带评论内容接口
  public static final String PRODUCT_CASH_CREDIT_GET_REVIEW_LIST =
      "product-cash-credit/get-review-list";

  //获取现金贷流程信息接口
  public static final String PRODUCT_CASH_CREDIT_GET_PROCEDURE =
      "product-cash-credit/get-procedure";

  //获取分期贷产品列表接口
  public static final String PRODUCT_INSTALLMENT_GET_LIST = "product-installment/get-list";

  //获取分期贷产品信息接口
  public static final String PRODUCT_INSTALLMENT_GET_INFO = "product-installment/get-info";

  //产品点击
  public static final String PRODUCT_STATISTICS = "statistics/product-click";

  //获取分期贷详细信息接口
  public static final String PRODUCT_INSTALLMENT_GET_DETAILS = "product-installment/get-details";

  //获取分期贷评论内容接口
  public static final String PRODUCT_INSTALLMENT_GET_REVIEW_LIST =
      "product-installment/get-review-list";

  //获取分期贷流程信息接口
  public static final String PRODUCT_INSTALLMENT_GET_PROCEDURE =
      "product-installment/get-procedure";

  //获取产品攻略列表
  public static final String ACTIVITY_STRATEGY_LIST = "activity/strategy-list";

  //获取现金带删选按钮信息
  public static final String FILTER_WORDS_INDEX = "filter-words/index";

  //获取快速选带选择列表接口
  public static final String FAST_SELECT_PRODUCT_SELECT_LIST = "fast-select-product/select-list";

  //获取快速选带产品信息接口 fast-select-product/search-list
  public static final String FAST_SELECT_PRODUCT_SEARCH_LIST = "fast-select-product/new-search-list";

  //已安装应用列表
  public static final String PRODUCT_INSTALLED_GET_LIST="product-installed/get-list";

  //获取用户guid
  public static final String TOURIST_GET_USER_ID="tourist/get-user-id";


  //提交手机已安装app
  public static final String  PACKAGE_UPLOAD_TOURIST_PACKAGE="package/upload-tourist-package";

  //版本更新检查
  public static final String  VERSION_UPDATE_INDEX="version-update/index";

  //统计日活
  public static final String  STATISTICS="statistics/user-day-run";

}
