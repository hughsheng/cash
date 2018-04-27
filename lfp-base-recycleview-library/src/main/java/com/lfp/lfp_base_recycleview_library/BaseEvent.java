package com.lfp.lfp_base_recycleview_library;

import android.view.View;

/**
 * Created by lfp on 2016/5/7.
 * 适配器事件
 */
public class BaseEvent {

    public interface OnItemClickListener<T> {
        void onItemClick(View view, T data, int position);
    }

    public interface OnLongItemClickListener<T> {
        void onLongItemClick(View view, T data, int position);
    }
}
