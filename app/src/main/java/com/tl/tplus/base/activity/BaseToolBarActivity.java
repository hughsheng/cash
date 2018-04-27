package com.tl.tplus.base.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tl.tplus.R;
import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

/**
 * Created by fangpenglin on 16/6/7.
 * 管理toolbar的基础类
 */
public abstract class BaseToolBarActivity extends BaseComActivity {

  @BindView(R.id.toolbar)
  protected Toolbar toolbar;
  @BindView(R.id.title_center)
  protected TextView titleCenter;
  @BindView(R.id.tv_right)
  protected TextView titleRight;
  @BindView(R.id.image_right)
  protected ImageView imageRight;
  @BindView(R.id.pj_guaid_layout)
  protected LinearLayout pj_guaid_layout;
  @BindView(R.id.title_layout)
  protected LinearLayout title_layout;
  @BindView(R.id.pj_guaid1)
  protected TextView pj_guaid1;
  @BindView(R.id.pj_guaid2)
  protected TextView pj_guaid2;
  @BindView(R.id.pj_guaid3)
  protected TextView pj_guaid3;
  @BindView(R.id.pj_guaid_director1)
  protected TextView pj_guaid_director1;
  @BindView(R.id.pj_guaid_director2)
  protected TextView pj_guaid_director2;
  @BindView(R.id.pj_guaid_director3)
  protected TextView pj_guaid_director3;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initToolbar();
    initFragment(savedInstanceState);
  }

  protected void initToolbar() {
    toolbar.setNavigationIcon(R.mipmap.back);
    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    EventBus.getDefault().unregister(this);
  }

  protected void dismiss() {

  }

  protected void setTitleCenter(int resId) {
    titleCenter.setText(resId);
  }

  protected void setTitleCenter(String title) {
    titleCenter.setText(title);
  }

  protected void setTitleRight(int resId) {
    titleRight.setText(resId);
  }

  protected void setTitleRight(String title) {
    titleRight.setText(title);
  }

}
