package com.tl.tplus.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.tl.tplus.R;
import com.tl.tplus.base.activity.BaseNoToolBarActivity;
import com.tl.tplus.base.activity.BaseToolBarActivity;
import com.tl.tplus.base.adapter.NetworkImageBanner;
import com.tl.tplus.base.api.bean.RequestBean;
import com.tl.tplus.dialogfragment.CashTyeDialogFragment;
import com.tl.tplus.dialogfragment.FilterDialogFragment;
import com.tl.tplus.evenbus.ErrorBus;
import com.tl.tplus.evenbus.HomeBus;
import com.tl.tplus.filter.FilterActivity;
import com.tl.tplus.filter.FilterFragment;
import com.tl.tplus.home.adapter.HomePagerFragmentadapter;
import com.tl.tplus.home.dagger.HomePresenterModule;
import com.tl.tplus.home.mvp.HomeBannerBean;
import com.tl.tplus.home.mvp.HomeCiciPresenter;
import com.tl.tplus.home.mvp.HomeDanaPresenter;
import com.tl.tplus.info.about.AboutActivity;
import com.tl.tplus.info.clean.CleanActivity;
import com.tl.tplus.instanllapp.InstallActivity;
import com.tl.tplus.strategy.StrategyActivity;
import com.tl.tplus.strategy.StrategyFragment;
import com.tl.tplus.util.ConstanceValue;
import com.tl.tplus.util.EventTrackUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import rx.functions.Action1;

import static com.tl.tplus.home.DanaComFragment.CICIBANNER;
import static com.tl.tplus.home.DanaComFragment.DANABANNER;

public class MainActivity extends BaseNoToolBarActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener,
        DanaComFragment.DanaFragmentListener, FilterDialogFragment.FilterDialogFragmentListener,
        CashTyeDialogFragment.CashTyeDialogFragmentListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.home_filter_icon)
    ImageView home_filter_icon;
    @BindView(R.id.filter_layout)
    RelativeLayout filter_layout;
    @BindView(R.id.home_filter_text)
    TextView home_filter_text;
    @BindView(R.id.home_viewpager)
    ViewPager home_viewpager;
    @BindView(R.id.bottom_tab)
    TabLayout bottom_tab;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.home_filter_nav)
    ImageView home_filter_nav;
    @Inject
    HomeDanaPresenter homeDanaPresenter;
    @Inject
    HomeCiciPresenter homeCiciPresenter;

    private DanaComFragment danaFragment;
    private CicilanComFragment cicilanFragment;
    private List<Fragment> fragmentList;
    private TabLayout.Tab tab1, tab2;
    private ImageView iv1, iv2;
    private TextView tv1,tv2;
    private CashTyeDialogFragment cashTyeDialogFragment;
    private FilterDialogFragment filterDialogFragment;
    public static final int REQUEST_READ_PHONE_STATE = 100;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initFragment(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        setDrawer();
        navigationView.setItemIconTintList(null);

        EventTrackUtil.trackEventApp(EventTrackUtil.BOSTUNAI_HOME_PAGEVIEW);
    }

    private void setDrawer() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setup_viewPager() {
        HomePagerFragmentadapter adapter = new HomePagerFragmentadapter(fragmentManager, fragmentList);
        home_viewpager.setAdapter(adapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Object object) {
        if (object instanceof HomeBus) {
            new RxPermissions(this)
                    .request(Manifest.permission.READ_PHONE_STATE)//访问手机状态权限
                    .subscribe(new Action1<Boolean>() {
                        @Override
                        public void call(Boolean aBoolean) {
                            if (aBoolean) {
                                setIMEI();
                                danaFragment = DanaComFragment.newInstance();
                                cicilanFragment = CicilanComFragment.newInstance();
                                getApiServiceComponent().homeComponent(new HomePresenterModule(danaFragment,
                                        cicilanFragment)
                                ).inject(MainActivity.this);
                                fragmentList = new ArrayList<>();
                                fragmentList.add(danaFragment);
                                fragmentList.add(cicilanFragment);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        initNavgationBar();
                                        home_filter_text.setText("RECOMEND");
                                        home_filter_text.setOnClickListener(MainActivity.this);
                                        home_filter_icon.setOnClickListener(MainActivity.this);
                                        home_filter_nav.setOnClickListener(MainActivity.this);

                                    }
                                });
                            } else {

                            }

                        }
                    });
        }
    }


    private void setIMEI() {
        TelephonyManager tm = (TelephonyManager) this.getSystemService
                (TELEPHONY_SERVICE);
        RequestBean requestBean = JSON.parseObject(ConstanceValue.COMMREQUESTBEAN, RequestBean.class);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        String imei = tm.getDeviceId() != null ? tm.getDeviceId() : "";
        requestBean.getDevice_info().setImei(imei);
        Gson gson = new Gson();
        ConstanceValue.COMMREQUESTBEAN = gson.toJson(requestBean);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == FilterActivity.RESULT) {
            if (filterDialogFragment == null) {
                filterDialogFragment = FilterDialogFragment.newInstance();
            }
            if (!filterDialogFragment.isAdded()) {
                filterDialogFragment.show(fragmentManager, FilterDialogFragment.TAG);
            }
        }

    }

    private void initNavgationBar() {
        setup_viewPager();
        tab1 = bottom_tab.newTab().setCustomView(tab_icon(R.string.bottom_tab1, R.mipmap
                .ic_dana_on));
        tab2 = bottom_tab.newTab().setCustomView(tab_icon(R.string.bottom_tab2, R.mipmap
                .ic_cician_off));
        iv1 = tab1.getCustomView().findViewById(R.id.bottom_tab_iv);
        iv2 = tab2.getCustomView().findViewById(R.id.bottom_tab_iv);
        tv1=tab1.getCustomView().findViewById(R.id.bottom_tab_tv);
        tv1.setTextColor(getResources().getColor(R.color.orange));
        tv2=tab2.getCustomView().findViewById(R.id.bottom_tab_tv);
        bottom_tab.addTab(tab1);
        bottom_tab.addTab(tab2);

        //Tablayout自定义view绑定ViewPager
        home_viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(bottom_tab));
        bottom_tab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener
                (home_viewpager));
        bottom_tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab == tab1) {
                    iv1.setImageResource(R.mipmap.ic_dana_on);
                    tv1.setTextColor(getResources().getColor(R.color.orange));
                    iv2.setImageResource(R.mipmap.ic_cician_off);
                    tv2.setTextColor(getResources().getColor(R.color.black));
//                    filter_layout.setVisibility(View.VISIBLE);
                    if (danaFragment != null) {
                        danaFragment.showBannerByType(DANABANNER);
                    }
                    EventTrackUtil.trackEventApp(EventTrackUtil.BOSTUNAI_HOME_TAB_CREDIT_CLICK);
                } else {
                    iv2.setImageResource(R.mipmap.ic_cician_on);
                    tv2.setTextColor(getResources().getColor(R.color.orange));
                    iv1.setImageResource(R.mipmap.ic_dana_off);
                    tv1.setTextColor(getResources().getColor(R.color.black));
//                    filter_layout.setVisibility(View.GONE);
                    danaFragment.showBannerByType(CICIBANNER);
                    EventTrackUtil.trackEventApp(EventTrackUtil.BOSTUNAI_HOME_TAB_CICILAN_CLICK);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private View tab_icon(int strID, int iconID) {
        View newtab = LayoutInflater.from(this).inflate(R.layout.bottom_tab_item_layout, null);
        ImageView iv = newtab.findViewById(R.id.bottom_tab_iv);
        TextView tv = (TextView) newtab.findViewById(R.id.bottom_tab_tv);
        tv.setText(strID);
        tv.setTextSize(10);
        iv.setImageResource(iconID);
        return newtab;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_pinjam) {
            startActivity(new Intent(this, InstallActivity.class));
        } else if (id == R.id.nav_pengaturan) {
            EventTrackUtil.trackEventApp(EventTrackUtil.BOSTUNAI_PROFILE_SET_CLICK);
            startActivity(new Intent(this, CleanActivity.class));
        } else if (id == R.id.nav_tentang) {
            EventTrackUtil.trackEventApp(EventTrackUtil.BOSTUNAI_PROFILE_ABOUTUS_CLICK);
            startActivity(new Intent(this, AboutActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.END);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_filter_icon:
                if (filterDialogFragment == null) {
                    filterDialogFragment = FilterDialogFragment.newInstance();
                }
                if (!filterDialogFragment.isAdded()) {
                    filterDialogFragment.show(fragmentManager, FilterDialogFragment.TAG);
                }
                break;
            case R.id.home_filter_text:

                if (cashTyeDialogFragment == null) {
                    cashTyeDialogFragment = CashTyeDialogFragment.newInstance();
                }
                if (!cashTyeDialogFragment.isAdded()) {
                    cashTyeDialogFragment.show(fragmentManager, CashTyeDialogFragment.TAG);
                }
                break;

            case R.id.home_filter_nav:
                if (drawer.isDrawerOpen(navigationView)) {
                    drawer.closeDrawer(navigationView);
                } else {
                    drawer.openDrawer(navigationView);
                }
                break;
        }
    }

    @Override
    public void setUpBanner(final List<HomeBannerBean.DataBean.BannerBean> bannerBeanList, final
    int type) {
        if(type == 1) {
            danaFragment.showBannerImgs(bannerBeanList);
        }else if(type == 2){
            cicilanFragment.showBannerImgs(bannerBeanList);
        }

    }

    @Override
    public void showFilterList(Map<String, String> selectedData) {
        Intent intent = new Intent(this, FilterActivity.class);
        intent.putExtra(FilterFragment.MONEYID, selectedData.get("money"));
        intent.putExtra(FilterFragment.ADDRESSID, selectedData.get("address"));
        intent.putExtra(FilterFragment.TIMEID, selectedData.get("time"));
        intent.putExtra(FilterFragment.INTERESTID, selectedData.get("interests"));
        startActivity(intent);
    }

    @Override
    public void changeCashType(String fw_id, String fw_type, String name) {
        Intent intent = new Intent(this, StrategyActivity.class);
        intent.putExtra(StrategyFragment.FWID, fw_id);
        intent.putExtra(StrategyFragment.FWTYPY,  "1");
        intent.putExtra(StrategyFragment.NAME, name);
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (danaFragment != null) {
            danaFragment.rePortInstallApp();
        }
    }
}
