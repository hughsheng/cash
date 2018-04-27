package com.tl.tplus.detail;

import android.os.Bundle;

import com.tl.tplus.base.activity.ActivityUtils;
import com.tl.tplus.base.activity.BaseToolBarActivity;
import com.tl.tplus.detail.dagger.DetailWebModule;
import com.tl.tplus.detail.mvp.DetailPresenter;
import com.tl.tplus.instanllapp.InstallFragment;
import com.tl.tplus.instanllapp.dagger.InstallModule;
import com.tl.tplus.instanllapp.mvp.InstallPresenter;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/3/10.
 */

public class DetailsWebActivity extends BaseToolBarActivity {

    @Inject
    DetailPresenter mPresenter;

    private DetailsWebFragment mFragment;

    @Override
    public int getLayoutResId() {
        return com.tl.tplus.R.layout.activity_base;
    }

    @Override
    public void initFragment(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        setTitleCenter(bundle.getString(DetailsWebFragment.NAME));
        mFragment = DetailsWebFragment.newInstance(bundle);
        ActivityUtils.addFragmentToActivity(fragmentManager, mFragment, com.tl.tplus.R.id.container,
                DetailsWebFragment.TAG);
        getApiServiceComponent().detailWebComponent(new DetailWebModule(mFragment)).inject(this);
    }
}
