package com.tl.tplus.filter.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tl.tplus.R;
import com.tl.tplus.base.app.CashApplication;
import com.tl.tplus.detail.DetailComFragment;
import com.tl.tplus.filter.mvp.FilterBean;
import com.tl.tplus.util.CommonUtil;
import com.tl.tplus.util.ConstanceValue;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 2018/3/10.
 */

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.MyViewHolder> {
  protected Context context;
  protected List<FilterBean.DataBean> datas;
  private OnItemClickListener mOnItemClickListener;

  public FilterAdapter(Context context, List<FilterBean.DataBean> datas) {
    this.context = context;
    this.datas = datas;
  }

  @Override
  public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R
        .layout
        .item_filter, parent, false));
    return holder;
  }

  @Override
  public void onBindViewHolder(final MyViewHolder holder, final int position) {
    final FilterBean.DataBean dataBean = datas.get(position);
    Glide.with(context)
        .load(dataBean.getIcon())
        .error(com.tl.tplus.R.mipmap.item_icon)
        .placeholder(com.tl.tplus.R.mipmap.item_icon)
        .into(holder.home_item_icon);
    holder.home_item_title.setText(dataBean.getProduct_name());
    DecimalFormat df = new DecimalFormat("#.0");
    holder.home_item_score.setText(df.format(Float.valueOf(dataBean.getTotal_score())));

    float priceMax = Float.valueOf(dataBean.getPrice_max());
    float priceMin = Float.valueOf(dataBean.getPrice_min());

    if (priceMax == priceMin) {
      holder.home_item_price.setText("Rp " + CommonUtil.getSortPrice((int) priceMin + ""));
    } else {
      holder.home_item_price.setText("Rp " + CommonUtil.getSortPrice((int) priceMin + "") + "~" +
          CommonUtil.getSortPrice((int) priceMax + ""));
    }

    holder.home_item_detail1.setText("Bunga≥" + dataBean.getInterest_rate() + "%/" + dataBean
        .getInterest_rate_unit());
    if (Integer.valueOf(dataBean.getTime_day_min()) == Integer.valueOf(dataBean.getTime_day_max()
    )) {
      holder.home_item_detail2.setText("Waktu:" + dataBean.getTime_day_min() + " hari");
    } else {
      holder.home_item_detail2.setText("Waktu:" + dataBean.getTime_day_min() + "~" + dataBean
          .getTime_day_max() + " hari");
    }
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        int type = 0;
        if (dataBean.isInstalled()) {
          type = DetailComFragment.DOWN;
        }
        mOnItemClickListener.onItemClick(dataBean.getProduct_id(), type);
      }
    });


    CashApplication cashApplication = CashApplication.getInstance();
    final String packname = dataBean.getPackage_name();
    if (ConstanceValue.INSTALLLIST != null && ConstanceValue.INSTALLLIST.contains(packname)) {
      dataBean.setInstalled(true);
      holder.home_item_end.setText("Buka");
      holder.home_item_end.setTextColor(cashApplication.getResources().getColor(R.color.orange));
      holder.home_item_end.setBackground(cashApplication.getResources().getDrawable(R.drawable
          .bg_txt_orange));
      final PackageManager packageManager = context.getPackageManager();
      holder.home_item_end.setClickable(true);
      holder.home_item_end.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Intent intent = packageManager.getLaunchIntentForPackage(packname);
          context.startActivity(intent);
        }
      });

    } else {
      dataBean.setInstalled(false);
      holder.home_item_end.setOnClickListener(null);
      holder.home_item_end.setClickable(false);
      if (dataBean.getCap_status() == 1) {
        holder.home_item_end.setText("Penuh");
        holder.home_item_end.setTextColor(cashApplication.getResources().getColor(R.color.bg_gray));
        holder.home_item_end.setBackground(cashApplication.getResources().getDrawable(R.drawable
            .bg_txt_square_gray));
      } else {
        holder.home_item_end.setTextColor(cashApplication.getResources().getColor(R.color
            .text_item_edge));
        holder.home_item_end.setBackground(cashApplication.getResources().getDrawable(R.drawable
            .bg_txt_square_red));
        holder.home_item_end.setText("Ajukan");
      }
    }

  }

  @Override
  public int getItemCount() {
    return datas.size();
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView home_item_icon;
    TextView home_item_title;
    TextView home_item_score;
    TextView home_item_end;
    TextView home_item_price;
    TextView home_item_detail1;
    TextView home_item_detail2;

    public MyViewHolder(View itemView) {
      super(itemView);
      home_item_icon = itemView.findViewById(com.tl.tplus.R.id.home_item_icon);
      home_item_title = itemView.findViewById(com.tl.tplus.R.id.home_item_title);
      home_item_score = itemView.findViewById(com.tl.tplus.R.id.home_item_score);
      home_item_end = itemView.findViewById(com.tl.tplus.R.id.home_item_end);
      home_item_price = itemView.findViewById(com.tl.tplus.R.id.home_item_price);
      home_item_detail1 = itemView.findViewById(com.tl.tplus.R.id.home_item_detail1);
      home_item_detail2 = itemView.findViewById(com.tl.tplus.R.id.home_item_detail2);
    }
  }

  public interface OnItemClickListener {
    void onItemClick(String tag, int type);
  }

  public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    mOnItemClickListener = onItemClickListener;
  }
}
