package com.tl.tplus.instanllapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tl.tplus.R;
import com.tl.tplus.instanllapp.mvp.InstallBean;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 2018/3/10.
 */

public class InstallAdapter extends RecyclerView.Adapter<InstallAdapter.MyViewHolder> {
  protected Context context;
  protected List<InstallBean.DataBean> datas;
  private OnItemClickListener mOnItemClickListener;

  public InstallAdapter(Context context, List<InstallBean.DataBean> datas) {
    this.context = context;
    this.datas = datas;
  }

  @Override
  public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    MyViewHolder holder = new MyViewHolder
        (LayoutInflater.from(context).inflate(R.layout
                .item_install,
            parent, false));

    return holder;
  }

  @Override
  public void onBindViewHolder(final MyViewHolder holder, final int position) {
    InstallBean.DataBean dataBean = datas.get(position);
    Glide.with(context)
        .load(dataBean.getIcon())
        .error(R.mipmap.item_icon)
        .placeholder(R.mipmap.item_icon)
        .into(holder.install_app_icon);
    holder.install_app_name.setText(dataBean.getProduct_name());
    DecimalFormat df = new DecimalFormat("#.0");
    holder.install_app_score.setText(df.format(Float.valueOf(dataBean.getTotal_score())));
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mOnItemClickListener.onItemClick(datas.get(position).getPackage_name(),position);
      }
    });
  }

  @Override
  public int getItemCount() {
    return datas.size();
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView install_app_icon;
    TextView install_app_name;
    TextView install_app_score;
    TextView install_item_end;

    public MyViewHolder(View itemView) {
      super(itemView);
      install_app_icon = itemView.findViewById(R.id.install_app_icon);
      install_app_name = itemView.findViewById(R.id.install_app_name);
      install_app_score = itemView.findViewById(R.id.install_app_score);
      install_item_end = itemView.findViewById(R.id.install_item_end);
    }
  }

  public interface OnItemClickListener {
    void onItemClick(String tag, int position);
  }

  public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    mOnItemClickListener = onItemClickListener;
  }
}
