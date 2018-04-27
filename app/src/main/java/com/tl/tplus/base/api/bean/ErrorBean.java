package com.tl.tplus.base.api.bean;

/**
 * Created by fangpenglin on 16/7/3.
 * 整个项目的错误model层,方便后期扩展
 */
public class ErrorBean {

    private int errCode;   //错误编码

    private String errMessage; //错误描述

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    @Override
    public String toString() {
        return "ErrorBean{" +
                "errCode=" + errCode +
                ", errMessage='" + errMessage + '\'' +
                '}';
    }
}
