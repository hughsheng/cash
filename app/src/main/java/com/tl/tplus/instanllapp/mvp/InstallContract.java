package com.tl.tplus.instanllapp.mvp;

import com.tl.tplus.base.mvp.BasePresenter;
import com.tl.tplus.base.mvp.BaseView;

/**
 * Created by Administrator on 2018/3/10.
 */

public interface InstallContract {
  interface IView extends BaseView<Presenter> {

    void showUi(InstallBean installBean);

    void hideLoading();

    void showLoading();

  }

  interface Presenter extends BasePresenter {

    void getUIData(String data);

  }
}
