package com.tl.tplus.cicidetailinfo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;

import com.tl.tplus.R;
import com.tl.tplus.base.api.bean.ErrorBean;
import com.tl.tplus.base.api.bean.RequestBean;
import com.tl.tplus.base.fragment.BaseFragment;
import com.tl.tplus.cicidetailinfo.mvp.CicidetailInfoBean;
import com.tl.tplus.cicidetailinfo.mvp.CicidetailInfoContract;
import com.tl.tplus.util.CommonUtil;
import com.tl.tplus.util.ConstanceValue;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


/**
 * Created by Administrator on 2018/3/7.
 *
 */

public class CicidetailInfoFragment extends BaseFragment implements CicidetailInfoContract.IView {

  @BindView(com.tl.tplus.R.id.info_area)
  TextView info_area;
  @BindView(com.tl.tplus.R.id.info_interest_rate)
  TextView info_interest_rate;
  @BindView(com.tl.tplus.R.id.info_pass_rate)
  TextView info_pass_rate;
  @BindView(com.tl.tplus.R.id.info_loan_time)
  TextView info_loan_time;
  @BindView(com.tl.tplus.R.id.info_repayment_type)
  TextView info_repayment_type;
  @BindView(com.tl.tplus.R.id.info_repayment_method)
  TextView info_repayment_method;
  @BindView(com.tl.tplus.R.id.info_default_rate)
  TextView info_default_rate;
  @BindView(com.tl.tplus.R.id.info_way)
  TextView info_way;
  @BindView(com.tl.tplus.R.id.info_effort)
  TextView info_effort;
  @BindView(com.tl.tplus.R.id.info_company)
  TextView info_company;
  @BindView(com.tl.tplus.R.id.info_news)
  TextView info_news;
  @BindView(com.tl.tplus.R.id.beria_layout)
  LinearLayout beria_layout;
  @BindView(com.tl.tplus.R.id.cara_layout)
  LinearLayout cara_layout;
  @BindView(R.id.bunga_layout)
  LinearLayout bunga_layout;
  @BindView(R.id.waku_layout)
  LinearLayout waku_layout;

  public static final String TAG = "CicidetailInfoFragment";
  public static final String PID = "pid";
  public static final String NAME = "name";

  @Inject
  CicidetailInfoContract.Presenter mPresenter;


  public static CicidetailInfoFragment newInstance(String pid) {

    Bundle args = new Bundle();
    args.putString(PID, pid);
    CicidetailInfoFragment fragment = new CicidetailInfoFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public int getLayoutResId() {
    return com.tl.tplus.R.layout.detailinfo_layout;
  }

  @Override
  public void onLogicPresenter() {
    RequestBean requestBean = JSON.parseObject(ConstanceValue.COMMREQUESTBEAN, RequestBean.class);
    requestBean.setPid(Integer.valueOf(getArguments().getString(PID)));
    String sign = CommonUtil.RequestSignData(requestBean);
    requestBean.setSign(sign);
    requestBean.setTimestamp(System.currentTimeMillis() / 1000);
    String data = JSON.toJSONString(requestBean);
    mPresenter.getUIData(data);
  }

  @Override
  public void setPresenter(@NonNull CicidetailInfoContract.Presenter presenter) {
    mPresenter = presenter;
  }

  @Override
  public void onLoadFail(ErrorBean errorBean) {

  }

  @Override
  public void showUi(CicidetailInfoBean detailInfoBean) {
    beria_layout.setVisibility(View.GONE);
    cara_layout.setVisibility(View.GONE);
    bunga_layout.setVisibility(View.GONE);
    waku_layout.setVisibility(View.GONE);
    CicidetailInfoBean.DataBean dataBean = detailInfoBean.getData();
    info_area.setText(dataBean.getArea());
    info_interest_rate.setText(dataBean.getInterest_rate_text());
    info_pass_rate.setText(dataBean.getPass_rate() + "%");
    info_loan_time.setText(dataBean.getLoan_time_text());
    info_repayment_type.setText(dataBean.getRepayment_type());
    info_repayment_method.setText(dataBean.getRepayment_methods_text());
    info_default_rate.setText(dataBean.getDefault_rate_text());
    info_way.setText(dataBean.getDunning_way());
    info_effort.setText(dataBean.getDunning_effort());
    info_company.setText(dataBean.getCompany_name());
    info_news.setText(dataBean.getNews());
  }

  @Override
  public void hideLoading() {
    dismiss();
  }

  @Override
  public void showLoading() {
    show(getString(R.string.waiting));
  }
}
