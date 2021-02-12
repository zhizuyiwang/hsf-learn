package com.hsf.learn.common.utils.enumset;

public enum LoginTypeEnum {
    USERNAME("1", "用户名"),
    PHONE("2", "手机");
    private String code;
    private String msg;
    LoginTypeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public String getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}
