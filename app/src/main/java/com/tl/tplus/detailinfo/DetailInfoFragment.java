package com.tl.tplus.detailinfo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;

import com.tl.tplus.R;
import com.tl.tplus.base.api.bean.ErrorBean;
import com.tl.tplus.base.api.bean.RequestBean;
import com.tl.tplus.base.fragment.BaseFragment;
import com.tl.tplus.detailinfo.mvp.DetailInfoBean;
import com.tl.tplus.detailinfo.mvp.DetailInfoContract;
import com.tl.tplus.util.CommonUtil;
import com.tl.tplus.util.ConstanceValue;

import javax.inject.Inject;

import butterknife.BindView;


/**
 * Created by Administrator on 2018/3/7.
 *
 */

public class DetailInfoFragment extends BaseFragment implements DetailInfoContract.IView {

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
  @BindView(com.tl.tplus.R.id.info_repayment_discrible)
  TextView info_repayment_discrible;
  public static final String TAG = "CicidetailInfoFragment";
  public static final String PID = "pid";
  public static final String NAME="name";

  @Inject
  DetailInfoContract.Presenter mPresenter;


  public static DetailInfoFragment newInstance(String pid) {

    Bundle args = new Bundle();
    args.putString(PID, pid);
    DetailInfoFragment fragment = new DetailInfoFragment();
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
  public void setPresenter(@NonNull DetailInfoContract.Presenter presenter) {
    mPresenter = presenter;
  }

  @Override
  public void onLoadFail(ErrorBean errorBean) {

  }

  @Override
  public void showUi(DetailInfoBean detailInfoBean) {
    DetailInfoBean.DataBean dataBean=detailInfoBean.getData();
    info_area.setText(dataBean.getArea());
    info_interest_rate.setText(dataBean.getInterest_rate_text());
    info_pass_rate.setText(dataBean.getPass_rate()+"%");
    info_loan_time.setText(dataBean.getLoan_time_text());
    info_repayment_type.setText(dataBean.getRepayment_type());
    info_repayment_method.setText(dataBean.getRepayment_methods_text());
    info_repayment_discrible.setText(dataBean.getRepayment_describe());
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
