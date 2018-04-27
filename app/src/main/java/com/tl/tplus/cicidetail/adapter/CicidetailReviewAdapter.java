package com.tl.tplus.cicidetail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tl.tplus.R;
import com.tl.tplus.cicidetail.mvp.CicidetailReviewBean;
import com.tl.tplus.widges.CircularImage;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by sztangli on 2018-3-7.
 *
 */

public class CicidetailReviewAdapter extends RecyclerView.Adapter<CicidetailReviewAdapter.MyViewHolder> {
  protected Context context;
  protected List<CicidetailReviewBean.DataBean.UserReviewBean> datas;
  private OnItemClickListener mOnItemClickListener;

  public CicidetailReviewAdapter(Context context, List<CicidetailReviewBean.DataBean.UserReviewBean>
      datas) {
    this.context = context;
    this.datas = datas;
  }

  @Override
  public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    MyViewHolder holder = new MyViewHolder
        (LayoutInflater.from(context).inflate(com.tl.tplus.R.layout
                .item_review,
            parent, false));

    return holder;
  }

  @Override
  public void onBindViewHolder(final MyViewHolder holder, int position) {
    CicidetailReviewBean.DataBean.UserReviewBean bean = datas.get(position);
    Glide.with(context)
        .load(bean.getHead_img())
        .error(R.mipmap.head_default)
        .placeholder(R.mipmap.head_default)
        .into(holder.review_item_head);
    //holder.review_item_title.setText(bean.getNickname());
    DecimalFormat df = new DecimalFormat("#.0");
    holder.review_item_title.setText(bean.getNickname());
    holder.review_item_star.setText("Penilaian:" + df.format(Float.valueOf(bean
        .getScore())));
    holder.review_item_time.setText(bean.getReview_date());
    holder.review_item_description.setText(bean.getContent());
  }

  @Override
  public int getItemCount() {
    return datas.size();
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {
    CircularImage review_item_head;
    TextView review_item_title;
    TextView review_item_star;
    TextView review_item_time;
    TextView review_item_description;


    public MyViewHolder(View itemView) {
      super(itemView);
      review_item_head = itemView.findViewById(com.tl.tplus.R.id.review_item_head);
      review_item_title = itemView.findViewById(com.tl.tplus.R.id.review_item_title);
      review_item_star = itemView.findViewById(com.tl.tplus.R.id.review_item_star);
      review_item_time = itemView.findViewById(com.tl.tplus.R.id.review_item_time);
      review_item_description = itemView.findViewById(com.tl.tplus.R.id.review_item_description);
    }
  }

  public interface OnItemClickListener {
    void onItemClick(String tag, int position);
  }

  public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    mOnItemClickListener = onItemClickListener;
  }
}
