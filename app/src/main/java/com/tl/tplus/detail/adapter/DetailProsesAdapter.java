package com.tl.tplus.detail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.tl.tplus.R;
import com.tl.tplus.detail.mvp.DetailProsesBean;

import java.util.List;

/**
 * Created by sztangli on 2018-3-7.
 *
 */

public class DetailProsesAdapter extends RecyclerView.Adapter<DetailProsesAdapter.MyViewHolder> {
  protected Context context;
  protected List<DetailProsesBean.DataBean.ProcedureBean> datas;
  private int type;
  private OnItemClickListener mOnItemClickListener;

  public DetailProsesAdapter(Context context, List<DetailProsesBean.DataBean.ProcedureBean>
      datas) {
    this.context = context;
    this.datas = datas;
  }

  @Override
  public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    MyViewHolder holder = new MyViewHolder
        (LayoutInflater.from(context).inflate(com.tl.tplus.R.layout
                .item_proses,
            parent, false));

    return holder;
  }

  @Override
  public void onBindViewHolder(final MyViewHolder holder, int position) {
    DetailProsesBean.DataBean.ProcedureBean bean = datas.get(position);
//    Glide.with(context)
//        .load(bean.getIcon())
//        .error(com.tl.bostunai.R.mipmap.item_icon)
//        .placeholder(com.tl.bostunai.R.mipmap.item_icon)
//        .into(holder.proses_img);
    holder.proses_describle.setVisibility(View.GONE);
    holder.proses_step.setText(bean.getStep());
    holder.proses_text.setText(bean.getTitle());
  }

  @Override
  public int getItemCount() {
    return datas.size();
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView proses_img;
    TextView proses_step;
    TextView proses_text;
    TextView proses_describle;
    public MyViewHolder(View itemView) {
      super(itemView);
      proses_img = itemView.findViewById(com.tl.tplus.R.id.proses_img);
      proses_step=itemView.findViewById(R.id.proses_step);
      proses_text = itemView.findViewById(com.tl.tplus.R.id.proses_text);
      proses_describle=itemView.findViewById(R.id.proses_describle);
    }
  }

  public interface OnItemClickListener {
    void onItemClick(String tag, int position);
  }

  public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    mOnItemClickListener = onItemClickListener;
  }
}
