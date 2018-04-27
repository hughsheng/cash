package com.tl.tplus.base.mvp;

import android.support.annotation.NonNull;

import com.tl.tplus.base.api.bean.ErrorBean;


/**
 * Created by fangpenglin on 16/6/10.
 * 基础视图层管理
 */
public interface BaseView<T> {
    /**
     * 设置路由层
     *
     * @param presenter (根据泛型选定路由层)
     */
    void setPresenter(@NonNull T presenter);

    /**
     * 错误信息(包含错误编码和错误描述)
     *
     * @param errorBean (记录错误信息编码和错误信息描述)
     */
    void onLoadFail(ErrorBean errorBean);
}
