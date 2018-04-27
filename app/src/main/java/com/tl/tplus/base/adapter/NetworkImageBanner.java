package com.tl.tplus.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.tl.tplus.R;


/**
 * Author: jinkx
 * Date: 2016/3/25
 * Describe：首页广告栏从网络加载图片的ViewPager适配器
 */
public class NetworkImageBanner implements Holder<String> {
    private LayoutInflater mLayoutInflater;
    private ImageView imageView;

    public NetworkImageBanner(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }
    @Override
    public View createView(Context context) {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
        imageView = (ImageView) mLayoutInflater.inflate(R.layout.img_banner, null);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        Glide.with(context)
                .load(data)
                .into(imageView);
    }
}