package com.tl.tplus.detail;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.tl.tplus.R;
import com.tl.tplus.base.activity.BaseToolBarActivity;
import com.tl.tplus.base.api.bean.ErrorBean;
import com.tl.tplus.base.api.bean.RequestBean;
import com.tl.tplus.detail.mvp.DetailBean;
import com.tl.tplus.detail.mvp.DetailContract;
import com.tl.tplus.detail.mvp.DetailProsesBean;
import com.tl.tplus.detail.mvp.DetailReviewBean;
import com.tl.tplus.util.CommonUtil;
import com.tl.tplus.util.ConstanceValue;
import com.tl.tplus.util.IntentUtil;

import javax.inject.Inject;

import butterknife.BindView;

import static com.tl.tplus.detail.DetailComFragment.DOWN;

/**
 * Created by Administrator on 2018/3/7.
 */

public class JumpWebActivity extends BaseToolBarActivity {

    public static final String JUMPURL = "jumpUrl";
    public static final String NAME = "name";

    private String jumpUrl;
    private String name;

    private boolean isReumeAble = false;

    @BindView(R.id.detail_webview)
    WebView detail_webview;
    @BindView(R.id.download_web_text)
    TextView download_text;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_detai_web;
    }

    @Override
    public void initFragment(Bundle savedInstanceState) {
        Intent intent = getIntent();
        name = intent.getStringExtra(NAME);
        jumpUrl = intent.getStringExtra(JUMPURL);
        download_text.setVisibility(View.GONE);
        setTitleCenter(name);
        if (!TextUtils.isEmpty(jumpUrl)) {
            WebSettings settings = detail_webview.getSettings();
            //适应屏幕
            settings.setUseWideViewPort(true);
            settings.setSupportZoom(true);
            settings.setJavaScriptEnabled(true);
//            detail_webview.setWebViewClient(new WebViewClient() {
//                public boolean shouldOverrideUrlLoading(WebView view, String url) { //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
//                    view.loadUrl(url);
//                    return true;
//                }
//            });

            //设置WebViewClient类
            detail_webview.setWebViewClient(new WebViewClient() {
                @Override
                public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                    openGoolePlay(url);
                    return super.shouldInterceptRequest(view, url);
                }

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    openGoolePlay(url);
                    return false;
                }

                //设置加载前的函数
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                }

                //设置结束加载函数
                @Override
                public void onPageFinished(WebView view, String url) {
                }

                @Override
                public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                    super.onReceivedError(view, request, error);
                }
            });

            detail_webview.loadUrl(jumpUrl);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isReumeAble) {
            isReumeAble = false;
            finish();
        }
    }

    /**
     * 判断链接打开GP
     *
     * @param url
     */
    private void openGoolePlay(String url) {
        String app_google_play_pre_url = "https://play.google.com/store/apps/details";
        if (detail_webview != null)
            detail_webview.setVisibility(View.VISIBLE);
        if (url.indexOf(app_google_play_pre_url) != -1) {
            String pkgName = IntentUtil.getQueryParam(url);
            String gpUlr = IntentUtil.getGooglePlayStoreUrl(this, pkgName);
            IntentUtil.startMarketView(this, gpUlr);
            isReumeAble = true;
        } else if (url.indexOf("market://details") != -1) {
            if (detail_webview != null)
                detail_webview.setVisibility(View.GONE);
            String pkgName = IntentUtil.getQueryParam(url);
            String gpUlr = IntentUtil.getGooglePlayStoreUrl(this, pkgName);
            IntentUtil.startMarketView(this, gpUlr);
            isReumeAble = true;
        }
    }


}
