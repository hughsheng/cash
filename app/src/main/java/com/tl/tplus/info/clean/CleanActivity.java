package com.tl.tplus.info.clean;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tl.tplus.base.activity.BaseToolBarActivity;
import com.tl.tplus.util.CleanMessageUtil;

import butterknife.BindView;

/**
 * Created by sztangli on 2018-3-13.
 *
 */

public class CleanActivity extends BaseToolBarActivity {

  @BindView(com.tl.tplus.R.id.clean_data_size_tv)
  TextView clean_data_size_tv;
  @BindView(com.tl.tplus.R.id.clean_layout)
  RelativeLayout relativeLayout;

  @Override
  public int getLayoutResId() {
    return com.tl.tplus.R.layout.activity_clean;
  }

  @Override
  public void initFragment(Bundle savedInstanceState) {
    setTitleCenter("Pengaturan");
    try {
      clean_data_size_tv.setText(CleanMessageUtil.getTotalCacheSize(this));
      relativeLayout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
         boolean isClean= CleanMessageUtil.clearAllCache(CleanActivity.this);
         if(isClean){
           clean_data_size_tv.setText("0KB");
         }
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
