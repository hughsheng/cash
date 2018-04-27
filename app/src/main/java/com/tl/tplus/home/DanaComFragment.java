package com.tl.tplus.home;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.lfp.lfp_base_recycleview_library.BaseEvent;
import com.tl.tplus.R;
import com.tl.tplus.base.adapter.NetworkImageBanner;
import com.tl.tplus.base.api.bean.ErrorBean;
import com.tl.tplus.base.api.bean.RequestBean;
import com.tl.tplus.base.fragment.BaseRefreshComFragment;
import com.tl.tplus.detail.DetailActivity;
import com.tl.tplus.detail.DetailComFragment;
import com.tl.tplus.evenbus.CiciBusBean;
import com.tl.tplus.evenbus.DanaBusBean;
import com.tl.tplus.home.adapter.DanaRecyclViewAdapter;
import com.tl.tplus.home.adapter.TipBean;
import com.tl.tplus.home.mvp.FilterListBean;
import com.tl.tplus.home.mvp.GuidBean;
import com.tl.tplus.home.mvp.HomeBannerBean;
import com.tl.tplus.home.mvp.HomeDanaContract;
import com.tl.tplus.home.mvp.HomeListBean;
import com.tl.tplus.home.mvp.UpdateBean;
import com.tl.tplus.info.about.AboutActivity;
import com.tl.tplus.util.CommonUtil;
import com.tl.tplus.util.ConstanceValue;
import com.tl.tplus.util.EventTrackUtil;
import com.tl.tplus.widges.AppUpgradeDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Created by sztangli on 2018-2-27.
 * DANA CEPAT fragment
 */

public class DanaComFragment extends BaseRefreshComFragment<HomeListBean.DataBean> implements
        HomeDanaContract.IView {

    public static final String TAG = "DanaComFragment";
    private HomeDanaContract.Presenter mPresenter;
    private RecyclerView recyclerView;
    private DanaFragmentListener mListener;
    private DanaRecyclViewAdapter adapter;
    private List<HomeBannerBean.DataBean.BannerBean> danaBannerBean = new ArrayList<>();
    private List<HomeBannerBean.DataBean.BannerBean> ciciBannerBean = new ArrayList<>();
    public static final String DANABANNER = "1";
    public static final String CICIBANNER = "2";
    private final int REFRESH = 0;
    private final int LOADMORE = 1;
    protected int loadState;
    private int page = 0;
    private int pageSize = 10;
    private View headerView;

    private ConvenientBanner convenientBanner;

    public static DanaComFragment newInstance() {

        Bundle args = new Bundle();

        DanaComFragment fragment = new DanaComFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (DanaFragmentListener) context;
    }

    @Override
    public void onLogicPresenter() {
        EventBus.getDefault().register(this);
        getGuid();
        getNewVersion();
        initalRecycleView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 获取首页banner图片
     */
    private void getBannerData(int type) {
        RequestBean requestBean = JSON.parseObject(ConstanceValue.COMMREQUESTBEAN, RequestBean.class);
        requestBean.setBanner_type(type);
        String sign = CommonUtil.RequestSignData(requestBean);
        requestBean.setSign(sign);
        requestBean.setTimestamp(System.currentTimeMillis() / 1000);
        String data = JSON.toJSONString(requestBean);
        mPresenter.getBannerData(data, type);
    }

    /**
     * 获取首页列表数据
     */
    private void getDataList(String start, String num) {
        RequestBean requestBean = JSON.parseObject(ConstanceValue.COMMREQUESTBEAN, RequestBean.class);
        requestBean.setStart(start);
        requestBean.setLimit(num);
        String sign = CommonUtil.RequestSignData(requestBean);
        requestBean.setSign(sign);
        requestBean.setTimestamp(System.currentTimeMillis() / 1000);
        String data = JSON.toJSONString(requestBean);
        mPresenter.getDanaDataList(data);
    }


    private void getGuid() {
        RequestBean requestBean = JSON.parseObject(ConstanceValue.COMMREQUESTBEAN, RequestBean.class);
        String sign = CommonUtil.RequestSignData(requestBean);
        requestBean.setSign(sign);
        requestBean.setTimestamp(System.currentTimeMillis() / 1000);
        String data = JSON.toJSONString(requestBean);
        mPresenter.getGuid(data);
    }

    /**
     * 版本更新
     */
    private void getNewVersion() {
        RequestBean requestBean = JSON.parseObject(ConstanceValue.COMMREQUESTBEAN, RequestBean.class);
        String sign = CommonUtil.RequestSignData(requestBean);
        requestBean.setSign(sign);
        requestBean.setTimestamp(System.currentTimeMillis() / 1000);
        String data = JSON.toJSONString(requestBean);
        mPresenter.getNewVersion(data);
    }


    public void rePortInstallApp() {
        //  boolean isFirst = (boolean) cashApplication.getCacheData(ConstanceValue.FIRSTINSTALL, true);
        Subscription subscription = Observable.just("")
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .map(new Func1<String, List<String>>() {
                    @Override
                    public List<String> call(String s) {
                        ConstanceValue.INSTALLLIST = CommonUtil.scanLocalInstallAppList(getActivity()
                                .getPackageManager());
                        return ConstanceValue.INSTALLLIST;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<String>>() {
                    @Override
                    public void call(List<String> strings) {
                        RequestBean requestBean = JSON.parseObject(ConstanceValue.COMMREQUESTBEAN,
                                RequestBean.class);
                        requestBean.setPackage_name(strings);
                        requestBean.setGuid(ConstanceValue.GUID);
                        String sign = CommonUtil.RequestSignData(requestBean);
                        requestBean.setSign(sign);
                        requestBean.setTimestamp(System.currentTimeMillis() / 1000);
                        String data = JSON.toJSONString(requestBean);
                        mPresenter.rePortInstallApp(data);
                    }
                });

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Object object) {
        if (object instanceof DanaBusBean) {
            rePortInstallApp();
        }
    }


    @Override
    public void showBanner(HomeBannerBean homeBannerBean) {
        int type = homeBannerBean.getData().getBannerType();
        if (type == 1) {
            ConstanceValue.TYPEBEAN = homeBannerBean.getData().getFilter_words();
            danaBannerBean = homeBannerBean.getData().getBanner();
            if (danaBannerBean.size() > 0) {
                mListener.setUpBanner(danaBannerBean, 1);
            }
        } else if (type == 2) {
            ciciBannerBean = homeBannerBean.getData().getBanner();
        }

    }


    public void showBannerByType(String type) {
        if (type.equals("1")) {
            mListener.setUpBanner(danaBannerBean, 1);
        } else if (type.equals("2")) {
            mListener.setUpBanner(ciciBannerBean, 2);
        }
    }

    @Override
    public void showList(HomeListBean homeListBean) {
        if (loadState == REFRESH) {
            dataList.clear();
            dataList.addAll(homeListBean.getData());

        } else {
            dataList.addAll(homeListBean.getData());
        }
        adapter.setDataList(dataList);
        adapter.notifyDatas();
    }

    @Override
    public void saveFilterData(FilterListBean filterListBean) {
        ConstanceValue.FILTERLISTBEAN = filterListBean;

    }

    @Override
    public void saveGuid(GuidBean guidBean) {
        RequestBean requestBean = JSON.parseObject(ConstanceValue.COMMREQUESTBEAN, RequestBean.class);
        requestBean.setGuid(guidBean.getData().getGuid());
        ConstanceValue.GUID = guidBean.getData().getGuid();
        ConstanceValue.COMMREQUESTBEAN = new Gson().toJson(requestBean);
        getBannerData(1);
        getBannerData(2);
        getFilterDada();
        rePortInstallApp();
    }

    @Override
    public void saveInstallState(GuidBean guidBean) {
        getDataList("" + page, "" + pageSize);
        EventBus.getDefault().post(new CiciBusBean());
    }


    private void initalRecycleView() {
        adapter = new DanaRecyclViewAdapter(getActivity(), dataList, R.layout.item_home_layout);
        recyclerView = ultraPullRefreshView.getRecyclerView();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // recyclerView.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayout
        // .HORIZONTAL));

        headerView = LayoutInflater.from(getContext()).inflate(R.layout.layout_header, null, false);
        convenientBanner = headerView.findViewById(R.id.main_banner);

        adapter.addSingleHeadView(headerView);

        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseEvent.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {
                HomeListBean.DataBean item = (HomeListBean.DataBean) data;
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(DetailComFragment.PID, item.getPid());
                if (item.isInstalled()) {
                    intent.putExtra(DetailComFragment.TYPE, DetailComFragment.DOWN);
                } else {
                    intent.putExtra(DetailComFragment.TYPE, 0);
                }
                startActivity(intent);

                EventTrackUtil.trackEventApp(EventTrackUtil.BOSTUNAI_HOME_LIST_CLICK);
            }
        });
    }

    @Override
    public void hideLoading() {
        load_progress.hide();
        onEnd();
    }

    public void showDanaDetail(String pid) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(DetailComFragment.PID, pid);
        startActivity(intent);
    }


    @Override
    public void showLoading() {
        load_progress.show();
    }

    @Override
    public void setPresenter(@NonNull HomeDanaContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onLoadFail(ErrorBean errorBean) {
//    dataList.clear();
//    adapter.clear();
//    adapter.setHasData(false);
//    ultraPullRefreshView.loadOver(true);
//    setTipInfo(errorBean.getErrMessage(), "", TipBean.DataStatus.NO_DATA);
    }

    //可上拉
    @Override
    public boolean enableLoadMore() {
        return true;
    }

    //可下拉
    @Override
    public boolean enableRefresh() {
        return true;
    }

    @Override
    public void onRefreshView() {
        loadState = REFRESH;
        page = 0;
        getDataList("" + page, "" + pageSize);
    }

    @Override
    public void onLoadView(int position) {
        page = page + 10;
        loadState = LOADMORE;
        getDataList("" + (page), "" + pageSize);
    }

    public void getFilterDada() {
        RequestBean requestBean = JSON.parseObject(ConstanceValue.COMMREQUESTBEAN, RequestBean.class);
        String sign = CommonUtil.RequestSignData(requestBean);
        requestBean.setSign(sign);
        requestBean.setTimestamp(System.currentTimeMillis() / 1000);
        String data = JSON.toJSONString(requestBean);
        mPresenter.getFilterDataList(data);
    }

    @Override
    public void onTipContent(TipBean tipBean) {
        adapter.setEmptyView(addTipView());
        adapter.setTipContent(tipBean);
        adapter.notifyDatas();
    }

    @Override
    public void showNewVersion(UpdateBean updateBean) {
        int type = updateBean.getData().getF_type();
        if (type == 0) {
            return;
        }
        AppUpgradeDialog upgradeDialog = new AppUpgradeDialog(getActivity(), updateBean);
        upgradeDialog.builder();
        upgradeDialog.show();
    }

    interface DanaFragmentListener {
        void setUpBanner(List<HomeBannerBean.DataBean.BannerBean> bannerBeanList, int type);
    }


    public void showBannerImgs(final List<HomeBannerBean.DataBean.BannerBean> bannerBeanList) {
        List<String> urlList = new ArrayList<>();
        for (HomeBannerBean.DataBean.BannerBean bannerBean : bannerBeanList) {
            urlList.add(bannerBean.getImg());
        }

        if (urlList.size() == 0) {
            convenientBanner.setVisibility(View.GONE);
        } else {
            convenientBanner.setVisibility(View.VISIBLE);
        }
        convenientBanner.setCanLoop(true);
        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageBanner>() {
            @Override
            public NetworkImageBanner createHolder() {
                return new NetworkImageBanner(getContext());
            }
        }, urlList)
                .startTurning(5000)
                .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        String pid = bannerBeanList.get(position).getPid();
                        showDanaDetail(pid);
                        EventTrackUtil.trackEventApp(EventTrackUtil.BOSTUNAI_HOME_BANNER_CLICK);
                    }
                });

    }

}
