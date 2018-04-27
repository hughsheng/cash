package com.tl.tplus.home.adapter;

/**
 * Created by linfp on 2016/6/16.
 * 无数据和无网络状态的提示
 */
public class TipBean {

    /**
     * 提示内容
     */
    private String content;

    /**
     * 提示按钮的内容
     */
    private String btnTxt;

    /**
     * 数据状态描述
     * NO_NET,  无网络状态
     * NO_LOGIN,  无登录状态
     * NO_DATA  无数据状态
     */
    private DataStatus mDataStatus;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBtnTxt() {
        return btnTxt;
    }

    public void setBtnTxt(String btnTxt) {
        this.btnTxt = btnTxt;
    }

    public DataStatus getDataStatus() {
        return mDataStatus;
    }

    public void setDataStatus(DataStatus dataStatus) {
        mDataStatus = dataStatus;
    }

    public enum DataStatus {
        NO_NET, NO_LOGIN, NO_DATA, NO_CARD
    }
}
