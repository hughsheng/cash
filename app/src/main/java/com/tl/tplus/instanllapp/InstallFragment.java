package com.tl.tplus.instanllapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.tl.tplus.R;
import com.tl.tplus.base.api.bean.ErrorBean;
import com.tl.tplus.base.api.bean.RequestBean;
import com.tl.tplus.base.fragment.BaseFragment;
import com.tl.tplus.instanllapp.adapter.InstallAdapter;
import com.tl.tplus.instanllapp.mvp.InstallBean;
import com.tl.tplus.instanllapp.mvp.InstallContract;
import com.tl.tplus.util.CommonUtil;
import com.tl.tplus.util.ConstanceValue;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/10.
 *
 */

public class InstallFragment extends BaseFragment implements InstallContract.IView {

  public static final String MONEYID = "money_id";
  public static final String TIMEID = "time_id";
  public static final String ADDRESSID = "address_id";
  public static final String INTERESTID = "interest_id";
  public static final String TAG = "StrategyFragment";

  @BindView(com.tl.tplus.R.id.filter_list)
  RecyclerView filter_list;

  @Inject
  InstallContract.Presenter mPresenter;

  public static InstallFragment newInstance() {

    Bundle args = new Bundle();
    InstallFragment fragment = new InstallFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void showUi(InstallBean installBean) {
    InstallAdapter adapter=new InstallAdapter(getContext(), installBean.getData());
    adapter.setOnItemClickListener(new InstallAdapter.OnItemClickListener() {
      @Override
      public void onItemClick(String tag, int position) {
        Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage(tag);
        if (intent != null) {
          startActivity(intent);
        } else {
          // 未安装应用
          Toast.makeText(getContext(), "Aplikasi tidak diinstal", Toast.LENGTH_LONG).show();
        }
      }
    });
    filter_list.setLayoutManager(new LinearLayoutManager(getContext()));
    filter_list.setAdapter(adapter);
  }

  @Override
  public void hideLoading() {
    dismiss();
  }

  @Override
  public void showLoading() {
    show(getString(R.string.waiting));
  }

  @Override
  public void setPresenter(@NonNull InstallContract.Presenter presenter) {
    mPresenter = presenter;
  }

  @Override
  public void onLoadFail(ErrorBean errorBean) {

  }

  @Override
  public int getLayoutResId() {
    return com.tl.tplus.R.layout.fragment_filter;
  }

  @Override
  public void onLogicPresenter() {
    RequestBean requestBean = JSON.parseObject(ConstanceValue.COMMREQUESTBEAN, RequestBean.class);
    requestBean.setStart("0");
    requestBean.setLimit("20");
    String sign = CommonUtil.RequestSignData(requestBean);
    requestBean.setSign(sign);
    requestBean.setTimestamp(System.currentTimeMillis() / 1000);
    String data = JSON.toJSONString(requestBean);
    mPresenter.getUIData(data);
  }
}
