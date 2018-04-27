package com.tl.tplus.cicidetail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sztangli on 2018-3-7.
 */

public class CicidetailTipAdapter extends RecyclerView.Adapter<CicidetailTipAdapter.MyViewHolder>{
  protected Context context;
  protected List<String> datas;
  private OnItemClickListener mOnItemClickListener;

  public CicidetailTipAdapter(Context context, List<String> datas) {
    this.context = context;
    this.datas = datas;
  }

  @Override
  public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    MyViewHolder holder = new MyViewHolder
        (LayoutInflater.from(context).inflate(com.tl.tplus.R.layout
                .item_detail_tip,
            parent, false));

    return holder;
  }

  @Override
  public void onBindViewHolder(final MyViewHolder holder, int position) {
    holder.tip_tag.setText(datas.get(position));
  }

  @Override
  public int getItemCount() {
    return datas.size();
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView tip_tag;

    public MyViewHolder(View itemView) {
      super(itemView);
      tip_tag = itemView.findViewById(com.tl.tplus.R.id.tip_tag);
    }
  }

  public interface OnItemClickListener {
    void onItemClick(String tag, int position);
  }

  public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    mOnItemClickListener = onItemClickListener;
  }
}
