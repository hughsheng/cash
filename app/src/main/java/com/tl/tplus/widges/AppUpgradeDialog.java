package com.tl.tplus.widges;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.tl.tplus.R;
import com.tl.tplus.home.mvp.UpdateBean;
import com.tl.tplus.util.ActivityManager;
import com.tl.tplus.util.IntentUtil;
import com.tl.tplus.util.SharedPreferencesUtils;

/**
 * Created by Think on 2017/11/21.
 */

public class AppUpgradeDialog implements View.OnClickListener {

    private Context context;
    private Dialog dialog;
    private Display display;

    private UpdateBean appVersion;
    private int app_type;

    private TextView tv_title_app_upgrade, tv_version_app_upgrade, tv_hint_app_upgrade,
            tv_content_app_upgrade, tv_left_app_upgrade, tv_right_app_upgrade;

    public AppUpgradeDialog(Context context, UpdateBean appVersion) {
        this.context = context;
        this.appVersion = appVersion;
        this.app_type = appVersion.getData().getF_type();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public AppUpgradeDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(R.layout.layout_app_upgrade, null);
        // 设置Dialog最小宽度为屏幕宽度
        view.setMinimumWidth(display.getWidth());
        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.ActionSheetDialogSweetStyle);
        dialog.setContentView(view);
//        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        // 初始化View
        initView(view);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = 0;
        dialogWindow.setAttributes(lp);
        return this;
    }

    private void initView(View view) {
        tv_title_app_upgrade = view.findViewById(R.id.tv_title_app_upgrade);
        tv_version_app_upgrade = view.findViewById(R.id.tv_version_app_upgrade);
        tv_hint_app_upgrade = view.findViewById(R.id.tv_hint_app_upgrade);
        tv_content_app_upgrade = view.findViewById(R.id.tv_content_app_upgrade);
        tv_left_app_upgrade = view.findViewById(R.id.tv_left_app_upgrade);
        tv_right_app_upgrade = view.findViewById(R.id.tv_right_app_upgrade);

        app_type = appVersion.getData().getF_type();
        String app_version = appVersion.getData().getF_version_no();
        String app_content = appVersion.getData().getF_desc();
        if (!TextUtils.isEmpty(app_version)) {
            tv_version_app_upgrade.setText(app_version);
        }
        if (!TextUtils.isEmpty(app_content)) {
            tv_content_app_upgrade.setText(app_content);
        }

        switch (app_type) {
            // 强制更新
            case 1:
                dialog.setCancelable(false);
                tv_hint_app_upgrade.setVisibility(View.VISIBLE);
                tv_left_app_upgrade.setText(R.string.text_exit);
                tv_right_app_upgrade.setText(R.string.text_update);
                break;

            // 选择更新
            case 2:
                tv_hint_app_upgrade.setVisibility(View.GONE);
                tv_left_app_upgrade.setText(R.string.text_ignore);
                tv_right_app_upgrade.setText(R.string.text_update);
                break;

            // 检测更新
            case 3:
                tv_hint_app_upgrade.setVisibility(View.GONE);
                tv_left_app_upgrade.setText(R.string.text_ignore);
                tv_right_app_upgrade.setText(R.string.text_update);
                break;
        }

        tv_left_app_upgrade.setOnClickListener(this);
        tv_right_app_upgrade.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.tv_left_app_upgrade: {
                if (app_type == 1) {
                    ActivityManager.getAppManager().AppExit(context);
                } else {
                    dismiss();
                }
            }
            break;

            case R.id.tv_right_app_upgrade: {
                String gpUlr = "https://play.google.com/store/apps/details?id=com.tl.bostunai";
                IntentUtil.startMarketView(context, gpUlr);
            }
            break;
        }
    }

    public void dismiss() {
        dialog.dismiss();
    }

    public void show() {
        if (!isShowing()) {
            SharedPreferencesUtils sharedPreferencesUtils = SharedPreferencesUtils.getInstance(context);
            String time = (String) sharedPreferencesUtils.getData(SharedPreferencesUtils.SP_NAME, "upgrade_time", "0");
            String currentTime = getTimeMillis();

            long current = Long.parseLong(currentTime);
            long save = Long.parseLong(time);
            long delay = timeDelayed(current, save);

            if (delay >= (60 * 24)) {
                dialog.show();
                sharedPreferencesUtils.saveData(SharedPreferencesUtils.SP_NAME, "upgrade_time", currentTime);
            }
        }
    }

    /**
     * 获取当前时间戳
     *
     * @return
     */
    private String getTimeMillis() {
        long time = System.currentTimeMillis();//获取系统时间的10位的时间戳 / 1000
        String str = String.valueOf(time);
        return str;
    }


    private Long timeDelayed(long time1, long time2) {
        return (time1 - time2) / (1000 * 60);
    }

    public boolean isShowing() {
        return dialog.isShowing();
    }

}
