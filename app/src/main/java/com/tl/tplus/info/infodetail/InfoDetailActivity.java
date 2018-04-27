package com.tl.tplus.info.infodetail;

import android.os.Bundle;

import com.tl.tplus.R;
import com.tl.tplus.base.activity.BaseToolBarActivity;

/**
 * Created by sztangli on 2018-3-13.
 *
 */

public class InfoDetailActivity extends BaseToolBarActivity {
  @Override
  public int getLayoutResId() {
    return R.layout.activity_info_detail;
  }

  @Override
  public void initFragment(Bundle savedInstanceState) {
     setTitleCenter("Hubungi Kami");
  }
}
