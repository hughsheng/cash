package com.tl.tplus.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;
import android.view.ViewDebug;
import android.widget.ImageView;

import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.tl.tplus.R;
import com.tl.tplus.base.app.CashApplication;
import com.tl.tplus.home.mvp.HomeCiciListBean;


import java.text.DecimalFormat;
import java.util.List;

import com.lfp.lfp_base_recycleview_library.BaseRecyclerViewAdapter;
import com.tl.tplus.util.ConstanceValue;

/**
 * Created by sztangli on 2018-3-2.
 */

public class CiciRecyclViewAdapter extends BaseTipAdapter<HomeCiciListBean.DataBean> {

  protected List<HomeCiciListBean.DataBean> datas;

  public CiciRecyclViewAdapter(Context context, List<HomeCiciListBean.DataBean> datas, int
      layoutId) {
    super(context, datas, layoutId);
  }


  @Override
  protected void bindDataToItemView(final SpaViewHolder vh, HomeCiciListBean.DataBean item, int
      relPosition) {
    ImageView home_item_icon = vh.getView(R.id.home_item_icon);
    TextView home_item_title = vh.getView(R.id.home_item_title);
    TextView home_item_score = vh.getView(R.id.home_item_score);
    TextView home_item_end = vh.getView(R.id.home_item_end);
    TextView home_item_price = vh.getView(R.id.home_item_price);
    TextView home_item_detail1 = vh.getView(R.id.home_item_detail1);
    TextView home_item_detail2 = vh.getView(R.id.home_item_detail2);
    TextView home_item_detail3 = vh.getView(R.id.home_item_detail3);
    TextView home_item_dscription = vh.getView(R.id.home_item_dscription);

    Glide.with(context)
        .load(item.getIcon())
        .error(R.mipmap.item_icon)
        .placeholder(R.mipmap.item_icon)
        .into(home_item_icon);
    home_item_title.setText(item.getProduct_name());
    DecimalFormat df = new DecimalFormat("#.0");
    home_item_score.setText(df.format(Float.valueOf(item.getTotal_score())));
    CashApplication cashApplication = CashApplication.getInstance();
    final String packname = item.getPackage_name();
    if (ConstanceValue.INSTALLLIST!=null&&ConstanceValue.INSTALLLIST.contains(packname)) {
      item.setInstall(true);
      home_item_end.setText("Buka");
      home_item_end.setTextColor(cashApplication.getResources().getColor(R.color.orange));
      home_item_end.setBackground(cashApplication.getResources().getDrawable(R.drawable
          .bg_txt_orange));
      final PackageManager packageManager = context.getPackageManager();
      home_item_end.setClickable(true);
      home_item_end.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Intent intent = packageManager.getLaunchIntentForPackage(packname);
          context.startActivity(intent);
        }
      });

    } else {
      item.setInstall(false);
      home_item_end.setOnClickListener(null);
      home_item_end.setClickable(false);
      if (item.getCap_status() == 1) {
        home_item_end.setText("Penuh");
        home_item_end.setTextColor(cashApplication.getResources().getColor(R.color.bg_gray));
        home_item_end.setBackground(cashApplication.getResources().getDrawable(R.drawable
            .bg_txt_square_gray));
      } else {
        home_item_end.setTextColor(cashApplication.getResources().getColor(R.color.orange));
        home_item_end.setBackground(cashApplication.getResources().getDrawable(R.drawable
            .bg_txt_square_red));
        home_item_end.setText("Ajukan");
      }
    }
//        home_item_price.setTextSize(26);
    if (Float.valueOf(item.getDown_payment_rate_min()) < Float.valueOf(item
        .getDown_payment_rate_max())) {
      float rate_min= Float.valueOf(item.getDown_payment_rate_min());
      float rate_max=Float.valueOf(item.getDown_payment_rate_max());
      home_item_price.setText((int)rate_min + "%~" + (int)rate_max + "%");
    } else {
      float rate_min= Float.valueOf(item.getDown_payment_rate_min());
      home_item_price.setText((int)rate_min + "%");
    }
    home_item_detail1.setText("Bunga≥" + df.format(Float.valueOf(item.getInterest_rate())) +
        "%/bulan");
    if(Integer.valueOf(item.getStaging_cycle_min())==Integer.valueOf(item.getStaging_cycle_max())){
      home_item_detail2.setText("Durasi " + item.getStaging_cycle_min());
    }else {
      home_item_detail2.setText("Durasi " + item.getStaging_cycle_min() + "-" + item
          .getStaging_cycle_max() + "bulan");
    }
    home_item_detail3.setText("Uang muka");
    home_item_dscription.setText(item.getReview());

  }


}
