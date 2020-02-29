package com.elasticsearch.util;

/**
 * @author quanroon.ysq
 * @version 1.0.0
 * @date 2020/2/29 17:42
 * @content
 */
public enum ReturnCodeEnum {

    RETURN_SUCCESS("000000", "成功"),
    SYSTEM_EXCEPTION("1000001", "服务器异常"),
    REQUEST_NO_PERMISSION("200001", "权限不足"),
    REQUEST_PARAM_ISNULL("100098", "参数不能为空"),
    REQUEST_PARAM_ERROR("100099", "请求参数格式化错误");

    private String retCode;
    private String message;

    private ReturnCodeEnum(String retCode, String message) {
        this.retCode = retCode;
        this.message = message;
    }

    public String getRetCode() {
        return this.retCode;
    }

    public String getMessage() {
        return this.message;
    }
}
