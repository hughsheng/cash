package com.tl.tplus.home.mvp;

import com.tl.tplus.base.mvp.BasePresenter;
import com.tl.tplus.base.mvp.BaseView;

/**
 * Created by sztangli on 2018-3-2.
 *
 */

public interface HomeCiciContract {

  interface IView extends BaseView<Presenter> {

    void showList(HomeCiciListBean homeCiciListBean);

    void hideLoading();

    void showLoading();
  }

  interface Presenter extends BasePresenter {

    void getCiciDataList(String data, int loadType);
  }
}
