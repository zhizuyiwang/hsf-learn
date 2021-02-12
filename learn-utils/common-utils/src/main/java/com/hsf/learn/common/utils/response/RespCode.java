package com.hsf.learn.common.utils.response;

/**
 * 响应码枚举
 * @author srainsk
 *
 */
public enum RespCode {
    //    Common Errors
    PARAM_FORMAT_EXCEPTION("-40001", "Parameter format error", "Parameter format is incorrect"),
    SQL_LINK_EXCEPTION("-50402", "database error","database error！detail info In log system!"),
    PARAM_MISS_EXCEPTION(
      "-20001", "Parameter is missing", "Some required parameters are missing"),
    NOT_FOUND("-10001","not found","not found"),
    NOT_SUPPORTED_METHOD("-10000", "请求方法错误","not supported_method"),
    
    FAIL("-1", "fail", "fail"),
	OK("0000", "success", "success") ;

    private String code;
    private String msg;
    private String description;

    RespCode(String code, String msg, String description) {
        this.code = code;
        this.msg = msg;
        this.description = description;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getDscription() {
        return this.description;
    }
}
