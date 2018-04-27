package com.tl.tplus.strategy.mvp;

import com.tl.tplus.base.mvp.BasePresenter;
import com.tl.tplus.base.mvp.BaseView;

/**
 * Created by Administrator on 2018/3/10.
 */

public interface StrategyContract {
  interface IView extends BaseView<Presenter> {

    void showUi(StrategyBean strategyBean);

    void hideLoading();

    void showLoading();

  }

  interface Presenter extends BasePresenter {

    void getUIData(String data);

  }
}
