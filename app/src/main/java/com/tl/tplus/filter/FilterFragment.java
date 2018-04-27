package com.tl.tplus.filter;

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
import com.tl.tplus.filter.adapter.FilterAdapter;
import com.tl.tplus.filter.mvp.FilterBean;
import com.tl.tplus.filter.mvp.FilterContract;
import com.tl.tplus.util.CommonUtil;
import com.tl.tplus.util.ConstanceValue;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/10.
 */

public class FilterFragment extends BaseFragment implements FilterContract.IView {

    public static final String MONEYID = "money_id";
    public static final String TIMEID = "time_id";
    public static final String ADDRESSID = "address_id";
    public static final String INTERESTID = "interest_id";
    public static final String TAG = "StrategyFragment";

    private FilterAdapter adapter;
    private List<FilterBean.DataBean> datas;


    @BindView(com.tl.tplus.R.id.filter_list)
    RecyclerView filter_list;

    @Inject
    FilterContract.Presenter mPresenter;

    public static FilterFragment newInstance(String moneyid, String timeid, String addressid,
                                             String interestid) {

        Bundle args = new Bundle();
        args.putString(MONEYID, moneyid);
        args.putString(TIMEID, timeid);
        args.putString(ADDRESSID, addressid);
        args.putString(INTERESTID, interestid);
        FilterFragment fragment = new FilterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void showUi(FilterBean filterBean) {
        if (adapter == null) {
            datas = filterBean.getData();
            adapter = new FilterAdapter(getContext(), datas);
            adapter.setOnItemClickListener(new FilterAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(String tag, int type) {
                    Intent intent = new Intent(getActivity(), DetailActivity.class);
                    intent.putExtra(DetailComFragment.PID, tag);
                    intent.putExtra(DetailComFragment.TYPE,type);
                    startActivity(intent);
                }
            });
            filter_list.setLayoutManager(new LinearLayoutManager(getContext()));
            filter_list.setAdapter(adapter);
        } else {
            datas.clear();
            datas.addAll(filterBean.getData());
            adapter.setOnItemClickListener(new FilterAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(String tag, int type) {
                    Intent intent = new Intent(getActivity(), DetailActivity.class);
                    intent.putExtra(DetailComFragment.PID, tag);
                    intent.putExtra(DetailComFragment.TYPE,type);
                    startActivity(intent);
                }
            });
            adapter.notifyDataSetChanged();
        }
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
    public void setPresenter(@NonNull FilterContract.Presenter presenter) {
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
        requestBean.setMoney_id(bundle.getString(MONEYID));
        requestBean.setAddress_id(bundle.getString(ADDRESSID));
        requestBean.setTime_id(bundle.getString(TIMEID));
        requestBean.setInterest_id(bundle.getString(INTERESTID));
        String sign = CommonUtil.RequestSignData(requestBean);
        requestBean.setSign(sign);
        requestBean.setTimestamp(System.currentTimeMillis() / 1000);
        String data = JSON.toJSONString(requestBean);
        mPresenter.getUIData(data);
    }


    public void showFilterList(Map<String, String> selectedData) {
        RequestBean requestBean = JSON.parseObject(ConstanceValue.COMMREQUESTBEAN, RequestBean.class);
        requestBean.setMoney_id(selectedData.get("money"));
        requestBean.setAddress_id(selectedData.get("address"));
        requestBean.setTime_id(selectedData.get("time"));
        requestBean.setInterest_id(selectedData.get("interests"));
        String sign = CommonUtil.RequestSignData(requestBean);
        requestBean.setSign(sign);
        requestBean.setTimestamp(System.currentTimeMillis() / 1000);
        String data = JSON.toJSONString(requestBean);
        mPresenter.getUIData(data);
    }
}
