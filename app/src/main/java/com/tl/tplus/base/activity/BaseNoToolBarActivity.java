package com.tl.tplus.base.activity;

import android.os.Bundle;

/**
 * Created by fangpenglin on 16/6/7.
 * 管理没有toolbar的基础类
 */
public abstract class BaseNoToolBarActivity extends BaseComActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragment(savedInstanceState);
    }
}
