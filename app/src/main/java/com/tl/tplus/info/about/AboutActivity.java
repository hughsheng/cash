package com.tl.tplus.info.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.tl.tplus.R;
import com.tl.tplus.base.activity.BaseToolBarActivity;
import com.tl.tplus.base.api.bean.ErrorBean;
import com.tl.tplus.base.api.bean.RequestBean;
import com.tl.tplus.base.rx.ResultSubscriber;
import com.tl.tplus.base.rx.SchedulersCompat;
import com.tl.tplus.home.api.HomeApiService;
import com.tl.tplus.home.mvp.UpdateBean;
import com.tl.tplus.info.infodetail.InfoDetailActivity;
import com.tl.tplus.util.CommonUtil;
import com.tl.tplus.util.ConstanceValue;
import com.tl.tplus.widges.AppUpgradeDialog;

import butterknife.BindView;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;

/**
 * Created by sztangli on 2018-3-13.
 */

public class AboutActivity extends BaseToolBarActivity implements View.OnClickListener {
    @BindView(R.id.about_beri)
    LinearLayout about_beri;
    @BindView(R.id.about_hub)
    LinearLayout about_hub;
    @BindView(R.id.about_cek)
    LinearLayout about_cek;
    @BindView(R.id.about_version)
    TextView about_version;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_about;
    }

    @Override
    public void initFragment(Bundle savedInstanceState) {
        setTitleCenter("Tentang");

        about_version.setText(ConstanceValue.APPNAME);
        about_beri.setOnClickListener(this);
        about_hub.setOnClickListener(this);
        about_cek.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.about_beri:
                if (CommonUtil.isMobile_spExist(this)) {
                    final String GOOGLE_PLAY = "com.android.vending";//这里对应的是谷歌商店，跳转别的商店改成对应的即可
                    try {
                        if (TextUtils.isEmpty(ConstanceValue.PACKAGENAME))
                            return;
                        Uri uri = Uri.parse("market://details?id=" + ConstanceValue.PACKAGENAME);
                        Intent jumpIntent = new Intent(Intent.ACTION_VIEW, uri);
                        jumpIntent.setPackage(GOOGLE_PLAY);
                        jumpIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(jumpIntent);
                    } catch (Exception e) {

                    }
                }
//        else {
//          Uri uri = Uri.parse(dataBean.getJump_url());
//          Intent downLoadIntent = new Intent(Intent.ACTION_VIEW, uri);
//          startActivity(downLoadIntent);
//        }
                break;

            case R.id.about_hub:
                startActivity(new Intent(this, InfoDetailActivity.class));
                break;

            case R.id.about_cek:

                HomeApiService homeApiService = cashApplication.getApiServiceComponent().getRetrofit()
                        .create(HomeApiService.class);
                RequestBean requestBean = JSON.parseObject(ConstanceValue.COMMREQUESTBEAN, RequestBean
                        .class);
                String sign = CommonUtil.RequestSignData(requestBean);
                requestBean.setSign(sign);
                requestBean.setTimestamp(System.currentTimeMillis() / 1000);
                String data = JSON.toJSONString(requestBean);

                Subscription subscription = homeApiService.getNewVersion(data)
                        .compose(SchedulersCompat.applyIoSchedulers())  //以第一个订阅的线程为准
                        .doOnSubscribe(new Action0() {         //在执行前,执行更新对话框(一最近的线程为主,更新界面)
                            @Override
                            public void call() {

                            }
                        })
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .doOnTerminate(new Action0() {         //无论正常回调还是异常信息,都会调用
                            @Override
                            public void call() {

                            }
                        })
                        .subscribe(new ResultSubscriber<Object>() {
                            @Override
                            public void onError(ErrorBean bean) {
                                if (bean.getErrCode() == -1) {

                                }
                            }

                            @Override
                            public void onData(Object object) {
                                UpdateBean updateBean = (UpdateBean) object;
                                int type = updateBean.getData().getF_type();
                                if (type == 0) {
                                    Toast.makeText(AboutActivity.this, "Saat ini sudah versi terbaru", Toast
                                            .LENGTH_SHORT)
                                            .show();
                                } else {
                                    AppUpgradeDialog upgradeDialog = new AppUpgradeDialog(AboutActivity.this, updateBean);
                                    upgradeDialog.builder();
                                    upgradeDialog.show();
                                }

                            }
                        });
                break;
        }
    }
}
