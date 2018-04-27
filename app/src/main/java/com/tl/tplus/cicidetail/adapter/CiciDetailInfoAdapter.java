package com.tl.tplus.cicidetail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tl.tplus.cicidetail.mvp.CicidetailBean;

import java.util.List;

/**
 * Created by sztangli on 2018-3-7.
 */

public class CiciDetailInfoAdapter extends RecyclerView.Adapter<CiciDetailInfoAdapter.MyViewHolder> {
  protected Context context;
  protected List<CicidetailBean.DataBean.DetailsInfoBean> datas;
  private OnItemClickListener mOnItemClickListener;

  public CiciDetailInfoAdapter(Context context, List<CicidetailBean.DataBean.DetailsInfoBean> datas) {
    this.context = context;
    this.datas = datas;
  }

  @Override
  public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    MyViewHolder holder = new MyViewHolder
        (LayoutInflater.from(context).inflate(com.tl.tplus.R.layout
                .item_detail_info,
            parent, false));

    return holder;
  }

  @Override
  public void onBindViewHolder(final MyViewHolder holder, int position) {
    holder.detial_info_text.setText(datas.get(position).getContent());
  }

  @Override
  public int getItemCount() {
    return datas.size();
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView detial_info_text;

    public MyViewHolder(View itemView) {
      super(itemView);
      detial_info_text = itemView.findViewById(com.tl.tplus.R.id.detial_info_text);
    }
  }

  public interface OnItemClickListener {
    void onItemClick(String tag, int position);
  }

  public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    mOnItemClickListener = onItemClickListener;
  }
}
