package com.tl.tplus.strategy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.tl.tplus.R;
import com.tl.tplus.base.api.bean.ErrorBean;
import com.tl.tplus.base.api.bean.RequestBean;
import com.tl.tplus.base.fragment.BaseFragment;
import com.tl.tplus.detail.DetailActivity;
import com.tl.tplus.detail.DetailComFragment;
import com.tl.tplus.strategy.adapter.StrategyAdapter;
import com.tl.tplus.strategy.mvp.StrategyBean;
import com.tl.tplus.strategy.mvp.StrategyContract;
import com.tl.tplus.util.CommonUtil;
import com.tl.tplus.util.ConstanceValue;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/10.
 */

public class StrategyFragment extends BaseFragment implements StrategyContract.IView {

  public static final String FWID = "fw_id";
  public static final String FWTYPY = "fw_type";
  public static final String TAG = "StrategyFragment";
  public static final String NAME = "name";

  @BindView(com.tl.tplus.R.id.filter_list)
  RecyclerView filter_list;

  @Inject
  StrategyContract.Presenter mPresenter;

  public static StrategyFragment newInstance(String fw_id, String fw_type) {

    Bundle args = new Bundle();
    args.putString(FWID, fw_id);
    args.putString(FWTYPY, fw_type);
    StrategyFragment fragment = new StrategyFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void showUi(StrategyBean strategyBean) {
    StrategyAdapter adapter = new StrategyAdapter(getContext(), strategyBean.getData());
    adapter.setOnItemClickListener(new StrategyAdapter.OnItemClickListener() {
      @Override
      public void onItemClick(String tag, int type) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(DetailComFragment.PID, tag);
        intent.putExtra(DetailComFragment.TYPE, type);
        startActivity(intent);
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
  public void setPresenter(@NonNull StrategyContract.Presenter presenter) {
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
    Bundle bundle = getArguments();
    requestBean.setFw_id(bundle.getString(FWID));
    requestBean.setFw_type(bundle.getString(FWTYPY));
    String sign = CommonUtil.RequestSignData(requestBean);
    requestBean.setSign(sign);
    requestBean.setTimestamp(System.currentTimeMillis() / 1000);
    String data = JSON.toJSONString(requestBean);
    mPresenter.getUIData(data);
  }
}
