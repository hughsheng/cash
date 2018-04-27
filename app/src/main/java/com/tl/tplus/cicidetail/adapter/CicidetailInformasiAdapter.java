package com.tl.tplus.cicidetail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tl.tplus.R;
import com.tl.tplus.cicidetail.mvp.CicidetailProsesBean;

import java.util.List;

/**
 * Created by sztangli on 2018-3-7.
 */

public class CicidetailInformasiAdapter extends RecyclerView.Adapter<CicidetailInformasiAdapter.MyViewHolder> {
  protected Context context;
  protected List<CicidetailProsesBean.DataBean.AuditInfoBean> datas;
  private int type;
  private OnItemClickListener mOnItemClickListener;

  public CicidetailInformasiAdapter(Context context, List<CicidetailProsesBean.DataBean.AuditInfoBean>
      datas) {
    this.context = context;
    this.datas = datas;
  }

  @Override
  public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    MyViewHolder holder = new MyViewHolder
        (LayoutInflater.from(context).inflate(R.layout
                .item_proses,
            parent, false));

    return holder;
  }

  @Override
  public void onBindViewHolder(final MyViewHolder holder, int position) {
    CicidetailProsesBean.DataBean.AuditInfoBean bean = datas.get(position);
//    Glide.with(context)
//        .load(bean.getIcon())
//        .error(R.mipmap.item_icon)
//        .placeholder(R.mipmap.item_icon)
//        .into(holder.proses_img);
    holder.proses_step.setText(""+(position+1));
    holder.proses_text.setText(bean.getTitle());
    holder.proses_describle.setText(bean.getDescribe());
  }

  @Override
  public int getItemCount() {
    return datas.size();
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {
    //    CircularImage review_item_head;
//    TextView review_item_title;
//    TextView review_item_star;
//    TextView review_item_time;
//    TextView review_item_description;
    ImageView proses_img;
    TextView proses_step;
    TextView proses_text;
    TextView proses_describle;

    public MyViewHolder(View itemView) {
      super(itemView);
//      review_item_head = itemView.findViewById(R.id.review_item_head);
//      review_item_title = itemView.findViewById(R.id.review_item_title);
//      review_item_star = itemView.findViewById(R.id.review_item_star);
//      review_item_time = itemView.findViewById(R.id.review_item_time);
//      review_item_description = itemView.findViewById(R.id.review_item_description);
      proses_img = itemView.findViewById(R.id.proses_img);
      proses_step=itemView.findViewById(R.id.proses_step);
      proses_text = itemView.findViewById(R.id.proses_text);
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
