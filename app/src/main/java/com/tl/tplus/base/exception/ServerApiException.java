package com.tl.tplus.base.exception;

/**
 * Created by linfp on 2016/6/28.
 * 服务器异常(和服务器协议的错误)
 */
public final class ServerApiException extends Exception {
    private final int code;

    private final String errMsg;

    public ServerApiException(int code, String message) {
//        super("Server Api Code " + code + " " + message);
        super(message);
        this.code = code;
        this.errMsg = message;
    }

    public int getCode() {
        return code;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
