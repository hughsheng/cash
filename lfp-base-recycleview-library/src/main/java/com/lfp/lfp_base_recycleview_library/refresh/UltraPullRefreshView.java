package com.lfp.lfp_base_recycleview_library.refresh;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.lfp.lfp_base_recycleview_library.R;
import com.lfp.lfp_base_recycleview_library.loadmore.LoadMoreListener;
import com.lfp.lfp_base_recycleview_library.loadmore.RefreshListener;
import com.lfp.lfp_base_recycleview_library.refresh.headview.RefreshHeadView;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.PtrUIHandler;

/**
 * Created by linfp on 2016/4/8.
 * 自定义刷新的头部布局,一般是统一处理的
 */
@SuppressWarnings("unused")
public class UltraPullRefreshView implements LoadMoreListener.OnLoadMoreListener {

    private View view, mHeadView;
    private PtrFrameLayout frameLayout;
    private RecyclerView recyclerView;
    private RefreshListener listener;

    private LoadMoreListener onLoadListener = new LoadMoreListener();
    private Context context;
    private boolean over = false; //数据是否加载完全
    private Builder mBuilder;

    UltraPullRefreshView(Context context, Builder builder) {
        this.context = context;
        this.mBuilder = builder;
        initView();
    }

    private void initView() {
        view = LayoutInflater.from(context).inflate(R.layout.ultra_pull_refresh_recy_view, null);
        frameLayout = view.findViewById(R.id.ultra_pull);
        recyclerView = view.findViewById(R.id.all_order_ry);

        setListener(mBuilder.listener);
        initHeadView(mBuilder.headView);
        enableRefresh(mBuilder.enableRefresh, mHeadView);
        enableLoadMore(mBuilder.enableLoadMore);
        loadOver(mBuilder.loadOver);
    }

    private void initHeadView(Object headView) {
        if (headView != null) {
            if (headView instanceof View) {
                this.mHeadView = (View) headView;
            } else if (headView instanceof Integer) {
                this.mHeadView = LayoutInflater.from(context).inflate((int) headView, null);
            }
        } else {
            RefreshHeadView tempHead = new RefreshHeadView(context);
            tempHead.setPullDownRefreshText(context.getString(R.string.ultra_down_list_header_default_text));
            tempHead.setReleaseRefreshText(context.getString(R.string.ultra_down_list_header_release_text));
            tempHead.setRefreshingText(context.getString(R.string.ultra_down_list_header_refresh_text));
            tempHead.setRefreshCompleteText(context.getString(R.string.ultra_down_list_header_complete_text));
            this.mHeadView = tempHead;
        }
    }

    public View getRootView() {
        return view;
    }

    /**
     * 开发RecycleView的布局视图,供调用者设置参数
     * <p>
     * Date: 2016/4/8
     * Time: 14:21
     * FIXME
     */
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    private void setListener(RefreshListener listener) {
        this.listener = listener;
    }

    public void enableRefresh(final boolean enable, View headView) {
        if (enable) {
            frameLayout.setHeaderView(headView);
            if (headView instanceof PtrUIHandler) {
                frameLayout.addPtrUIHandler((PtrUIHandler) headView);
            } else {
                throw new RuntimeException(headView
                        + " must implement PtrUIHandler");
            }
            frameLayout.setKeepHeaderWhenRefresh(true);
            frameLayout.setPtrHandler(new PtrHandler() {
                @Override
                public void onRefreshBegin(PtrFrameLayout frame) {
                    onLoadListener.setRefresh(true);  //正在刷新
                    listener.onRefresh(getRecyclerView());
                }

                @Override
                public boolean checkCanDoRefresh(PtrFrameLayout frame,
                                                 View content, View header) {
                    //判定是否能够刷新
                    return PtrDefaultHandler.checkContentCanBePulledDown(frame,
                            content, header);
                }
            });
        }
    }

    @Override
    public void onLoadMore(RecyclerView recyclerView) {
        if (!over) {   //数据已经加载完了,不在访问服务器,减小压力
            listener.onLoadMore(recyclerView);
        }
    }

    public void enableLoadMore(boolean enableLoadMore) {
        if (enableLoadMore) {
            getRecyclerView().addOnScrollListener(onLoadListener);   //添加滚动事件判断
            onLoadListener.setOnLoadListener(this);
        }
    }

    public void enableAutoRefresh(boolean enable) {
        if (enable) {
            frameLayout.autoRefresh(true);
        }
    }

    public void endRefresh() {
        onLoadListener.setRefresh(false);
        frameLayout.refreshComplete();
    }

    public void endLoadMore() {
        onLoadListener.loadMoreComplete();
    }


    public void loadOver(boolean over) {
        this.over = over;
    }

    public static class Builder {

        private boolean enableRefresh;
        private boolean enableLoadMore;
        private boolean loadOver;
        private RefreshListener listener;
        private Context context;
        private Object headView;

        public void setHeadView(Object headView) {
            this.headView = headView;
        }

        public void setContext(Context context) {
            this.context = context;
        }

        public Builder setListener(RefreshListener listener) {
            this.listener = listener;
            return this;
        }

        public boolean isEnableRefresh() {
            return enableRefresh;
        }

        public Builder setEnableRefresh(boolean enableRefresh) {
            this.enableRefresh = enableRefresh;
            return this;
        }

        public boolean isEnableLoadMore() {
            return enableLoadMore;
        }

        public Builder setEnableLoadMore(boolean enableLoadMore) {
            this.enableLoadMore = enableLoadMore;
            return this;
        }

        public boolean isLoadOver() {
            return loadOver;
        }

        public Builder setLoadOver(boolean loadOver) {
            this.loadOver = loadOver;
            return this;
        }

        public UltraPullRefreshView builder() {
            return new UltraPullRefreshView(context, this);
        }
    }
}
