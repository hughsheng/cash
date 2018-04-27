package com.tl.tplus.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tl.tplus.home.mvp.HomeBannerBean;

import java.util.List;


/**
 * Created by sztangli on 2018-3-2.
 */


public class CashTypeAdapter extends RecyclerView.Adapter<CashTypeAdapter.MyViewHolder> {

  protected Context context;
  protected List<HomeBannerBean.DataBean.FilterWordsBean> datas;
  private OnItemClickListener mOnItemClickListener;

  public CashTypeAdapter(Context context, List<HomeBannerBean.DataBean.FilterWordsBean> datas) {
    this.context = context;
    this.datas = datas;
  }

  @Override
  public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    MyViewHolder holder = new MyViewHolder
        (LayoutInflater.from(context).inflate(com.tl.tplus.R.layout
            .item_cash_layout,
        parent, false));

    return holder;
  }

  @Override
  public void onBindViewHolder(final MyViewHolder holder, int position) {
    final HomeBannerBean.DataBean.FilterWordsBean bean=datas.get(position);
    holder.cash_type_tv.setText(bean.getName());
    View itemView = ((LinearLayout) holder.itemView).getChildAt(0);

    if (mOnItemClickListener != null) {
      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          int position = holder.getLayoutPosition();
          mOnItemClickListener.onItemClick(bean.getId(), bean
              .getType(),bean.getName());
        }
      });
    }
  }

  @Override
  public int getItemCount() {
    return datas.size();
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView cash_type_tv;

    public MyViewHolder(View itemView) {
      super(itemView);
      cash_type_tv=itemView.findViewById(com.tl.tplus.R.id.cash_type_tv);
    }
  }

  public interface OnItemClickListener {
    void onItemClick(String fw_id, String fw_type, String name);
  }
  public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    mOnItemClickListener = onItemClickListener;
  }
}
