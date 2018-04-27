package com.tl.tplus.detail;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.tl.tplus.R;
import com.tl.tplus.base.api.bean.ErrorBean;
import com.tl.tplus.base.api.bean.RequestBean;
import com.tl.tplus.base.fragment.BaseFragment;
import com.tl.tplus.cicidetail.CicidetailActivity;
import com.tl.tplus.detail.adapter.DetailInfoAdapter;
import com.tl.tplus.detail.adapter.DetailInformasiAdapter;
import com.tl.tplus.detail.adapter.DetailProsesAdapter;
import com.tl.tplus.detail.adapter.DetailReviewAdapter;
import com.tl.tplus.detail.adapter.DetailTipAdapter;
import com.tl.tplus.detail.mvp.DetailBean;
import com.tl.tplus.detail.mvp.DetailContract;
import com.tl.tplus.detail.mvp.DetailProsesBean;
import com.tl.tplus.detail.mvp.DetailReviewBean;
import com.tl.tplus.detailinfo.DetailInfoActivity;
import com.tl.tplus.detailinfo.DetailInfoFragment;
import com.tl.tplus.util.CommonUtil;
import com.tl.tplus.util.ConstanceValue;
import com.tl.tplus.util.EventTrackUtil;
import com.tl.tplus.util.IntentUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.picker.SinglePicker;

/**
 * Created by Administrator on 2018/3/2.
 */

public class DetailComFragment extends BaseFragment implements DetailContract.IView, View
        .OnClickListener, NestedScrollView.OnScrollChangeListener {

    @BindView(R.id.scollView)
    protected NestedScrollView scollView;
    @BindView(R.id.detail_icon)
    ImageView detail_icon;
    @BindView(R.id.detail_title)
    TextView detail_title;
    @BindView(R.id.detail_score)
    TextView detail_score;
    @BindView(R.id.detail_see_detail)
    TextView detail_see_detail;
    @BindView(R.id.detail_description1)
    TextView detail_description1;
    @BindView(R.id.detail_info_list)
    RecyclerView detail_info_list;
    @BindView(R.id.calculator_layout)
    LinearLayout calculator_layout;
    @BindView(R.id.detail_tip_layout)
    RelativeLayout detail_tip_layout;
    @BindView(R.id.tip_img)
    ImageView tip_img;
    @BindView(R.id.tip_title)
    TextView tip_title;
    @BindView(R.id.proses_list)
    RecyclerView proses_list;
    @BindView(R.id.informasi_list)
    RecyclerView informasi_list;
    @BindView(R.id.komentar_list)
    RecyclerView komentar_list;
    @BindView(R.id.download_text)
    TextView download_text;
    @BindView(R.id.detail_tip_list)
    RecyclerView detail_tip_list;
    @BindView(R.id.borrow_type)
    TextView borrow_type;
    @BindView(R.id.borrow_price)
    TextView borrow_price;
    @BindView(R.id.borrow_time)
    TextView borrow_time;
    @BindView(R.id.pen_txt)
    TextView totalPay_text;
    @BindView(R.id.pin_txt)
    TextView actualPrice_text;
    @BindView(R.id.bun_txt)
    TextView totalInterest_text;
    @BindView(R.id.guaid1_layout)
    RelativeLayout guaid1_layout;
    @BindView(R.id.guaid2_layout)
    LinearLayout guaid2_layout;
    @BindView(R.id.guaid3_layout)
    LinearLayout guaid3_layout;
    @BindView(R.id.dana_info_layout)
    LinearLayout dana_info_layout;

    public static final String TAG = "CicidetailComFragment";
    public static final String PID = "pid";
    public static final String TYPE = "type";
    private static final String DEFAULTTYPE = "0";
    public static final int DOWN = 1111;
    private String webUrl;
    private String pid;
    private String name;
    private String currentType;
    private String currentPrice;
    private String currentTime;
    private DetailBean.DataBean dataBean;
    private List<String> priceList, timeList = new ArrayList<>();
    public static final String FREE = "free";
    public static final String FREETYPE = "free_type";
    public static final String RATE = "rate";
    public static final String RATEUNIT = "rate_unit";
    public static final int FREEPERCET = 1;
    public static final int FREENUM = 2;
    private DetailListener listener;
    private LinearLayoutManager layoutManager;
    private int installType;
    private int cap_status;

    @Inject
    DetailContract.Presenter mPresenter;

    public static DetailComFragment newInstance(int pid, int type) {

        Bundle args = new Bundle();
        args.putInt(PID, pid);
        args.putInt(TYPE, type);
        DetailComFragment fragment = new DetailComFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (DetailListener) context;
    }

    @Override
    public void onLogicPresenter() {
        RequestBean requestBean = JSON.parseObject(ConstanceValue.COMMREQUESTBEAN, RequestBean.class);
        requestBean.setPid(getArguments().getInt(PID));
        String sign = CommonUtil.RequestSignData(requestBean);
        requestBean.setSign(sign);
        requestBean.setTimestamp(System.currentTimeMillis() / 1000);
        String data = JSON.toJSONString(requestBean);
        mPresenter.getDetailData(data);
        mPresenter.getProsesListData(data);
        mPresenter.getReviewListData(data);
    }


    @Override
    public int getLayoutResId() {
        return R.layout.detail_layout;
    }

    @Override
    public void setPresenter(@NonNull DetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onLoadFail(ErrorBean errorBean) {

    }

    @Override
    public void showUi(final DetailBean detailBean) {
        scollView.setOnScrollChangeListener(this);
        dataBean = detailBean.getData();
        name = dataBean.getProduct_name();
        installType = getArguments().getInt(TYPE);
        cap_status = detailBean.getData().getCap_status();
        if (installType == DOWN) {
            final PackageManager packageManager = getContext().getPackageManager();
            download_text.setText("Buka");
            download_text.setClickable(true);
            download_text.setBackground(getResources().getDrawable(R.drawable.side_nav_bar));
            download_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    statisticsClick("2");
                    Intent intent = packageManager.getLaunchIntentForPackage(detailBean.getData()
                            .getPackage_name());
                    getActivity().startActivity(intent);
                }
            });
        } else {
            download_text.setOnClickListener(this);
            if (detailBean.getData().getCap_status() == 1) {
                download_text.setText("Sudah mencapai limit harian");
                download_text.setBackgroundColor(Color.GRAY);
                download_text.setClickable(false);
            } else {
                download_text.setText("DonwLoad dan Pinjam dari " + name);
                download_text.setBackground(getResources().getDrawable(R.drawable.side_nav_bar));
                download_text.setClickable(true);
            }

        }

        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        if (dataBean.getActivity_list() != null) {
            webUrl = dataBean.getActivity_list().get(0).getUrl();
        }
        pid = dataBean.getPid();
        detail_see_detail.setOnClickListener(this);
        detail_tip_layout.setOnClickListener(this);
        Glide.with(getContext())
                .load(dataBean.getIcon())
                .error(R.mipmap.item_icon)
                .placeholder(R.mipmap.item_icon)
                .into(detail_icon);
        detail_title.setText(name);
        DecimalFormat df = new DecimalFormat("#.0");
        detail_score.setText(df.format(Float.valueOf(dataBean.getTotal_score())));
        String l1 = df.format(Float.valueOf(dataBean.getPass_rate_score()));
        String l2 = df.format(Float.valueOf(dataBean.getSpeed_score()));
        String l3 = df.format(Float.valueOf(dataBean.getDunning_score()));

        detail_description1.setText("Disetujui " + l1 + "   Kecepatan "
                + l2 + "   Penagihan " + l3);
        if (dataBean.getDetails_info() != null) {
            DetailInfoAdapter detailInfoAdapter = new DetailInfoAdapter(getContext(), dataBean
                    .getDetails_info());
            detail_info_list.setAdapter(detailInfoAdapter);
            detail_info_list.setLayoutManager(new LinearLayoutManager(getContext()));
        } else {
            detail_see_detail.setVisibility(View.GONE);
        }

        if (dataBean.getActivity_list() != null) {
            Glide.with(getContext())
                    .load(dataBean.getActivity_list().get(0).getIcon())
                    .error(R.mipmap.item_icon)
                    .placeholder(R.mipmap.item_icon)
                    .into(tip_img);
            tip_title.setText(dataBean.getActivity_list().get(0).getTitle());

            DetailTipAdapter detailTipAdapter = new DetailTipAdapter(getContext(), dataBean
                    .getActivity_list().get(0).getTags());
            detail_tip_list.setAdapter(detailTipAdapter);
            detail_tip_list.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView
                    .HORIZONTAL, true));
        } else {
            detail_tip_layout.setVisibility(View.GONE);
        }


        if (dataBean.getBorrowing_type() != null && dataBean.getBorrowing_type().size() > 0) {

            //获取类型列表,设置默认第一个为类型
            final String firstTypeData = dataBean.getBorrowing_type().get(0);
            currentType = firstTypeData;
            borrow_type.setText(firstTypeData);

            //获取价格列表,设置默认价格最小值
            priceList = mPresenter.getBorrowPriceList(currentType, dataBean.getPrice());
            if (priceList.size() > 0) {
                currentPrice = priceList.get(0).replace(".", "").substring(3, priceList.get(0).replace("" +
                        ".", "").length());
                borrow_price.setText("Rp " + CommonUtil.getSortPrice(currentPrice));
            }


            //获取时间列表，设置默认时间最小值
            getTimeList();
            if (timeList.size() > 0) {
                currentTime = timeList.get(0).substring(0, timeList.get(0).length() - 5);
                borrow_time.setText(timeList.get(0));
            }

            //计算初始状态结果
            showResult();

            //打开选择器
            borrow_type.setOnClickListener(this);

            borrow_price.setOnClickListener(this);

            borrow_time.setOnClickListener(this);

        } else {
            //隐藏类型选择器
            borrow_type.setVisibility(View.GONE);
            currentType = DEFAULTTYPE;
            //获取价格列表,设置默认价格最小值
            priceList = mPresenter.getBorrowPriceList(currentType, dataBean.getPrice());
            if (priceList.size() > 0) {
                currentPrice = priceList.get(0).replace(".", "").substring(3, priceList.get(0).replace("" +
                        ".", "").length());
                borrow_price.setText("Rp " + CommonUtil.getSortPrice(currentPrice));
            }

            //获取时间列表,设置默认时间最小值
            getTimeList();
            if (timeList.size() > 0) {
                currentTime = timeList.get(0).substring(0, timeList.get(0).length() - 5);
                borrow_time.setText(timeList.get(0));
            }

            //计算初始状态结果
            showResult();

            //打开选择器
            borrow_price.setOnClickListener(this);

            borrow_time.setOnClickListener(this);
        }
    }

    /**
     * 获取时间列表
     */
    private void getTimeList() {
        Map<String, Integer> map = mPresenter.getBorrowTime(currentType, currentPrice, dataBean
                .getPrice());
        timeList.clear();

        if (map.size() > 0) {
            if (map.get(ConstanceValue.TIMETYPE) == 0) {//
                int min = map.get(ConstanceValue.TIME_MIN);
                int max = map.get(ConstanceValue.TIME_MAX);
                for (int i = min; i <= max; i++) {
                    timeList.add(i + " Hari");
                }
            } else if (map.get(ConstanceValue.TIMETYPE) == 1) {
                for (int i = 0; i < map.size() - 1; i++) {
                    timeList.add(map.get(i + "") + " Hari");
                }
            }
        } else {
            showToastTip("Tidak ada jenis waktu seperti itu");
        }

    }

    private SinglePicker getSinglePicker(int selectedColor, int unSelectedColor, List<String>
            dataList) {
        SinglePicker<String> picker = new SinglePicker<String>(getActivity(), dataList);
        picker.setCanLoop(false);//不禁用循环
        picker.setLineVisible(true);
        picker.setTextSize(18);
        picker.setSelectedIndex(8);
        picker.setWheelModeEnable(false);
        picker.setCancelText("Batal");
        picker.setSubmitText("Konfirmasi");
        //启用权重 setWeightWidth 才起作用
        //  picker.setLabel("分");
        picker.setWeightEnable(true);
        picker.setWeightWidth(1);
        picker.setSelectedTextColor(selectedColor);//前四位值是透明度
        picker.setUnSelectedTextColor(unSelectedColor);
        return picker;
    }


    /**
     * 滑动到导航位置
     */
    public void scrollToGuaid(int item) {
        if (item == CicidetailActivity.GUAIDITEM1) {
            scollView.scrollTo(0, 0);
        } else if (item == CicidetailActivity.GUAIDITEM2) {
            scollView.scrollTo(0, guaid2_layout.getTop());
        } else if (item == CicidetailActivity.GUAIDITEM3) {
            scollView.scrollTo(0, guaid3_layout.getTop());
        }
    }

    @Override
    public void hideLoading() {
        dismiss();
    }

    @Override
    public void showLoading() {
        show(getString(R.string.waiting));
    }

    @Override
    public void showPrses(DetailProsesBean prosesBean) {
        if (prosesBean.getData().getProcedure() != null && prosesBean.getData().getProcedure().size()
                > 0) {
            DetailProsesAdapter prosesAdapter = new DetailProsesAdapter(getContext(), prosesBean.getData
                    ().getProcedure());
            proses_list.setLayoutManager(layoutManager);
            proses_list.setHasFixedSize(true);
            proses_list.setNestedScrollingEnabled(false);
            proses_list.setAdapter(prosesAdapter);
            proses_list.setLayoutManager(new LinearLayoutManager(getContext()));
        } else {
            guaid2_layout.setVisibility(View.GONE);
        }

        if (prosesBean.getData().getAudit_info() != null && prosesBean.getData().getAudit_info().size
                () > 0) {
            DetailInformasiAdapter informasiAdapter = new DetailInformasiAdapter(getContext(), prosesBean
                    .getData().getAudit_info());
            informasi_list.setLayoutManager(layoutManager);
            informasi_list.setHasFixedSize(true);
            informasi_list.setNestedScrollingEnabled(false);
            informasi_list.setAdapter(informasiAdapter);
            informasi_list.setLayoutManager(new LinearLayoutManager(getContext()));
        } else {
            dana_info_layout.setVisibility(View.GONE);
        }

    }

    @Override
    public void showReview(DetailReviewBean reviewBean) {
        DetailReviewAdapter reviewAdapter = new DetailReviewAdapter(getContext(), reviewBean.getData
                ().getUser_review());
        komentar_list.setLayoutManager(layoutManager);
        komentar_list.setHasFixedSize(true);
        komentar_list.setNestedScrollingEnabled(false);
        komentar_list.setAdapter(reviewAdapter);
        komentar_list.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void showResult() {
        EventTrackUtil.trackEventApp(EventTrackUtil.BOSTUNAI_DETAILS_CALCULATOR_CLICK);
        Map<String, String> dataMap = mPresenter.getAlgorithm(currentType, currentPrice, currentTime,
                dataBean.getInterest_algorithm());
        if (dataMap != null) {
            String free = dataMap.get(FREE);
            String freeType = dataMap.get(FREETYPE);
            String rate = dataMap.get(RATE);
            String rateUnit = dataMap.get(RATEUNIT);

            float freeNum = mPresenter.getTotalFree(currentPrice, free, freeType);//手续费
            float rateNum = mPresenter.getRate(currentPrice, rate, currentTime, rateUnit);//利息

            float totalInterest = freeNum + rateNum;//总利息
            float totalPay = Float.valueOf(currentPrice) + rateNum;//应还总额
            float actualPrice = Float.valueOf(currentPrice) - freeNum;//实际到款金额

            totalPay_text.setText("Rp " + CommonUtil.getSortPrice((int) totalPay + ""));
            actualPrice_text.setText("Rp " + CommonUtil.getSortPrice((int) actualPrice + ""));
            totalInterest_text.setText("Rp " + CommonUtil.getSortPrice((int) totalInterest + ""));
        } else {
            showToastTip("Data tidak normal");
        }

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.detail_see_detail:
                EventTrackUtil.trackEventApp(EventTrackUtil.BOSTUNAI_DETAILS_MORE_DETAILS_CLICK);
                intent = new Intent(getActivity(), DetailInfoActivity.class);
                intent.putExtra(DetailInfoFragment.PID, pid);
                intent.putExtra(DetailInfoFragment.NAME, name);
                startActivity(intent);
                break;

            case R.id.detail_tip_layout:
                EventTrackUtil.trackEventApp(EventTrackUtil.BOSTUNAI_DETAILS_TIPS_CLICK);
                intent = new Intent(getActivity(), DetailsWebActivity.class);
                intent.putExtra(DetailsWebFragment.WEBURL, webUrl);
                intent.putExtra(DetailsWebFragment.NAME, name);
                intent.putExtra(DetailsWebFragment.PACKEGENAME, dataBean.getPackage_name());
                intent.putExtra(DetailsWebFragment.DOWNLOADURL, dataBean.getJump_url());
                intent.putExtra(DetailsWebFragment.INSTALL, installType);
                intent.putExtra(DetailsWebFragment.CAPSTATUS, cap_status);
                intent.putExtra(DetailsWebFragment.PID, pid);
                intent.putExtra(DetailsWebFragment.TYPE, "1");
                startActivity(intent);
                break;

            case R.id.borrow_type:
                SinglePicker typePicker = getSinglePicker(Color.BLACK, Color.GRAY, dataBean
                        .getBorrowing_type());
                typePicker.setItemWidth(500);
                typePicker.setOnItemPickListener(new OnItemPickListener<String>() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        currentType = item;
                        borrow_type.setText(item);
                        //更新价格和时间列表
                        priceList = mPresenter.getBorrowPriceList(currentType, dataBean.getPrice());
                        String prices = priceList.get(0).replace(".", "");
                        currentPrice = prices.substring(3, prices.length());
                        borrow_price.setText("Rp " + CommonUtil.getSortPrice(Integer.valueOf(currentPrice) + ""));
                        getTimeList();
                        currentTime = timeList.get(0).substring(0, timeList.get(0).length() - 5);
                        borrow_time.setText(currentTime + " Hari");
                        showResult();
                    }
                });
                typePicker.setSelectedIndex(0);
                typePicker.show();
                break;

            case R.id.borrow_price:
                SinglePicker pricePicker = getSinglePicker(Color.BLACK, Color.GRAY, priceList);
                pricePicker.setItemWidth(500);
                pricePicker.setOnItemPickListener(new OnItemPickListener<String>() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        currentPrice = item.replace(".", "").substring(3, item.replace(".", "").length());
                        float pricec = Float.valueOf(currentPrice);
                        borrow_price.setText("Rp " + CommonUtil.getSortPrice((int) pricec + ""));
                        //更新时间列表
                        getTimeList();
                        currentTime = timeList.get(0).substring(0, timeList.get(0).length() - 5);
                        borrow_time.setText(currentTime + " Hari");
                        showResult();
                    }
                });
                pricePicker.setSelectedIndex(0);
                pricePicker.show();
                break;

            case R.id.borrow_time:
                SinglePicker timePicker = getSinglePicker(Color.BLACK, Color.GRAY, timeList);
                timePicker.setItemWidth(500);
                timePicker.setOnItemPickListener(new OnItemPickListener<String>() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        currentTime = item.substring(0, item.length() - 5);
                        borrow_time.setText(item);
                        showResult();
                    }
                });
                timePicker.setSelectedIndex(0);
                timePicker.show();
                break;

            case R.id.download_text:
                String app_google_play_pre_url = "https://play.google.com/store/apps/details";
                String jump_url = dataBean.getJump_url();
                if (null != jump_url && jump_url.contains(app_google_play_pre_url)) {
                    String pkgName = IntentUtil.getQueryParam(jump_url);
                    String gpUlr = IntentUtil.getGooglePlayStoreUrl(getActivity(), pkgName);
                    IntentUtil.startMarketView(getActivity(), gpUlr);
                } else {
                    String title = null == dataBean ? "" : dataBean.getProduct_name();
                    intent = new Intent(getActivity(), JumpWebActivity.class);
                    intent.putExtra(JumpWebActivity.JUMPURL, jump_url);
                    intent.putExtra(JumpWebActivity.NAME, title);
                    startActivity(intent);
//                    CommonWebActivity.startAction(CashReditDetailsActivity.this, title, jump_url);
                }
//                if (CommonUtil.isMobile_spExist(getContext())) {
//                    final String GOOGLE_PLAY = "com.android.vending";//这里对应的是谷歌商店，跳转别的商店改成对应的即可
//                    try {
//                        if (TextUtils.isEmpty(dataBean.getPackage_name()))
//                            return;
//                        Uri uri = Uri.parse("market://details?id=" + dataBean.getPackage_name());
//                        Intent jumpIntent = new Intent(Intent.ACTION_VIEW, uri);
//                        jumpIntent.setPackage(GOOGLE_PLAY);
//                        jumpIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        getActivity().startActivity(jumpIntent);
//                    } catch (Exception e) {
//
//                    }
//                } else {
//                    Uri uri = Uri.parse(dataBean.getJump_url());
//                    Intent downLoadIntent = new Intent(Intent.ACTION_VIEW, uri);
//                    startActivity(downLoadIntent);
//                }
                statisticsClick("1");
                EventTrackUtil.trackEventApp(EventTrackUtil.BOSTUNAI_DETAILS_DOWNLOAD_CASHCREDIT);
                EventTrackUtil.trackEventApp(EventTrackUtil.BOSTUNAI_DETAILS_BUTTON_BORROW_CLICK);
                break;
        }
    }

    /**
     * 点击统计
     */
    private void statisticsClick(String click_type) {
        RequestBean requestBean = JSON.parseObject(ConstanceValue.COMMREQUESTBEAN, RequestBean.class);
        requestBean.setPid(getArguments().getInt(PID));
        requestBean.setProduct_type("1");
        requestBean.setClick_type(click_type);
        String sign = CommonUtil.RequestSignData(requestBean);
        requestBean.setSign(sign);
        requestBean.setTimestamp(System.currentTimeMillis() / 1000);
        String data = JSON.toJSONString(requestBean);
        mPresenter.productStatistics(data);
    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int
            oldScrollY) {
        boolean isSelected;
        if (scrollY >= 0 && scrollY <= guaid1_layout.getBottom()) {
            EventTrackUtil.trackEventApp(EventTrackUtil.BOSTUNAI_DETAILS_TAB_DETAIL_CLICK);
            isSelected = listener.getSelecteState(CicidetailActivity.GUAIDITEM1);
            if (!isSelected) {
                listener.setGuaidSelected(CicidetailActivity.GUAIDITEM1, CicidetailActivity.GUAIDITEMD1);
            }
        } else if (scrollY >= guaid2_layout.getTop() && scrollY <=
                guaid2_layout.getBottom()) {
            EventTrackUtil.trackEventApp(EventTrackUtil.BOSTUNAI_DETAILS_TAB_FLOW_CLICK);
            isSelected = listener.getSelecteState(CicidetailActivity.GUAIDITEM2);
            if (!isSelected) {
                listener.setGuaidSelected(CicidetailActivity.GUAIDITEM2, CicidetailActivity.GUAIDITEMD2);
            }
        } else if (scrollY >= guaid3_layout.getTop() && scrollY <=
                guaid3_layout.getBottom()) {
            EventTrackUtil.trackEventApp(EventTrackUtil.BOSTUNAI_DETAILS_TAB_COMMENT_CLICK);
            isSelected = listener.getSelecteState(CicidetailActivity.GUAIDITEM3);
            if (!isSelected) {
                listener.setGuaidSelected(CicidetailActivity.GUAIDITEM3, CicidetailActivity.GUAIDITEMD3);
            }
        }
    }

    interface DetailListener {
        boolean getSelecteState(int item);//获取按钮选中状态

        void setGuaidSelected(int item, int itemDerector);//设置按钮选中
    }

}
