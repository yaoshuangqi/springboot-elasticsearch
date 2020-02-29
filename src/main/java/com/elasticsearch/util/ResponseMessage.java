package com.elasticsearch.util;

/**
 * @author quanroon.ysq
 * @version 1.0.0
 * @date 2020/2/29 17:40
 * @content
 */
public class ResponseMessage {

    private static final long serialVersionUID = 1L;
    private String retCode = "000000";
    private String message = "成功";
    private Object result;

    public ResponseMessage() {
    }

    public String getRetCode() {
        return this.retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return this.result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
