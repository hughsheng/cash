package com.tl.tplus.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.lfp.lfp_base_recycleview_library.BaseEvent;
import com.tl.tplus.R;
import com.tl.tplus.base.adapter.NetworkImageBanner;
import com.tl.tplus.base.api.bean.ErrorBean;
import com.tl.tplus.base.api.bean.RequestBean;
import com.tl.tplus.base.fragment.BaseRefreshComFragment;
import com.tl.tplus.cicidetail.CicidetailActivity;
import com.tl.tplus.cicidetail.CicidetailComFragment;
import com.tl.tplus.detail.DetailActivity;
import com.tl.tplus.detail.DetailComFragment;
import com.tl.tplus.home.adapter.CiciRecyclViewAdapter;
import com.tl.tplus.home.adapter.TipBean;
import com.tl.tplus.home.mvp.HomeBannerBean;
import com.tl.tplus.home.mvp.HomeCiciContract;
import com.tl.tplus.home.mvp.HomeCiciListBean;
import com.tl.tplus.util.CommonUtil;
import com.tl.tplus.util.ConstanceValue;
import com.tl.tplus.util.EventTrackUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sztangli on 2018-3-2.
 */

public class CicilanComFragment extends BaseRefreshComFragment<HomeCiciListBean.DataBean>
    implements HomeCiciContract.IView {

  public static final String TAG = "CicilanComFragment";
  private HomeCiciContract.Presenter mPresenter;
  private RecyclerView recyclerView;
  private CiciRecyclViewAdapter adapter;
  private int page = 0;
  private int pageSize = 10;
  private final int REFRESH = 0;
  private final int LOADMORE = 1;
  private View headerView;
  private ConvenientBanner convenientBanner;


  public static CicilanComFragment newInstance() {

    Bundle args = new Bundle();

    CicilanComFragment fragment = new CicilanComFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void showList(HomeCiciListBean homeCiciListBean) {
    if (homeCiciListBean.getLoadType() == REFRESH) {
      dataList.clear();
      dataList.addAll(homeCiciListBean.getData());
    } else {
      dataList.addAll(homeCiciListBean.getData());
    }
    adapter.setDataList(dataList);
    adapter.notifyDatas();

  }

  @Override
  public void hideLoading() {
    load_progress.hide();
    onEnd();
  }

  @Override
  public void showLoading() {
    load_progress.show();
  }

  @Override
  public void setPresenter(@NonNull HomeCiciContract.Presenter presenter) {
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


  @Override
  public void onLogicPresenter() {
    EventBus.getDefault().register(this);
    getCiciDataList("" + page, "" + pageSize, REFRESH);
    initalRecycleView();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    EventBus.getDefault().unregister(this);
  }

  private void initalRecycleView() {
    adapter = new CiciRecyclViewAdapter(getContext(), dataList, R.layout
        .item_home_layout);

    headerView = LayoutInflater.from(getContext()).inflate(R.layout.layout_header, null, false);
    convenientBanner = headerView.findViewById(R.id.main_banner);

    recyclerView = ultraPullRefreshView.getRecyclerView();

    adapter.addSingleHeadView(headerView);

    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    //recyclerView.addItemDecoration(new ItemDecoration(getContext(), LinearLayout.VERTICAL));
    recyclerView.setAdapter(adapter);
    adapter.setOnItemClickListener(new BaseEvent.OnItemClickListener() {
      @Override
      public void onItemClick(View view, Object data, int position) {
        HomeCiciListBean.DataBean item = (HomeCiciListBean.DataBean) data;
        Intent intent = new Intent(getActivity(), CicidetailActivity.class);
        if (item.isInstall()) {
          intent.putExtra(DetailComFragment.TYPE, CicidetailComFragment.DOWN);
        } else {
          intent.putExtra(DetailComFragment.TYPE, 0);
        }
        intent.putExtra(CicidetailComFragment.PID, item.getPid());
        startActivity(intent);
      }
    });
  }

  private void getCiciDataList(String start, String num, int loadType) {
    RequestBean requestBean = JSON.parseObject(ConstanceValue.COMMREQUESTBEAN, RequestBean.class);
    requestBean.setStart(start);
    requestBean.setLimit(num);
    String sign = CommonUtil.RequestSignData(requestBean);
    requestBean.setSign(sign);
    requestBean.setTimestamp(System.currentTimeMillis() / 1000);
    String data = JSON.toJSONString(requestBean);
    mPresenter.getCiciDataList(data, loadType);
  }


  public void showCiciDetail(String pid) {
    Intent intent = new Intent(getActivity(), CicidetailActivity.class);
    intent.putExtra(CicidetailComFragment.PID, pid);
    startActivity(intent);
  }

  @Override
  public void onRefreshView() {
    getCiciDataList("0", "" + pageSize, REFRESH);
  }

  @Override
  public void onLoadView(int position) {
    page = page + 10;
    getCiciDataList("" + page, "" + pageSize, LOADMORE);
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  public void onEvent(Object object) {
    getCiciDataList("" + 0, "" + pageSize, REFRESH);
  }

  @Override
  public void onTipContent(TipBean tipBean) {
    adapter.setEmptyView(addTipView());
    adapter.setTipContent(tipBean);
    adapter.notifyDatas();
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
                showCiciDetail(pid);
                EventTrackUtil.trackEventApp(EventTrackUtil.BOSTUNAI_HOME_BANNER_CLICK);
              }
            });

  }

}
