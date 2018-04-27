package com.tl.tplus.cicidetail;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tl.tplus.base.activity.BaseNoToolBarActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/7.
 *
 */

public class CicidetaiWebActivity extends BaseNoToolBarActivity {

  public static final String WEBURL="webUrl";

  @BindView(com.tl.tplus.R.id.detail_webview)
  WebView detail_webview;


  @Override
  public int getLayoutResId() {
    return com.tl.tplus.R.layout.activity_detai_web;
  }

  @Override
  public void initFragment(Bundle savedInstanceState) {
    Intent intent=getIntent();
    String url=intent.getStringExtra(WEBURL);
    if(url!=null){
      WebSettings settings = detail_webview.getSettings();
      //适应屏幕
      settings.setUseWideViewPort(true);
      settings.setSupportZoom(true);
      settings.setJavaScriptEnabled(true);
      detail_webview.setWebViewClient(new WebViewClient() {
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        { //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
          view.loadUrl(url);
          return true;
        }
      });
      detail_webview.loadUrl(url);
    }
  }
}
