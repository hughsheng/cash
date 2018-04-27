package com.tl.tplus.dialogfragment;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.tl.tplus.R;
import com.tl.tplus.home.mvp.FilterListBean;
import com.tl.tplus.util.ConstanceValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sztangli on 2018-3-5.
 *
 */

public class FilterDialogFragment extends DialogFragment implements View.OnClickListener {

  public static final String TAG = "SearchVoiceDialogFragme";
  private FilterDialogFragmentListener listener;
  private View view;
  private RadioGroup rp1, rp2, rp3, rp4;


  public static FilterDialogFragment newInstance() {
    return new FilterDialogFragment();
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    listener = (FilterDialogFragmentListener) context;
  }

  @Override
  public void onStart() {
    super.onStart();
    Window window = getDialog().getWindow();
    WindowManager.LayoutParams params = window.getAttributes();
    params.gravity = Gravity.TOP;
    params.width = WindowManager.LayoutParams.MATCH_PARENT;
    window.setAttributes(params);
    Dialog dialog = getDialog();
    if (dialog != null) {
      DisplayMetrics dm = new DisplayMetrics();
      getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
      dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams
          .WRAP_CONTENT);
    }

  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    view = LayoutInflater.from(getActivity()).inflate(R.layout.filter_layout, null);
    TextView tv1 = view.findViewById(com.tl.tplus.R.id.filter_tv1);
    TextView tv2 = view.findViewById(com.tl.tplus.R.id.filter_tv2);
    TextView tv3 = view.findViewById(com.tl.tplus.R.id.filter_tv3);
    TextView tv4 = view.findViewById(com.tl.tplus.R.id.filter_tv4);
    ImageView back = view.findViewById(com.tl.tplus.R.id.filter_back);
    TextView done = view.findViewById(com.tl.tplus.R.id.filter_done);
    back.setOnClickListener(this);
    done.setOnClickListener(this);
    rp1 = view.findViewById(com.tl.tplus.R.id.radio_group1);
    rp2 = view.findViewById(com.tl.tplus.R.id.radio_group2);
    rp3 = view.findViewById(com.tl.tplus.R.id.radio_group3);
    rp4 = view.findViewById(com.tl.tplus.R.id.radio_group4);
    Context context = getContext();


    FilterListBean filterListBean = ConstanceValue.FILTERLISTBEAN;
    if (filterListBean != null) {
      List<FilterListBean.DataBean.SelectInterestsBean> selectInterestsBeanList = filterListBean
          .getData().getSelect_interests();

      List<FilterListBean.DataBean.SelectMoneyBean> selectMoneyBeanList = filterListBean.getData
          ().getSelect_money();

      List<FilterListBean.DataBean.SelectTimeBean> selectTimeBeanList = filterListBean.getData()
          .getSelect_time();

      List<FilterListBean.DataBean.SelectAddressBean> selectAddressBeanList = filterListBean
          .getData().getSelect_address();

      float density = getResources().getDisplayMetrics().density;
      RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(RadioGroup.LayoutParams
          .WRAP_CONTENT, 50);
      int margin = (int) (6 * density);
      params.setMargins(margin, margin, margin, margin);

      if (selectInterestsBeanList != null && selectInterestsBeanList.size() > 0) {
        for (FilterListBean.DataBean.SelectInterestsBean interestsBean : selectInterestsBeanList) {
          RadioButton tempButton = new RadioButton(context);
          tempButton.setBackgroundResource(com.tl.tplus.R.drawable.bg_filter_item);   //
          // 设置RadioButton的背景图片
          tempButton.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));           // 设置按钮的样式
          tempButton.setPadding(12, 6, 12, 6);                 // 设置文字距离按钮四周的距离
          tempButton.setTextColor(Color.BLACK);
          tempButton.setGravity(Gravity.CENTER);
          tempButton.setTextSize(12);
          tempButton.setText(interestsBean.getValue());
          tempButton.setTag(interestsBean.getInterest_id());
          rp1.addView(tempButton, params);
        }

        for (FilterListBean.DataBean.SelectMoneyBean selectMoneyBean : selectMoneyBeanList) {
          RadioButton tempButton = new RadioButton(context);
          tempButton.setBackgroundResource(com.tl.tplus.R.drawable.bg_filter_item);   //
          // 设置RadioButton的背景图片
          tempButton.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));// 设置按钮的样式
          tempButton.setPadding(12, 6, 12, 6);                      // 设置文字距离按钮四周的距离
          tempButton.setTextColor(Color.BLACK);
          tempButton.setGravity(Gravity.CENTER);
          tempButton.setTextSize(12);         // 设置文字距离按钮四周的距离
          tempButton.setText(selectMoneyBean.getValue());
          tempButton.setTag(selectMoneyBean.getMoney_id());
          rp2.addView(tempButton, params);
        }


        for (FilterListBean.DataBean.SelectTimeBean selectTimeBean : selectTimeBeanList) {
          RadioButton tempButton = new RadioButton(context);
          tempButton.setBackgroundResource(com.tl.tplus.R.drawable.bg_filter_item);   //
          // 设置RadioButton的背景图片
          tempButton.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));           // 设置按钮的样式
          tempButton.setPadding(12, 6, 12, 6);                    // 设置文字距离按钮四周的距离
          tempButton.setTextColor(Color.BLACK);
          tempButton.setGravity(Gravity.CENTER);
          tempButton.setTextSize(12);       // 设置文字距离按钮四周的距离
          tempButton.setText(selectTimeBean.getValue());
          tempButton.setTag(selectTimeBean.getTime_id());
          rp3.addView(tempButton, params);
        }

        for (FilterListBean.DataBean.SelectAddressBean selectAddressBean : selectAddressBeanList) {
          RadioButton tempButton = new RadioButton(context);
          tempButton.setBackgroundResource(com.tl.tplus.R.drawable.bg_filter_item);   //
          // 设置RadioButton的背景图片
          tempButton.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));           // 设置按钮的样式
          tempButton.setPadding(12, 6, 12, 6);                     // 设置文字距离按钮四周的距离
          tempButton.setTextColor(Color.BLACK);
          tempButton.setGravity(Gravity.CENTER);
          tempButton.setTextSize(12);      // 设置文字距离按钮四周的距离
          tempButton.setText(selectAddressBean.getValue());
          tempButton.setTag(selectAddressBean.getAddress_id());
          rp4.addView(tempButton, params);
        }


      }
    }

    Dialog dialog = new Dialog(getActivity(), com.tl.tplus.R.style.PersonDialogFragment);
    dialog.setContentView(view);
    dialog.setCanceledOnTouchOutside(true);
    return dialog;
  }

  @Override
  public void onResume() {
    super.onResume();

  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.filter_back:
        dismiss();
        break;

      case R.id.filter_done:
        TextView tv1 = (TextView) rp1.findViewById(rp1.getCheckedRadioButtonId());
        TextView tv2 = (TextView) rp2.findViewById(rp2.getCheckedRadioButtonId());
        TextView tv3 = (TextView) rp3.findViewById(rp3.getCheckedRadioButtonId());
        TextView tv4 = (TextView) rp4.findViewById(rp4.getCheckedRadioButtonId());

        Map<String, String> map = new HashMap<>();
        String select1 = tv1 != null ? (String) tv1.getTag() : null;
        String select2 = tv2 != null ? (String) tv2.getTag() : null;
        String select3 = tv3 != null ? (String) tv3.getTag() : null;
        String select4 = tv4 != null ? (String) tv4.getTag() : null;
        map.put("interests", select1);
        map.put("money", select2);
        map.put("time", select3);
        map.put("address", select4);
        listener.showFilterList(map);
        dismiss();

        break;
    }
  }


  public interface FilterDialogFragmentListener {
    void showFilterList(Map<String, String> selectedData);
  }


}
