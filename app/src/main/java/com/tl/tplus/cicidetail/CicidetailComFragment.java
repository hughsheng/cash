package com.tl.tplus.cicidetail;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.tl.tplus.R;
import com.tl.tplus.base.api.bean.ErrorBean;
import com.tl.tplus.base.api.bean.RequestBean;
import com.tl.tplus.base.fragment.BaseFragment;
import com.tl.tplus.cicidetail.adapter.CiciDetailInfoAdapter;
import com.tl.tplus.cicidetail.adapter.CicidetailInformasiAdapter;
import com.tl.tplus.cicidetail.adapter.CicidetailProsesAdapter;
import com.tl.tplus.cicidetail.adapter.CicidetailReviewAdapter;
import com.tl.tplus.cicidetail.mvp.CicidetailBean;
import com.tl.tplus.cicidetail.mvp.CicidetailContract;
import com.tl.tplus.cicidetail.mvp.CicidetailPresenter;
import com.tl.tplus.cicidetail.mvp.CicidetailProsesBean;
import com.tl.tplus.cicidetail.mvp.CicidetailReviewBean;
import com.tl.tplus.cicidetailinfo.CicidetailInfoActivity;
import com.tl.tplus.cicidetailinfo.CicidetailInfoFragment;
import com.tl.tplus.detail.JumpWebActivity;
import com.tl.tplus.util.CommonUtil;
import com.tl.tplus.util.ConstanceValue;
import com.tl.tplus.util.EventTrackUtil;
import com.tl.tplus.util.IntentUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.picker.SinglePicker;

/**
 * Created by Administrator on 2018/3/2.
 */

public class CicidetailComFragment extends BaseFragment implements CicidetailContract.IView, View
        .OnClickListener, NestedScrollView.OnScrollChangeListener {


    @BindView(com.tl.tplus.R.id.scollView)
    protected NestedScrollView scollView;
    @BindView(com.tl.tplus.R.id.detail_icon)
    ImageView detail_icon;
    @BindView(com.tl.tplus.R.id.detail_title)
    TextView detail_title;
    @BindView(com.tl.tplus.R.id.detail_score)
    TextView detail_score;
    @BindView(com.tl.tplus.R.id.detail_see_detail)
    TextView detail_see_detail;
    @BindView(com.tl.tplus.R.id.detail_description1)
    TextView detail_description1;
    @BindView(com.tl.tplus.R.id.detail_info_list)
    RecyclerView detail_info_list;
    @BindView(com.tl.tplus.R.id.calculator_layout3)
    LinearLayout calculator_layout3;
    @BindView(com.tl.tplus.R.id.calculator_layout4)
    LinearLayout calculator_layout4;

    @BindView(com.tl.tplus.R.id.proses_list)
    RecyclerView proses_list;
    @BindView(com.tl.tplus.R.id.informasi_list)
    RecyclerView informasi_list;
    @BindView(com.tl.tplus.R.id.komentar_list)
    RecyclerView komentar_list;
    @BindView(com.tl.tplus.R.id.download_text)
    TextView download_text;
    @BindView(com.tl.tplus.R.id.borrow_rate)
    TextView borrow_rate;
    @BindView(com.tl.tplus.R.id.borrow_price)
    EditText borrow_price;
    @BindView(com.tl.tplus.R.id.borrow_time)
    TextView borrow_time;
    @BindView(com.tl.tplus.R.id.borrow_typeg)
    TextView borrow_typeg;
    @BindView(com.tl.tplus.R.id.borrow_rateg)
    TextView borrow_rateg;
    @BindView(com.tl.tplus.R.id.borrow_priceg)
    EditText borrow_priceg;
    @BindView(com.tl.tplus.R.id.borrow_timeg)
    TextView borrow_timeg;
    @BindView(com.tl.tplus.R.id.pen_txt)
    TextView totalPay_text;
    @BindView(com.tl.tplus.R.id.pin_txt)
    TextView actualPrice_text;
    @BindView(com.tl.tplus.R.id.bun_txt)
    TextView totalInterest_text;
    @BindView(com.tl.tplus.R.id.guaid1_layout)
    RelativeLayout guaid1_layout;
    @BindView(com.tl.tplus.R.id.guaid2_layout)
    LinearLayout guaid2_layout;
    @BindView(com.tl.tplus.R.id.guaid3_layout)
    LinearLayout guaid3_layout;
    @BindView(R.id.cici_info_layout)
    LinearLayout cici_info_layout;
    public static final String TAG = "CicidetailComFragment";
    public static final String PID = "pid";
    private static final String DEFAULTRATE = "0.00";
    public static final String TYPE = "type";
    public static final int DOWN = 1111;
    private String webUrl;
    private String pid;
    private String name;
    private String currentType = "0";
    private String currentPrice;
    private String currentTime;
    private String currentRate;
    private CicidetailBean.DataBean dataBean;
    private List<String> firstRateList = new ArrayList<>(), timeList = new ArrayList<>(), typeList,
            priceMinList = new
                    ArrayList<>(),
            priceMaxList = new ArrayList<>();
    private Map<String, String> priceArea;
    private CiciDetailListener listener;
    private LinearLayoutManager layoutManager;
    private int installType;
    private String price_min, price_max;

    public static final int FREEPERCET = 1;
    public static final int FREENUM = 2;

    @Inject
    CicidetailContract.Presenter mPresenter;

    public static CicidetailComFragment newInstance(int pid, int type) {
        Bundle args = new Bundle();
        args.putInt(PID, pid);
        args.putInt(TYPE, type);
        CicidetailComFragment fragment = new CicidetailComFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (CiciDetailListener) context;
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
        return com.tl.tplus.R.layout.cicidetail_layout;
    }

    @Override
    public void setPresenter(@NonNull CicidetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onLoadFail(ErrorBean errorBean) {

    }

    @Override
    public void showUi(final CicidetailBean cicidetailBean) {
        scollView.setOnScrollChangeListener(this);
        dataBean = cicidetailBean.getData();
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);

        if (dataBean.getActivity_list() != null) {
            //  webUrl = dataBean.getActivity_list().get(0).getUrl();
        }
        pid = dataBean.getPid();
        name = dataBean.getProduct_name();

        installType = getArguments().getInt(TYPE);
        if (installType == DOWN) {
            final PackageManager packageManager = getContext().getPackageManager();
            download_text.setText("Buka");
            download_text.setClickable(true);
            download_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    statisticsClick("2");
                    Intent intent = packageManager.getLaunchIntentForPackage(cicidetailBean.getData()
                            .getPackage_name());
                    getActivity().startActivity(intent);
                }
            });
        } else {
            download_text.setOnClickListener(this);
            if (cicidetailBean.getData().getCap_status() == 1) {
                download_text.setText("Sudah mencapai limit harian");
                download_text.setBackgroundColor(Color.GRAY);
                download_text.setClickable(false);
            } else {
                download_text.setText("DonwLoad dan Pinjam dari " + name);
                download_text.setClickable(true);
            }

        }

        detail_see_detail.setOnClickListener(this);
        Glide.with(getContext())
                .load(dataBean.getIcon())
                .error(com.tl.tplus.R.mipmap.item_icon)
                .placeholder(com.tl.tplus.R.mipmap.item_icon)
                .into(detail_icon);
        detail_title.setText(dataBean.getProduct_name());

        DecimalFormat df = new DecimalFormat("#.0");
        detail_score.setText(df.format(Float.valueOf(dataBean.getTotal_score())));
        String l1 = df.format(Float.valueOf(dataBean.getPass_rate_score()));
        String l3 = df.format(Float.valueOf(dataBean.getDunning_score()));
        detail_description1.setText("Disetujui " + l1 + "   Penagihan " +
                l3);

        if (dataBean.getDetails_info() != null) {
            CiciDetailInfoAdapter ciciDetailInfoAdapter = new CiciDetailInfoAdapter(getContext(), dataBean
                    .getDetails_info());
            detail_info_list.setAdapter(ciciDetailInfoAdapter);
            detail_info_list.setLayoutManager(new LinearLayoutManager(getContext()));
        } else {
            detail_see_detail.setVisibility(View.GONE);
        }

        download_text.setOnClickListener(this);

        if (dataBean.getBorrowing_type() != null && dataBean.getBorrowing_type().size() > 0) {
            //显示4个选择器布局
            calculator_layout4.setVisibility(View.VISIBLE);

            //获取分期贷类型集合,设置默认类型
            typeList = dataBean.getBorrowing_type();
            currentType = typeList.get(0);
            borrow_typeg.setText(currentType);

            if (dataBean.getPrice() == null || dataBean.getPrice().size() == 0) {
                //获取首付比率,设置最小首付比率
                firstRateList.addAll(mPresenter.getClearList(dataBean.getDown_payment_rate()));

                for (CicidetailBean.DataBean.InterestAlgorithmBean bean : dataBean.getInterest_algorithm
                        ()) {
                    if (bean.getNum().equals(currentType)) {
                        currentRate = bean.getDown_payment_rate();
                        break;
                    }
                }
                borrow_rateg.setText(currentRate + "%");

                //获取允许贷款的金额范围,设置最小贷款金额
                List<String> dirtTimeList = new ArrayList<>();
                for (CicidetailBean.DataBean.InterestAlgorithmBean bean : dataBean.getInterest_algorithm
                        ()) {
                    priceMinList.add(bean.getPrice_min());
                    priceMaxList.add(bean.getPrice_max());
                    dirtTimeList.add(bean.getTime_min());
                }
                price_min = mPresenter.getClearList(priceMinList).get(0);
                price_max = mPresenter.getClearList(priceMaxList).get(0);
                if (!price_min.equals("0")) {
                    currentPrice = price_min;
                    borrow_price.setText("Rp " + CommonUtil.getSortPrice(currentPrice));
                } else {
                    String priceR = borrow_price.getText().toString().replace(".", "");
                    currentPrice = priceR.substring(3, priceR.length());
                }

                //获取贷款时间,设置最短贷款时间
                for (String s : mPresenter.getClearList(dirtTimeList)) {
                    timeList.add(s + " Hari");
                }
                String mintime = timeList.get(0);
                currentTime = mintime.substring(0, mintime.length() - 5);
                borrow_timeg.setText(mintime);

            } else {

                //获取首付比率,设置最小首付比率
                getRateList();
                currentRate = firstRateList.get(0);
                borrow_rateg.setText(currentRate + "%");

                //获取允许贷款的金额范围,设置最小贷款金额
                getPriceArea();
                if (!price_min.equals("0")) {
                    currentPrice = price_min;
                    borrow_price.setText("Rp " + CommonUtil.getSortPrice(currentPrice));
                } else {
                    String priceR = borrow_price.getText().toString().replace(".", "");
                    currentPrice = priceR.substring(3, priceR.length());
                }

                //获取贷款时间,设置最短贷款时间
                getTimeList();
                String mintime = timeList.get(0);
                currentTime = mintime.substring(0, mintime.length() - 5);
                borrow_timeg.setText(mintime);
            }


            //根据选择的数据获取金额计算因子
            Map<String, String> dataMap = mPresenter.getAlgorithm(currentType, currentPrice,
                    currentTime,
                    currentRate, dataBean.getInterest_algorithm());

            //显示计算结果
            showResult(dataMap);

            //设置选择器
            borrow_typeg.setOnClickListener(this);

            borrow_rateg.setOnClickListener(this);

            borrow_priceg.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        EditText editText = (EditText) v;
                        editText.getText().clear();
                    }
                }
            });
            borrow_priceg.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.toString().contains("Rp") && s.length() > 3) {
                        currentPrice = s.toString().substring(3, s.length()).replace(".", "");
                    } else if (s.toString().contains("Rp") && s.length() == 3) {
                        s.clear();
                    } else {
                        currentPrice = s.toString().replace(".", "");
                    }
                }
            });
            borrow_priceg.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    //回车键
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        if (!price_min.equals("0") && Long.valueOf(currentPrice) < Long.valueOf(price_min)) {
                            currentPrice = price_min;
                        }
                        if (!price_max.equals("0") && Long.valueOf(currentPrice) > Long.valueOf(price_max)) {
                            currentPrice = price_max;
                        }

                    }
                    Map<String, String> dataMap = mPresenter.getAlgorithm(currentType, currentPrice,
                            currentTime, currentRate, dataBean.getInterest_algorithm());
                    borrow_priceg.setText("Rp " + CommonUtil.getSortPrice(currentPrice));
                    showResult(dataMap);
                    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context
                            .INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken()
                            , 0);
                    return true;
                }
            });

            borrow_timeg.setOnClickListener(this);

        } else {
            //显示3个选择器布局
            calculator_layout3.setVisibility(View.VISIBLE);

            if (dataBean.getPrice() == null || dataBean.getPrice().size() == 0) {

                //获取首付比率,设置最小首付比率
                firstRateList.addAll(mPresenter.getClearList(dataBean.getDown_payment_rate()));
                currentRate = firstRateList.get(0);
                borrow_rate.setText(currentRate + "%");

                //获取允许贷款的金额范围,设置最小贷款金额
                List<String> dirtTimeList = new ArrayList<>();
                for (CicidetailBean.DataBean.InterestAlgorithmBean bean : dataBean.getInterest_algorithm
                        ()) {
                    priceMinList.add(bean.getPrice_min());
                    priceMaxList.add(bean.getPrice_max());
                    dirtTimeList.add(bean.getTime_min());
                }
                price_min = mPresenter.getClearList(priceMinList).get(0);
                price_max = mPresenter.getClearList(priceMaxList).get(0);
                if (!price_min.equals("0")) {
                    currentPrice = price_min;
                    borrow_price.setText("Rp " + CommonUtil.getSortPrice(currentPrice));
                } else {
                    String priceR = borrow_price.getText().toString().replace(".", "");
                    currentPrice = priceR.substring(3, priceR.length());
                }

                //获取贷款时间,设置最短贷款时间
                for (String s : mPresenter.getClearList(dirtTimeList)) {
                    timeList.add(s + " Hari");
                }
                String mintime = timeList.get(0);
                currentTime = mintime.substring(0, mintime.length() - 5);
                borrow_time.setText(mintime);
            } else {

                //获取首付比率,设置最小首付比率
                getRateList();
                currentRate = firstRateList.get(0);
                borrow_rate.setText(currentRate + "%");

                //获取允许贷款的金额范围,设置最小贷款金额
                getPriceArea();
                if (!price_min.equals("0")) {
                    currentPrice = price_min;
                    borrow_price.setText("Rp " + CommonUtil.getSortPrice(currentPrice));
                } else {
                    String priceR = borrow_price.getText().toString().replace(".", "");
                    currentPrice = priceR.substring(3, priceR.length());
                }
                //获取贷款时间,设置最短贷款时间
                getTimeList();
                String mintime = timeList.get(0);
                currentTime = mintime.substring(0, mintime.length() - 5);
                borrow_time.setText(mintime);
            }

            //获取金额计算因子
            Map<String, String> dataMap = mPresenter.getAlgorithm(currentType, currentPrice,
                    currentTime, currentRate, dataBean.getInterest_algorithm());
            //显示计算结果
            showResult(dataMap);

            borrow_rate.setOnClickListener(this);

            //打开选择器
            borrow_price.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        EditText editText = (EditText) v;
                        editText.getText().clear();
                    }
                }
            });
            borrow_price.setOnClickListener(this);
            borrow_price.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.toString().contains("Rp") && s.length() > 3) {
                        currentPrice = s.toString().substring(3, s.length()).replace(".", "");
                    } else if (s.toString().contains("Rp") && s.length() == 3) {
                        s.clear();
                    } else {
                        currentPrice = s.toString().replace(".", "");
                    }
                }
            });

            borrow_price.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    //回车键
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        if (!price_min.equals("0") && Long.valueOf(currentPrice) < Long.valueOf(price_min)) {
                            currentPrice = price_min;
                        }
                        if (!price_max.equals("0") && Long.valueOf(currentPrice) > Long.valueOf(price_max)) {
                            currentPrice = price_max;
                        }

                    }
                    Map<String, String> dataMap = mPresenter.getAlgorithm(currentType, currentPrice,
                            currentTime, currentRate, dataBean.getInterest_algorithm());
                    borrow_price.setText("Rp " + CommonUtil.getSortPrice(currentPrice));
                    showResult(dataMap);
                    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context
                            .INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken()
                            , 0);
                    return true;
                }

            });

            borrow_time.setOnClickListener(this);

        }


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

    //获取价格范围
    private void getPriceArea() {
        for (CicidetailBean.DataBean.PriceBean bean : dataBean.getPrice()) {
            if (currentType.equals(bean.getNum()) && isContain(currentRate, bean
                    .getDown_payment_rate())) {
                priceMinList.add(bean.getPrice_min());
                priceMaxList.add(bean.getPrice_max());
            }
        }
        price_min = mPresenter.getClearList(priceMinList).get(0);
        price_max = mPresenter.getClearList(priceMaxList).get(0);
    }


    //获取时间列表
    private void getTimeList() {
        timeList.clear();
        List<String> dirtTimeList = new ArrayList<>();
        for (CicidetailBean.DataBean.PriceBean bean : dataBean.getPrice()) {
            String price_max;
            if (bean.getPrice_max().equals("0.00")) {
                price_max = Float.MAX_VALUE + "";
            } else {
                price_max = bean.getPrice_max();
            }
            float pricef = Float.valueOf(currentPrice);
            float priceMaxf = Float.valueOf(price_max);
            float priceMinf = Float.valueOf(bean.getPrice_min());
            if (currentType.equals(bean.getNum()) && isContain(currentRate, bean
                    .getDown_payment_rate()) && pricef <= priceMaxf && pricef >= priceMinf) {
                for (int i = 0; i < bean.getTime().size(); i++) {
                    dirtTimeList.add(bean.getTime().get(i).getTime_min());
                }
            }
        }
        for (String s : mPresenter.getClearList(dirtTimeList)) {
            timeList.add(s + " Hari");
        }
    }

    //获取首负利率列表
    private void getRateList() {
        List<String> dirtyRateList = new ArrayList<>();
        for (CicidetailBean.DataBean.InterestAlgorithmBean bean : dataBean.getInterest_algorithm
                ()) {
            if (bean.getNum().equals(currentType)) {
                float rate = Float.valueOf(bean.getDown_payment_rate());
                dirtyRateList.add((int) rate + "");
            }
        }
        firstRateList.clear();
        firstRateList.addAll(mPresenter.getClearList(dirtyRateList));
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


    @Override
    public void hideLoading() {
        dismiss();
    }

    @Override
    public void showLoading() {
        show(getResources().getString(R.string.waiting));
    }

    @Override
    public void showPrses(CicidetailProsesBean prosesBean) {
        if (prosesBean.getData().getProcedure() != null && prosesBean.getData().getProcedure().size()
                > 0) {
            CicidetailProsesAdapter prosesAdapter = new CicidetailProsesAdapter(getContext(), prosesBean
                    .getData().getProcedure());
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

            CicidetailInformasiAdapter informasiAdapter = new CicidetailInformasiAdapter(getContext(),
                    prosesBean.getData().getAudit_info());
            informasi_list.setLayoutManager(layoutManager);
            informasi_list.setHasFixedSize(true);
            informasi_list.setNestedScrollingEnabled(false);
            informasi_list.setAdapter(informasiAdapter);
            informasi_list.setLayoutManager(new LinearLayoutManager(getContext()));
        } else {
            cici_info_layout.setVisibility(View.GONE);
        }

    }

    @Override
    public void showReview(CicidetailReviewBean reviewBean) {
        CicidetailReviewAdapter reviewAdapter = new CicidetailReviewAdapter(getContext(), reviewBean
                .getData().getUser_review());

        komentar_list.setLayoutManager(layoutManager);
        komentar_list.setHasFixedSize(true);
        komentar_list.setNestedScrollingEnabled(false);
        komentar_list.setAdapter(reviewAdapter);
        komentar_list.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void showResult(Map<String, String> dataMap) {
        EventTrackUtil.trackEventApp(EventTrackUtil.BOSTUNAI_DETAILS_CALCULATOR_CLICK);
        if (dataMap != null) {
            String free = dataMap.get(CicidetailPresenter.FREE);
            String freeType = dataMap.get(CicidetailPresenter.FREETYPE);
            String rate = dataMap.get(CicidetailPresenter.RATE);
            String rateUnit = dataMap.get(CicidetailPresenter.RATEUNIT);
            String first_rate = dataMap.get(CicidetailPresenter.FIRSTRATE);
            String rightTime;
            if (currentTime.indexOf("Hari") > 0) {
                rightTime = currentTime.substring(0, currentTime.length() - 5);
            } else {
                rightTime = currentTime;
            }

            float firstPayMoney = Long.valueOf(currentPrice) * Float.valueOf(first_rate) / 100; //首付金额
            float freeNum = mPresenter.getTotalFree(currentPrice, free, freeType);//手续费
            float rateNum = mPresenter.getRate((Long.valueOf(currentPrice) - firstPayMoney) + "", rate,
                    rightTime, rateUnit);//利息

            float totalInterest = freeNum + rateNum;//总利息
            float totalPay = Long.valueOf(currentPrice) + totalInterest;//应还总额
            float actualPrice = Long.valueOf(currentPrice) - freeNum;//实际到款金额

            float monthPayMoney = (Long.valueOf(currentPrice) - firstPayMoney) * 30 / Float.valueOf
                    (rightTime); //每月应还本金
            float monthPayInterest = monthPayMoney * Float.valueOf(rate) / 100; //每月利息
            // float monthPayTotalMoney = monthPayMoney + monthPayInterest; //每月应还金额
            float monthPayTotalMoney = (totalPay - firstPayMoney) * 30 / Float.valueOf
                    (rightTime); //每月应还金额
            totalPay_text.setText("Rp " + CommonUtil.getSortPrice((int) totalPay + ""));
            actualPrice_text.setText("Rp " + CommonUtil.getSortPrice((int) firstPayMoney + ""));
            totalInterest_text.setText("Rp " + CommonUtil.getSortPrice((int) monthPayTotalMoney + ""));
        } else {
            showToastTip("Data tidak normal");
        }

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case com.tl.tplus.R.id.detail_see_detail:
                EventTrackUtil.trackEventApp(EventTrackUtil.BOSTUNAI_DETAILS_MORE_DETAILS_CLICK);
                intent = new Intent(getActivity(), CicidetailInfoActivity.class);
                intent.putExtra(CicidetailInfoFragment.PID, pid);
                intent.putExtra(CicidetailInfoFragment.NAME, name);
                startActivity(intent);
                break;

            case com.tl.tplus.R.id.detail_tip_layout:
                EventTrackUtil.trackEventApp(EventTrackUtil.BOSTUNAI_DETAILS_TIPS_CLICK);
                intent = new Intent(getActivity(), CicidetaiWebActivity.class);
                intent.putExtra(CicidetaiWebActivity.WEBURL, webUrl);
                startActivity(intent);
                break;

            case com.tl.tplus.R.id.borrow_typeg:
                SinglePicker typePicker = getSinglePicker(Color.BLACK, Color.GRAY, dataBean
                        .getBorrowing_type());
                typePicker.setItemWidth(500);
                typePicker.setOnItemPickListener(new OnItemPickListener<String>() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        currentType = item;
                        borrow_typeg.setText(item);
                        //更新时间列表
                        getRateList();
                        currentRate = firstRateList.get(0);
                        borrow_rateg.setText(currentRate + "%");
                        getPriceArea();
                        if (Long.valueOf(currentPrice) < Long.valueOf(price_min)) {
                            currentPrice = price_min;
                        }
                        borrow_priceg.setText("Rp " + currentPrice);
                        getTimeList();
                        currentTime = timeList.get(0);
                        borrow_timeg.setText(currentTime);
                        showResult(mPresenter.getAlgorithm(currentType, currentPrice, currentTime,
                                currentRate, dataBean.getInterest_algorithm()));
                    }
                });
                typePicker.setSelectedIndex(0);
                typePicker.show();
                break;

            case com.tl.tplus.R.id.borrow_rateg:
            case com.tl.tplus.R.id.borrow_rate:
                List<String> rateList = new ArrayList<>();
                for (String rate : firstRateList) {
                    rateList.add(rate + "%");
                }
                SinglePicker ratePicker = getSinglePicker(Color.BLACK, Color.GRAY, rateList);
                ratePicker.setItemWidth(500);
                ratePicker.setOnItemPickListener(new OnItemPickListener<String>() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        currentRate = item.substring(0, item.length() - 1);
                        borrow_rate.setText(item);
                        borrow_rateg.setText(item);
                        showResult(mPresenter.getAlgorithm(currentType, currentPrice, currentTime,
                                currentRate, dataBean.getInterest_algorithm()));
                    }
                });
                ratePicker.setSelectedIndex(0);
                ratePicker.show();
                break;


            case com.tl.tplus.R.id.borrow_timeg:
            case com.tl.tplus.R.id.borrow_time:
                if (timeList.size() > 0) {
                    SinglePicker timePicker = getSinglePicker(Color.BLACK, Color.GRAY, timeList);
                    timePicker.setItemWidth(500);
                    timePicker.setOnItemPickListener(new OnItemPickListener<String>() {
                        @Override
                        public void onItemPicked(int index, String item) {
                            currentTime = item.substring(0, item.length() - 5);
                            borrow_timeg.setText(item);
                            borrow_time.setText(item);
                            showResult(mPresenter.getAlgorithm(currentType, currentPrice, currentTime,
                                    currentRate, dataBean.getInterest_algorithm()));
                        }
                    });
                    timePicker.setSelectedIndex(0);
                    timePicker.show();
                }
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
                }
//                Uri uri = Uri.parse(dataBean.getJump_url());
//                Intent downLoadIntent = new Intent(Intent.ACTION_VIEW, uri);
//                startActivity(downLoadIntent);
                statisticsClick("1");
                EventTrackUtil.trackEventApp(EventTrackUtil.BOSTUNAI_DETAILS_DOWNLOAD_INSTALLMENT);
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
        requestBean.setProduct_type("2");
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
            isSelected = listener.getSelecteState(CicidetailActivity.GUAIDITEM1);
            EventTrackUtil.trackEventApp(EventTrackUtil.BOSTUNAI_DETAILS_TAB_DETAIL_CLICK);
            if (!isSelected) {
                listener.setGuaidSelected(CicidetailActivity.GUAIDITEM1, CicidetailActivity.GUAIDITEMD1);
            }
        } else if (scrollY >= guaid2_layout.getTop() && scrollY <=
                guaid2_layout.getBottom()) {
            isSelected = listener.getSelecteState(CicidetailActivity.GUAIDITEM2);
            EventTrackUtil.trackEventApp(EventTrackUtil.BOSTUNAI_DETAILS_TAB_FLOW_CLICK);
            if (!isSelected) {
                listener.setGuaidSelected(CicidetailActivity.GUAIDITEM2, CicidetailActivity.GUAIDITEMD2);
            }
        } else if (scrollY >= guaid3_layout.getTop() && scrollY <=
                guaid3_layout.getBottom()) {
            isSelected = listener.getSelecteState(CicidetailActivity.GUAIDITEM3);
            EventTrackUtil.trackEventApp(EventTrackUtil.BOSTUNAI_DETAILS_TAB_COMMENT_CLICK);
            if (!isSelected) {
                listener.setGuaidSelected(CicidetailActivity.GUAIDITEM3, CicidetailActivity.GUAIDITEMD3);
            }
        }
    }

    private boolean isContain(String goal, List<String> list) {

        for (String s : list) {
            float rate = Float.valueOf(s);
            if (goal.equals((int) rate + "")) {
                return true;
            }
        }
        return false;
    }


    interface CiciDetailListener {
        boolean getSelecteState(int item);//获取按钮选中状态

        void setGuaidSelected(int item, int itemDerector);//设置按钮选中
    }

}
