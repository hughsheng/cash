package com.lfp.lfp_base_recycleview_library.loadmore;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * Created by lfp on 2016/5/8.
 * 实现上拉加载更多的事件
 */
public class LoadMoreListener extends RecyclerView.OnScrollListener {

    private boolean mIsLoadingMore = false;

    private boolean mRefresh = false;    //默认不刷新

    private OnLoadMoreListener mOnLoadListener;

    private int dx, dy;

    public boolean isLoadingMore() {
        return mIsLoadingMore;
    }

    public void setLoadingMore(boolean loadingMore) {
        mIsLoadingMore = loadingMore;
    }

    public void setOnLoadListener(LoadMoreListener.OnLoadMoreListener loadListener) {
        this.mOnLoadListener = loadListener;
    }

    public boolean isRefresh() {
        return mRefresh;
    }

    public void setRefresh(boolean mRefresh) {
        this.mRefresh = mRefresh;
    }

    public void loadMoreComplete() {
        setLoadingMore(false);  //恢复初始状态
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
        super.onScrolled(recyclerView, dx, dy);

    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int lastVisibleItemPosition;
        switch (newState) {
            case RecyclerView.SCROLL_STATE_IDLE:              //处于空闲状态
                if (layoutManager instanceof StaggeredGridLayoutManager) {
                    int into[] = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];   //获取有多少列元素
                    ((StaggeredGridLayoutManager) layoutManager).findLastCompletelyVisibleItemPositions(into);
                    lastVisibleItemPosition = findMax(into);
                } else {
                    lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
                }
                //不能同时刷新数据和加载数据
                if ((dx > 0 || dy > 0) && !isRefresh() && layoutManager.getChildCount() > 0 && lastVisibleItemPosition >= layoutManager.getItemCount() - 1 && layoutManager.getItemCount() > 3) {
                    if (!isLoadingMore()) {
                        setLoadingMore(true);    //说明正在上拉刷新
                        mOnLoadListener.onLoadMore(recyclerView);
                    }
                }
                break;
        }
    }

    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public interface OnLoadMoreListener {
        void onLoadMore(RecyclerView recyclerView);
    }
}
