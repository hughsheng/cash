package com.tl.tplus.dialogfragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.tl.tplus.home.adapter.CashTypeAdapter;
import com.tl.tplus.home.mvp.HomeBannerBean;
import com.tl.tplus.util.ConstanceValue;
import com.lfp.lfp_base_recycleview_library.itemdecora.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by sztangli on 2018-1-2.
 */

public class CashTyeDialogFragment extends BottomSheetDialogFragment {

    public static final String TAG = "CashTyeDialogFragment";
    private CashTyeDialogFragmentListener mListener;


    public static CashTyeDialogFragment newInstance() {

        Bundle args = new Bundle();
        CashTyeDialogFragment fragment = new CashTyeDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(params);
        window.setWindowAnimations(com.tl.tplus.R.style.anim_dialog_city);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(com.tl.tplus.R.layout.dialog_cash_layout, null);
        RecyclerView cash_type_recycleview = view.findViewById(com.tl.tplus.R.id.cash_type_recycleview);
        cash_type_recycleview.setLayoutManager(new LinearLayoutManager(getContext()));
        if (ConstanceValue.TYPEBEAN != null) {

            List<HomeBannerBean.DataBean.FilterWordsBean> list=new ArrayList<>();
            for(HomeBannerBean.DataBean.FilterWordsBean bean:ConstanceValue.TYPEBEAN){
                if(!bean.getFunction().equals("getRapidLoan")){
                    list.add(bean);
                }
            }

            CashTypeAdapter cashTypeAdapter = new CashTypeAdapter(getContext(),list);
            cash_type_recycleview.setAdapter(cashTypeAdapter);
            cash_type_recycleview.addItemDecoration(new RecycleViewDivider(getContext(),
                LinearLayoutManager.HORIZONTAL, 4, Color.rgb(250, 250, 250)));
            cashTypeAdapter.setOnItemClickListener(new CashTypeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(String fw_id, String fw_type,String name) {
                    mListener.changeCashType(fw_id,fw_type,name);
                    dismiss();
                }
            });
        }

        BottomSheetDialog sheetDialog = new BottomSheetDialog(getActivity(), com.tl.tplus.R.style.PersonDialogFragment);
        sheetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        sheetDialog.setContentView(view);
        sheetDialog.setCanceledOnTouchOutside(true);

        return sheetDialog;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (CashTyeDialogFragmentListener) context;
    }


    public interface CashTyeDialogFragmentListener {
        void changeCashType(String fw_id, String fw_type, String name);
    }

}
