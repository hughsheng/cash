package com.tl.tplus.detail;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.alibaba.fastjson.JSON;
import com.tl.tplus.R;
import com.tl.tplus.base.activity.BaseToolBarActivity;
import com.tl.tplus.base.api.bean.ErrorBean;
import com.tl.tplus.base.api.bean.RequestBean;
import com.tl.tplus.base.fragment.BaseFragment;
import com.tl.tplus.detail.dagger.DetailWebModule;
import com.tl.tplus.detail.mvp.DetailBean;
import com.tl.tplus.detail.mvp.DetailContract;
import com.tl.tplus.detail.mvp.DetailProsesBean;
import com.tl.tplus.detail.mvp.DetailReviewBean;
import com.tl.tplus.instanllapp.InstallFragment;
import com.tl.tplus.util.CommonUtil;
import com.tl.tplus.util.ConstanceValue;
import com.tl.tplus.util.IntentUtil;

import javax.inject.Inject;

import butterknife.BindView;

import static com.tl.tplus.detail.DetailComFragment.DOWN;

/**
 * Created by Administrator on 2018/3/7.
 */

public class DetailsWebFragment extends BaseFragment implements DetailContract.IView, View.OnClickListener {

    public static final String WEBURL = "webUrl";
    public static final String NAME = "name";
    public static final String DOWNLOADURL = "down_url";
    public static final String PACKEGENAME = "packege_name";
    public static final String INSTALL = "install";
    public static final String CAPSTATUS = "capstatus";
    public static final String PID = "pid";
    public static final String TYPE = "type";

    public static final String TAG = "DetailsWebFragment";

    private String packegeName;
    private String url;
    private String jumpUrl;
    private String name;
    private int installType;
    private int capStatus;
    private String pid;
    private String type;

    @BindView(R.id.ll_toolbar)
    LinearLayout toolbar;
    @BindView(R.id.detail_webview)
    WebView detail_webview;
    @BindView(R.id.download_web_text)
    TextView download_text;

    public static DetailsWebFragment newInstance(Bundle args) {
        DetailsWebFragment fragment = new DetailsWebFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Inject
    DetailContract.Presenter mPresenter;

    @Override
    public void setPresenter(@NonNull DetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_detai_web;
    }

    @Override
    public void onLogicPresenter() {
        toolbar.setVisibility(View.GONE);
        Bundle bundle = getArguments();
        name = bundle.getString(NAME);
        url = bundle.getString(WEBURL);
        jumpUrl = bundle.getString(DOWNLOADURL);
        packegeName = bundle.getString(PACKEGENAME);
        installType = bundle.getInt(INSTALL, 0);
        capStatus = bundle.getInt(CAPSTATUS, 0);
        pid = bundle.getString(PID);
        type = bundle.getString(TYPE);
        if (url != null) {
            WebSettings settings = detail_webview.getSettings();
            //适应屏幕
            settings.setUseWideViewPort(true);
            settings.setSupportZoom(true);
            settings.setJavaScriptEnabled(true);
            detail_webview.setWebViewClient(new WebViewClient() {
                public boolean shouldOverrideUrlLoading(WebView view, String url) { //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                    view.loadUrl(url);
                    return true;
                }
            });
            if (installType == DOWN) {
                final PackageManager packageManager = getActivity().getPackageManager();
                download_text.setText("Buka");
                download_text.setClickable(true);
                download_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        statisticsClick("2");
                        Intent intent = packageManager.getLaunchIntentForPackage(packegeName);
                        startActivity(intent);
                    }
                });
            } else {
                download_text.setOnClickListener(this);
                if (capStatus == 1) {
                    download_text.setText("Sudah mencapai limit harian");
                    download_text.setBackgroundColor(Color.GRAY);
                    download_text.setClickable(false);
                } else {
                    download_text.setText("DonwLoad dan Pinjam dari " + name);
                    download_text.setClickable(true);
                }
            }
        }
        detail_webview.loadUrl(url);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.download_web_text:
                String app_google_play_pre_url = "https://play.google.com/store/apps/details";
                if (null != jumpUrl && jumpUrl.contains(app_google_play_pre_url)) {
                    String pkgName = IntentUtil.getQueryParam(jumpUrl);
                    String gpUlr = IntentUtil.getGooglePlayStoreUrl(getActivity(), pkgName);
                    IntentUtil.startMarketView(getActivity(), gpUlr);
                } else {
                    Intent intent = new Intent(getActivity(), JumpWebActivity.class);
                    intent.putExtra(JumpWebActivity.JUMPURL, jumpUrl);
                    intent.putExtra(JumpWebActivity.NAME, name);
                    startActivity(intent);
                }
                statisticsClick("1");
//                if (CommonUtil.isMobile_spExist(DetaiWebActivity.this)) {
//                    final String GOOGLE_PLAY = "com.android.vending";//这里对应的是谷歌商店，跳转别的商店改成对应的即可
//                    try {
//                        if (TextUtils.isEmpty(packegeName))
//                            return;
//                        Uri uri = Uri.parse("market://details?id=" + packegeName);
//                        Intent jumpIntent = new Intent(Intent.ACTION_VIEW, uri);
//                        jumpIntent.setPackage(GOOGLE_PLAY);
//                        jumpIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(jumpIntent);
//                    } catch (Exception e) {
//
//                    }
//                } else {
//                    Uri uri = Uri.parse(url);
//                    Intent downLoadIntent = new Intent(Intent.ACTION_VIEW, uri);
//                    startActivity(downLoadIntent);
//                }
                break;

        }
    }

    /**
     * 点击统计
     */
    private void statisticsClick(String click_type) {
        RequestBean requestBean = JSON.parseObject(ConstanceValue.COMMREQUESTBEAN, RequestBean.class);
        requestBean.setPid(Integer.parseInt(pid));
        requestBean.setProduct_type(type);
        requestBean.setClick_type(click_type);
        String sign = CommonUtil.RequestSignData(requestBean);
        requestBean.setSign(sign);
        requestBean.setTimestamp(System.currentTimeMillis() / 1000);
        String data = JSON.toJSONString(requestBean);
        mPresenter.productStatistics(data);
    }

    @Override
    public void showUi(DetailBean detailBean) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showPrses(DetailProsesBean prosesBean) {

    }

    @Override
    public void showReview(DetailReviewBean reviewBean) {

    }

    @Override
    public void onLoadFail(ErrorBean errorBean) {

    }

}
