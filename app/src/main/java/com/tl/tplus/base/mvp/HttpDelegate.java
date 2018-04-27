package com.tl.tplus.base.mvp;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by fangpenglin on 16/6/19.
 * 处理rxjava的数据回传
 */
public abstract class HttpDelegate {
    private CompositeSubscription mCompositeSubscription;

    public void addSubscription(Subscription subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscriber);
    }

    /**
     * RxJava取消注册，以避免内存泄露
     */
    public void onUnSubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
