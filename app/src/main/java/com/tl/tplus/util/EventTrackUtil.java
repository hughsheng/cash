package com.tl.tplus.util;

import android.content.Context;

import com.appsflyer.AFInAppEventParameterName;
import com.appsflyer.AppsFlyerLib;
import com.tl.tplus.base.app.CashApplication;

import java.util.HashMap;
import java.util.Map;

import retrofit2.http.PUT;

/**
 * Created by Administrator on 2017/2/22 0022.
 * <p>
 * 统计事件
 */

public class EventTrackUtil {

    public static final String BOSTUNAI_HOME_PAGEVIEW = "tplus_home_pageview";

    public static final String BOSTUNAI_HOME_TAB_CREDIT_CLICK = "tplus_home_tab_credit_click";

    public static final String BOSTUNAI_HOME_TAB_CICILAN_CLICK = "tplus_home_tab_cicilan_click";

    public static final String BOSTUNAI_DETAILS_OPEN_CASHCREDIT = "tplus_details_open_cashcredit";

    public static final String BOSTUNAI_DETAILS_OPEN_INSTALLMENT = "tplus_details_open_installment";

    public static final String BOSTUNAI_DETAILS_DOWNLOAD_CASHCREDIT = "tplus_details_download_cashcredit";

    public static final String BOSTUNAI_DETAILS_DOWNLOAD_INSTALLMENT = "tplus_details_download_installment";

    public static final String BOSTUNAI_DETAILS_BUTTON_BORROW_CLICK = "tplus_details_button_borrow_click";

    public static final String BOSTUNAI_HOME_PROFILE_CLICK = "tplus_home_profile_click";

    public static final String BOSTUNAI_HOME_LIST_CLICK = "tplus_home_list_click";

    public static final String BOSTUNAI_HOME_BANNER_CLICK = "tplus_home_banner_click";

    public static final String BOSTUNAI_DETAILS_PAGEVIEW = "tplus_details_pageview";

    public static final String BOSTUNAI_DETAILS_TIPS_CLICK = "tplus_details_tips_click";

    public static final String BOSTUNAI_DETAILS_MORE_DETAILS_CLICK = "tplus_details_more_details_click";

    public static final String BOSTUNAI_DETAILS_CALCULATOR_CLICK = "tplus_details_calculator_click";


    public static final String BOSTUNAI_DETAILS_TAB_DETAIL_CLICK = "tplus_details_tab_detail_click";

    public static final String BOSTUNAI_DETAILS_TAB_FLOW_CLICK = "tplus_details_tab_flow_click";

    public static final String BOSTUNAI_DETAILS_TAB_COMMENT_CLICK = "tplus_details_tab_comment_click";

    public static final String BOSTUNAI_PROFILE_SET_CLICK = "tplus_profile_set_click";

    public static final String BOSTUNAI_PROFILE_ABOUTUS_CLICK = "tplus_profile_aboutus_click";

    private EventTrackUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 统计点击事件
     *
     * @param value
     */
    public static void trackEventApp(String value) {
        Context context = CashApplication.getInstance().getApplicationContext();

        // Appsflyer
        Map<String, Object> eventValue = new HashMap<String, Object>();
        eventValue.put(AFInAppEventParameterName.SUCCESS, value);
        AppsFlyerLib.getInstance().trackEvent(context, value, eventValue);

    }

}
