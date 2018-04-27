package com.tl.tplus.base.fragment;

import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.tl.tplus.home.adapter.TipBean;
import com.lfp.lfp_base_recycleview_library.BaseRecyclerViewAdapter;
import com.lfp.lfp_base_recycleview_library.loadmore.RefreshListener;
import com.lfp.lfp_base_recycleview_library.refresh.UltraPullRefreshView;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by fangpenglin on 16/6/10.
 * 上拉刷新和下拉刷新数据的管理fragment
 */
public abstract class BaseRefreshComFragment<T> extends BaseComFragment implements RefreshListener {

    @BindView(com.tl.tplus.R.id.load_progress)
    protected ContentLoadingProgressBar load_progress;

    protected View rootView;
    protected Unbinder mUnbinder;
    protected UltraPullRefreshView ultraPullRefreshView;
    protected List<T> dataList;
    /**
     * 0表示下拉刷新, 1表示上拉加载
     */
    protected int type = 0;
    protected View footerView;
    private View mTipView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (rootView == null) {
            initView();
            initRecyclerView();
        }
        mUnbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    /**
     * 对外提供可编辑recyclerView的编辑属性
     * 实现该类,可以覆盖该方法,子类继承,
     * 必须复写该方法
     */
    public void initRecyclerView() {
        rootView = ultraPullRefreshView.getRootView();
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mUnbinder.unbind();
        mUnbinder = null;

    }

    private void initView() {
        UltraPullRefreshView.Builder builder = new UltraPullRefreshView.Builder();
        builder.setEnableRefresh(enableRefresh());
        builder.setEnableLoadMore(enableLoadMore());
        builder.setContext(getContext());
        builder.setListener(this);

        ultraPullRefreshView = builder.builder();
    }

    /**
     * 预留刷新接口,需要设置状态时,可重写父类方法
     * Date: 2016/5/9
     * Time: 13:50
     */

    public boolean enableRefresh() {
        return true;
    }

    public boolean enableLoadMore() {
        return true;
    }

    @Override
    public void onRefresh(RecyclerView recyclerView) {
        onRefreshView();
    }

    @Override
    public void onLoadMore(RecyclerView recyclerView) {
        final BaseRecyclerViewAdapter adapter = (BaseRecyclerViewAdapter) recyclerView.getAdapter();
        adapter.setLoadFooter(View.VISIBLE);
        if (adapter.isHeadViews()) {
            ultraPullRefreshView.getRecyclerView().smoothScrollToPosition(adapter.getItemCount() + 1);
        } else {
            ultraPullRefreshView.getRecyclerView().smoothScrollToPosition(adapter.getItemCount());
        }
        onLoadView(dataList.size() - 1);  //在list中的哪个位置插入数据
    }


    /**
     * 上拉提示数据
     *
     * @return (底部数据提示语)
     */
    public View addFootView() {
        if (footerView == null) {
            footerView = LayoutInflater.from(getContext()).inflate(com.tl.tplus.R.layout.footer_re_view, (ViewGroup) ultraPullRefreshView.getRecyclerView().getParent(), false);
        }
        return footerView;
    }

    /**
     * 无数据和无网络以及无登录提示状态
     *
     * @return 提示布局
     */
    public View addTipView() {
        if (mTipView == null) {
            mTipView = LayoutInflater.from(getContext()).inflate(com.tl.tplus.R.layout.tip_view_group, (ViewGroup) ultraPullRefreshView.getRecyclerView().getParent(), false);
        }
        return mTipView;
    }

    /**
     * 数据请求失败调用
     *
     * @param content    (提示的内容)
     * @param btnTxt     (按钮提示,传空字符串表示不显示按钮)
     * @param dataStatus (NO_NET(无网络状态), NO_LOGIN(无登录状态), NO_DATA(无数据状态))
     */
    public void setTipInfo(String content, String btnTxt, TipBean.DataStatus dataStatus) {
        TipBean mTipBean = new TipBean();

        mTipBean.setContent(content);   //getString(R.string.no_order)
        mTipBean.setBtnTxt(btnTxt);     //getString(R.string.click_me_str)
        mTipBean.setDataStatus(dataStatus);
        onTipContent(mTipBean);
    }

    public void onEnd() {
        load_progress.hide();
        ultraPullRefreshView.endRefresh();
        ultraPullRefreshView.endLoadMore();
    }


    public abstract void onRefreshView();

    public abstract void onLoadView(int position);

    public abstract void onTipContent(TipBean tipBean);

}
