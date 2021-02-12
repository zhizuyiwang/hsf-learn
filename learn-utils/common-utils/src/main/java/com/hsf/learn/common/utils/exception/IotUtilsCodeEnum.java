package com.hsf.learn.common.utils.exception;


/**
 * 这里需要进行代码冗余
 * 按照module 进行
 * @author og 19.07.02
 */
public enum IotUtilsCodeEnum {

    NONE("0000", "none"),
    SUCCESS("0001", "success"),
    FAILURE("0002", "failure"),
    ImageOverSize("1300", "image over size"),
    UploadFailure("1301", "upload fail"),
    ILLEGAL("0003", "illegal");

    IotUtilsCodeEnum(String code, String msg){

    }
    /**
     * 错误码
     */
    private String code;
    /**
     * 描述
     */
    private String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }



}
