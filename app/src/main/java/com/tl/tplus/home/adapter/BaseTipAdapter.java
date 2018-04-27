package com.tl.tplus.home.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;


import com.tl.tplus.R;
import com.lfp.lfp_base_recycleview_library.BaseRecyclerViewAdapter;

import java.util.List;

/**
 * Created by linfp on 2016/7/5.
 * 处理异常的总体适配器
 */
public abstract class BaseTipAdapter<T> extends BaseRecyclerViewAdapter<T> {

    protected View.OnClickListener mClickListener;

    public BaseTipAdapter(Context context, List<T> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    public BaseTipAdapter(Context context, List<T> datas, int layoutId, Object footView) {
        super(context, datas, layoutId, footView);
    }

    public void setClickListener(View.OnClickListener clickListener) {
        this.mClickListener = clickListener;
    }

    @Override
    public void bindEmptyView(SpaViewHolder holder, int position, Object tipContent) {
        if (tipContent == null) {
            throw new NullPointerException("必须有提示内容");
        }
        final TipBean tipBean = (TipBean) tipContent;
        final AppCompatButton net_btn = holder.getView(R.id.net_btn);
        final TextView content_txt = holder.getView(R.id.content_txt);
        switch (tipBean.getDataStatus()) {
            case NO_NET:
//                showView(holder, false, false, true, false);
//                content_txt.setText("请确保网络通畅");
//                net_btn.setText("刷新");
//                net_btn.setOnClickListener(mClickListener);
                break;

            case NO_DATA:
//                showView(holder, true, false, false,false);
//                content_txt.setText(tipBean.getContent());
//                if (!"".equals(tipBean.getBtnTxt())) {
//                    net_btn.setVisibility(View.VISIBLE);
//                } else {
//                    net_btn.setVisibility(View.INVISIBLE);
//                }
//                net_btn.setText(tipBean.getBtnTxt());
//                net_btn.setOnClickListener(mClickListener);
                break;

        }
    }

    private void showView(SpaViewHolder holder, boolean no_data, boolean no_login, boolean no_net, boolean no_card) {
        holder.setVisibleViewGrpup(R.id.no_data_id, no_data);
        holder.setVisibleViewGrpup(R.id.no_net_id, no_net);
    }
}
