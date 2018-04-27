package com.tl.tplus.cicidetailinfo.mvp;

import com.tl.tplus.base.mvp.BasePresenter;
import com.tl.tplus.base.mvp.BaseView;

/**
 * Created by Administrator on 2018/3/7.
 *
 */

public interface CicidetailInfoContract {
  interface IView extends BaseView<Presenter> {

    void showUi(CicidetailInfoBean cicidetailInfoBean);

    void hideLoading();

    void showLoading();

  }

  interface Presenter extends BasePresenter {

    void getUIData(String data);

  }
}
