package com.lfp.lfp_base_recycleview_library.refresh.headview;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lfp.lfp_base_recycleview_library.R;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Created by linfp on 2016/4/8.
 * 刷新动画的头部(自定义)
 */
public class RefreshHeadView extends FrameLayout implements PtrUIHandler {

  private final static String TAG = RefreshHeadView.class.getSimpleName();

  private String mPullDownRefreshText = "下拉刷新";
  private String mReleaseRefreshText = "释放更新";
  private String mRefreshingText = "加载中...";
  private String mRefreshCompleteText = "刷新完成";

  private TextView tvHeadTitle;    //头部下拉后,显示的文字
  private ImageView mHeadImage;    //下拉的动画显示

  public RefreshHeadView(Context context) {
    //super(context);
    this(context, null);
  }

  public RefreshHeadView(Context context, AttributeSet attrs) {
    //super(context, attrs);
    this(context, attrs, 0);
  }

  public RefreshHeadView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    initView(context);
  }

  private void initView(Context context) {
    ViewGroup headView = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.refresh_header_normal, this, true);
    tvHeadTitle = headView.findViewById(R.id.tv_normal_refresh_header_status);
    mHeadImage = headView.findViewById(R.id.iv_normal_refresh_header);
  }

  @Override
  public void onUIReset(PtrFrameLayout frame) {
    mHeadImage.setVisibility(View.INVISIBLE);
    tvHeadTitle.setText(mPullDownRefreshText);
    setHeaderFlipState(false);
  }

  @Override
  public void onUIRefreshPrepare(PtrFrameLayout frame) {
    mHeadImage.setVisibility(View.VISIBLE);
    tvHeadTitle.setText(mPullDownRefreshText);
    setHeaderFlipState(false);
  }

  @Override
  public void onUIRefreshBegin(PtrFrameLayout frame) {
    mHeadImage.setVisibility(View.VISIBLE);
    tvHeadTitle.setText(mRefreshingText);
    setHeaderFlipState(true);
  }

  @Override
  public void onUIRefreshComplete(PtrFrameLayout frame) {
    mHeadImage.setVisibility(View.INVISIBLE);
    tvHeadTitle.setText(mRefreshCompleteText);
    setHeaderFlipState(false);
  }

  @Override
  public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
    final int mOffsetToRefresh = frame.getOffsetToRefresh();
    final int currentPos = ptrIndicator.getCurrentPosY();
    final int lastPos = ptrIndicator.getLastPosY();

    if (currentPos < mOffsetToRefresh && mOffsetToRefresh <= lastPos) {
      if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
        mHeadImage.setVisibility(View.VISIBLE);
        tvHeadTitle.setText(mPullDownRefreshText);
        setHeaderFlipState(false);
      }
    } else if (mOffsetToRefresh < currentPos && lastPos <= mOffsetToRefresh) {
      if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
        mHeadImage.setVisibility(View.VISIBLE);
        tvHeadTitle.setText(mReleaseRefreshText);
        setHeaderFlipState(true);
      }
    }
  }

  /**
   * 设置未满足刷新条件，提示继续往下拉的文本
   *
   * @param pullDownRefreshText
   */
  public void setPullDownRefreshText(String pullDownRefreshText) {
    this.mPullDownRefreshText = pullDownRefreshText;
  }

  /**
   * 设置满足刷新条件时的文本
   *
   * @param releaseRefreshText
   */
  public void setReleaseRefreshText(String releaseRefreshText) {
    this.mReleaseRefreshText = releaseRefreshText;
  }

  /**
   * 设置正在刷新时的文本
   *
   * @param refreshingText
   */
  public void setRefreshingText(String refreshingText) {
    this.mRefreshingText = refreshingText;
  }

  /**
   * 设置刷新完成的文内容
   *
   * @param refreshCompleteText
   */
  public void setRefreshCompleteText(String refreshCompleteText) {
    this.mRefreshCompleteText = refreshCompleteText;
  }

  private void setHeaderFlipState(boolean animation) {
    if (animation) {
      mHeadImage.setImageResource(R.drawable.refresh_head_anim);
      AnimationDrawable animationDrawable = (AnimationDrawable) mHeadImage.getDrawable();
      animationDrawable.start();
    } else {
      mHeadImage.setImageResource(R.mipmap.loading);
    }
  }
}
