package com.tl.tplus.home.mvp;

import com.tl.tplus.base.mvp.BasePresenter;
import com.tl.tplus.base.mvp.BaseView;

/**
 * Created by sztangli on 2018-2-28.
 *
 */

public interface HomeDanaContract {

  interface IView extends BaseView<Presenter> {

    void showBanner(HomeBannerBean homeBannerBean);

    void showList(HomeListBean homeListBean);

    void saveFilterData(FilterListBean filterListBean);

    void saveGuid(GuidBean guidBean);

    void saveInstallState(GuidBean guidBean);

    void showNewVersion(UpdateBean updateBean);

    void hideLoading();

    void showLoading();
  }

  interface Presenter extends BasePresenter {
    void getBannerData(String data,int type);

    void getDanaDataList(String data);

    void getFilterDataList(String data);

    void getGuid(String data);

    void rePortInstallApp(String data);

    void getNewVersion(String data);
  }
}
