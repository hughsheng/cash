package com.tl.tplus.strategy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.tl.tplus.detail.DetailComFragment;
import com.tl.tplus.strategy.mvp.StrategyBean;
import com.tl.tplus.util.CommonUtil;
import com.tl.tplus.util.ConstanceValue;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 2018/3/10.
 */

public class StrategyAdapter extends RecyclerView.Adapter<StrategyAdapter.MyViewHolder> {
  protected Context context;
  protected List<StrategyBean.DataBean> datas;
  private OnItemClickListener mOnItemClickListener;

  public StrategyAdapter(Context context, List<StrategyBean.DataBean> datas) {
    this.context = context;
    this.datas = datas;
  }

  @Override
  public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    MyViewHolder holder = new MyViewHolder
        (LayoutInflater.from(context).inflate(com.tl.tplus.R.layout
                .item_strategy,
            parent, false));

    return holder;
  }

  @Override
  public void onBindViewHolder(final MyViewHolder holder, final int position) {
    final StrategyBean.DataBean dataBean = datas.get(position);
    Glide.with(context)
        .load(dataBean.getIcon())
        .error(com.tl.tplus.R.mipmap.item_icon)
        .placeholder(com.tl.tplus.R.mipmap.item_icon)
        .into(holder.home_item_icon);
    holder.home_item_title.setText(dataBean.getProduct_name());
    DecimalFormat df = new DecimalFormat("#.0");
    holder.home_item_score.setText(df.format(Float.valueOf(dataBean.getTotal_score())));
    float pricef=Float.valueOf(dataBean.getPrice_max());
    int priceI= (int) pricef;
    holder.home_item_detail1.setText("Rp" + CommonUtil.getSortPrice(priceI+""));
    holder.home_item_detail3.setText("Bunga≥" + dataBean.getInterest_rate() + "%/" + dataBean
        .getInterest_rate_unit());
    holder.home_item_detail2.setText("Proses" + dataBean.getLoan_time_day() + " " + dataBean
        .getInterest_rate_unit());
    final String packname = dataBean.getPackage_name();
    if (ConstanceValue.INSTALLLIST != null && ConstanceValue.INSTALLLIST.contains(packname)) {
      dataBean.setInstalled(true);
    }
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        int type=0;
        if (dataBean.isInstalled()) {
          type = DetailComFragment.DOWN;
        }
        mOnItemClickListener.onItemClick(dataBean.getPid(),type);
      }
    });
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
    TextView home_item_detail3;

    public MyViewHolder(View itemView) {
      super(itemView);
      home_item_icon = itemView.findViewById(com.tl.tplus.R.id.home_item_icon);
      home_item_title = itemView.findViewById(com.tl.tplus.R.id.home_item_title);
      home_item_score = itemView.findViewById(com.tl.tplus.R.id.home_item_score);
      home_item_end = itemView.findViewById(com.tl.tplus.R.id.home_item_end);
      home_item_price = itemView.findViewById(com.tl.tplus.R.id.home_item_price);
      home_item_detail1 = itemView.findViewById(com.tl.tplus.R.id.home_item_detail1);
      home_item_detail2 = itemView.findViewById(com.tl.tplus.R.id.home_item_detail2);
      home_item_detail3 = itemView.findViewById(com.tl.tplus.R.id.home_item_detail3);
    }
  }

  public interface OnItemClickListener {
    void onItemClick(String tag, int type);
  }

  public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    mOnItemClickListener = onItemClickListener;
  }
}
