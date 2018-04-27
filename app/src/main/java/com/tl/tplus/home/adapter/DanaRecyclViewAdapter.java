package com.tl.tplus.home.adapter;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tl.tplus.R;
import com.tl.tplus.base.app.CashApplication;
import com.tl.tplus.home.mvp.HomeListBean;
import com.tl.tplus.util.CommonUtil;
import com.tl.tplus.util.ConstanceValue;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by sztangli on 2017/8/15.
 *
 */

public class DanaRecyclViewAdapter extends BaseTipAdapter<HomeListBean.DataBean> {

  protected List<HomeListBean.DataBean> datas;

  public DanaRecyclViewAdapter(Activity context, List<HomeListBean.DataBean> datas, int layoutId) {
    super(context, datas, layoutId);
  }


  @Override
  protected void bindDataToItemView(final SpaViewHolder vh, HomeListBean.DataBean item, int
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
    if (ConstanceValue.INSTALLLIST != null && ConstanceValue.INSTALLLIST.contains(packname)) {
      item.setInstalled(true);
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
      item.setInstalled(false);
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


    //  holder.home_item_end.setText(dataBean.getTags_title()); item.getPrice_unit_text() +
    float pricec = Float.valueOf(item.getPrice_max());
    int priceI = (int) pricec;
    home_item_price.setText("Rp " + CommonUtil.getSortPrice(priceI + ""));
    home_item_detail1.setText("Bunga≥" + item.getInterest_rate() + "%/" + item
        .getInterest_rate_unit());
    home_item_detail2.setText("Proses " + item.getLoan_time_day() + item.getLoan_time_day_unit());
    home_item_dscription.setText(item.getReview());

  }

  @Override
  public void bindFooterView(SpaViewHolder holder, int position) {
    final TextView look_btn = holder.getView(R.id.look_btn);
    if (!isHasData()) {
      look_btn.setText(R.string.no_data);
    } else {
      look_btn.setText(R.string.up_more_str);
    }
  }

}
