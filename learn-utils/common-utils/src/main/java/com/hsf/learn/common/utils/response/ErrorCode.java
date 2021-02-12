package com.hsf.learn.common.utils.response;

/**
 * @ProjectName: message-hub-template
 * @Package: com.decathlon.core.error
 * @ClassName: ErrorCode
 * @Author: jerry
 * @Description:
 * @Date: 2020/1/2 3:27 PM
 * @Modify:
 * @Version: 2.0
 **/
public enum ErrorCode {
    //    Common Errors
    PARAM_FORMAT_EXCEPTION("-40001", "Parameter format error", "Parameter format is incorrect"),
    SQL_LINK_EXCEPTION("-50402", "database error","database error！detail info In log system!"),
    PARAM_MISS_EXCEPTION(
      "-20001", "Parameter is missing", "Some required parameters are missing"),
    FAIL("-1", "fail", "fail"),
	OK("0000", "success", "success") ;

    private String errCode;
    private String errMsg;
    private String errDescription;

    ErrorCode(String errCode, String errMsg, String errDescription) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.errDescription = errDescription;
    }

    public String getErrCode() {
        return this.errCode;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public String getErrDescription() {
        return this.errDescription;
    }
}
