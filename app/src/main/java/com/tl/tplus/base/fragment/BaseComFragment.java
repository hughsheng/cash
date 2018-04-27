package com.tl.tplus.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.tl.tplus.base.api.ApiServiceComponent;
import com.tl.tplus.base.app.CashApplication;

/**
 * Created by sztangli on 2018-2-27.
 * 基类fragment
 */

public abstract class BaseComFragment extends Fragment {

  protected CashApplication cashApplication;
  protected FragmentManager mChildFragmentManager;
  protected SVProgressHUD svProgressHUD;
//  protected Gson gson;

  public BaseComFragment() {
    // Required empty public constructor
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    cashApplication = CashApplication.getInstance();
    mChildFragmentManager = getChildFragmentManager();
    svProgressHUD = new SVProgressHUD(getActivity());

   // gson=new Gson();
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    onLogicPresenter();
  }


  protected ApiServiceComponent getApiServiceComponent() {
    return cashApplication.getApiServiceComponent();
  }

  /**
   * 隐藏对话框
   */
  protected void dismiss() {
    if (svProgressHUD.isShowing()) {
      svProgressHUD.dismiss();
    }
  }

  /**
   * 显示对话框
   *
   * @param showMes (提示用语)
   */
  protected void show(String showMes) {
    if (!svProgressHUD.isShowing()) {
      svProgressHUD.showWithStatus(showMes);
    }
  }



  public View getRootView() {
    return ((ViewGroup) getActivity().findViewById(android.R.id.content)).getChildAt(0);
  }

  public void showSnackbarTip(int resId) {
    Snackbar.make(getRootView(), resId, Snackbar.LENGTH_SHORT).show();
  }

  public void showSnackbarTip(String message) {
    Snackbar.make(getRootView(), message, Snackbar.LENGTH_SHORT).show();
  }

  public void showToastTip(int resId) {
    Toast.makeText(getActivity(), getString(resId), Toast.LENGTH_SHORT).show();
  }

  public void showToastTip(String message) {
    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
  }


  /**
   * view视图创建时调用
   */
  public abstract void onLogicPresenter();


}
