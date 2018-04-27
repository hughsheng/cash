package com.tl.tplus.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tl.tplus.base.activity.ActivityUtils;
import com.tl.tplus.base.activity.BaseToolBarActivity;
import com.tl.tplus.cicidetail.CicidetailActivity;
import com.tl.tplus.detail.dagger.DetailModule;
import com.tl.tplus.detail.mvp.DetailPresenter;
import com.tl.tplus.util.EventTrackUtil;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/3/2.
 *
 */

public class DetailActivity extends BaseToolBarActivity implements View.OnClickListener,
    DetailComFragment.DetailListener {

    public static final int GUAIDITEM1 = 1;
    public static final int GUAIDITEMD1 = 11;
    public static final int GUAIDITEM2 = 2;
    public static final int GUAIDITEMD2 = 22;
    public static final int GUAIDITEM3 = 3;
    public static final int GUAIDITEMD3 = 33;


    @Inject
    DetailPresenter mPresenter;

    private DetailComFragment mFragment;

    @Override
    public int getLayoutResId() {
        return com.tl.tplus.R.layout.activity_base;
    }

    @Override
    public void initFragment(Bundle savedInstanceState) {
        setToolBarGuaid();
        Intent intent=getIntent();
        String pid=intent.getStringExtra(DetailComFragment.PID);
        int type=intent.getIntExtra(DetailComFragment.TYPE,0);
        mFragment = DetailComFragment.newInstance(Integer.valueOf(pid),type);
        ActivityUtils.addFragmentToActivity(fragmentManager, mFragment, com.tl.tplus.R.id.container, DetailComFragment.TAG);
        getApiServiceComponent().detailComponent(new DetailModule(mFragment)).inject(this);

        EventTrackUtil.trackEventApp(EventTrackUtil.BOSTUNAI_DETAILS_OPEN_CASHCREDIT);
        EventTrackUtil.trackEventApp(EventTrackUtil.BOSTUNAI_DETAILS_PAGEVIEW);
    }

    /**
     * 设置头部导航栏
     */
    private void setToolBarGuaid() {
        title_layout.setVisibility(View.GONE);
        pj_guaid_layout.setVisibility(View.VISIBLE);
        initTitleSelectedState();
        pj_guaid1.setOnClickListener(this);
        pj_guaid2.setOnClickListener(this);
        pj_guaid3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case com.tl.tplus.R.id.pj_guaid1:
                setGuaidSelected(pj_guaid1, pj_guaid_director1);
                mFragment.scrollToGuaid(GUAIDITEM1);
                break;

            case com.tl.tplus.R.id.pj_guaid2:
                setGuaidSelected(pj_guaid2, pj_guaid_director2);
                mFragment.scrollToGuaid(GUAIDITEM2);
                break;

            case com.tl.tplus.R.id.pj_guaid3:
                setGuaidSelected(pj_guaid3, pj_guaid_director3);
                mFragment.scrollToGuaid(GUAIDITEM3);
                break;
        }
    }


    /**
     * 初始化title选中状态
     */
    private void initTitleSelectedState() {
        pj_guaid1.setTag(true);//初始第一个选中
        pj_guaid2.setTag(false);
        pj_guaid3.setTag(false);
    }

    /**
     * 设置选中title
     *
     * @param guaid
     * @param director
     */
    private void setGuaidSelected(TextView guaid, TextView director) {
        guaidUnselect(pj_guaid1, pj_guaid_director1);
        guaidUnselect(pj_guaid2, pj_guaid_director2);
        guaidUnselect(pj_guaid3, pj_guaid_director3);
        guaidSelect(guaid, director);
        guaid.setTag(true);
    }

    /**
     * 单个title选中
     *
     * @param guaid
     * @param director
     */
    private void guaidSelect(TextView guaid, TextView director) {
        director.setVisibility(View.VISIBLE);
    }

    /**
     * 单个title取消
     *
     * @param guaid
     * @param director
     */
    private void guaidUnselect(TextView guaid, TextView director) {
        director.setVisibility(View.GONE);
        guaid.setTag(false);
    }


    @Override
    public boolean getSelecteState(int item) {
        if (item == GUAIDITEM1) {
            return (boolean) pj_guaid1.getTag();
        } else if (item == GUAIDITEM2) {
            return (boolean) pj_guaid2.getTag();
        } else if (item == GUAIDITEM3) {
            return (boolean) pj_guaid3.getTag();
        }
        return false;
    }

    @Override
    public void setGuaidSelected(int item, int itemDerector) {
        if (item == GUAIDITEM1) {
            setGuaidSelected(pj_guaid1, pj_guaid_director1);
        } else if (item == GUAIDITEM2) {
            setGuaidSelected(pj_guaid2, pj_guaid_director2);
        } else if (item == GUAIDITEM3) {
            setGuaidSelected(pj_guaid3, pj_guaid_director3);
        }
    }

}
