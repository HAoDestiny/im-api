package com.tlink.common.core.domain;

/**
 * @author destiny
 * @date 2021/6/4 12:41
 */
public enum  ApiResponse {

    NOT_LOGIN("10000", "未登录, 请前往登录"),
    TOKEN_FAIL("10001", "token令牌已失效, 请重新登录"),

    PARAM_FAIL("30001", "参数错误"),
    NOT_FOUND("30002", "记录不存在"),
    ACTION_FAIL("30003", "操作失败"),


    ACCOUNT_EXISTED("40001", "用户已存在")
    ;

    private String code;
    private String msg;

    ApiResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
