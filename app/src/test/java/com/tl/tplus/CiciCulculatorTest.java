package com.tl.tplus;

import com.alibaba.fastjson.JSON;
import com.tl.tplus.base.api.bean.RequestBean;
import com.tl.tplus.base.api.bean.ResultBean;
import com.tl.tplus.cicidetail.api.CicidetailApiService;
import com.tl.tplus.cicidetail.mvp.CicidetailBean;
import com.tl.tplus.util.CommonUtil;
import com.tl.tplus.util.ConstanceValue;
import com.tl.tplus.utils.MockRetrofitHelper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import rx.observers.TestSubscriber;

import static org.junit.Assert.assertEquals;

public class CiciCulculatorTest {

  private CicidetailApiService cicidetailApiService;

  @Before
  public void setUp() throws Exception {

    MockitoAnnotations.initMocks(this);
    MockRetrofitHelper.Builder builder = new MockRetrofitHelper.Builder();
    builder.connectTime(30);
    builder.readTime(30);
    builder.writeTime(30);

    cicidetailApiService = builder
        .builder()
        .create(CicidetailApiService.class);
  }

  @Test
  public void searchResultTest() throws Exception {
    TestSubscriber<CicidetailBean> testSubscriber = new TestSubscriber<>();
    RequestBean commonRequestBean = new RequestBean();
    commonRequestBean.setApp_type(ConstanceValue.APP_TYPE);
    commonRequestBean.setApp_version(32);
    commonRequestBean.setApp_package(ConstanceValue.PACKAGENAME);
    commonRequestBean.setChannel(ConstanceValue.CHANNEL);
    commonRequestBean.setVersion(ConstanceValue.VERSION);
    RequestBean.DeviceInfoEntity entity = new RequestBean.DeviceInfoEntity();
    entity.setAnd_id(ConstanceValue.AND_ID);
    entity.setGaid(ConstanceValue.GAID);
    entity.setImei(ConstanceValue.IMEI);
    entity.setSn(ConstanceValue.SN);
    entity.setModel(ConstanceValue.MODEL);
    entity.setBrand(ConstanceValue.BRAND);
    commonRequestBean.setDevice_info(entity);
    commonRequestBean.setPid(3);
    String sign = CommonUtil.RequestSignData(commonRequestBean);
    commonRequestBean.setSign(sign);
    commonRequestBean.setTimestamp(System.currentTimeMillis() / 1000);
    String data = JSON.toJSONString(commonRequestBean);
    cicidetailApiService.getDetailData(data)
        .toBlocking()
        .subscribe(testSubscriber);

    CicidetailBean bean = testSubscriber.getOnNextEvents().get(0);
    assertEquals(bean.getResult(), 0);
  }





}
