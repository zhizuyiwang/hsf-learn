package com.hsf.learn.common.utils.exception;


import com.hsf.learn.common.utils.constants.APIConstants;

import java.util.Optional;

/**
 * utils - common exception definition
 * @author og 19.07.02
 */
public class IotUtilsCommonException extends RuntimeException{
    /**
     * 返回信息
     */
    private IotUtilsCodeEnum rCode;

    public IotUtilsCommonException(){
        super();
    }

    public IotUtilsCommonException(IotUtilsCodeEnum rCode, Throwable cause){
        super(rCode.getMsg(), cause);
        this.rCode = rCode;

    }

    public IotUtilsCommonException(IotUtilsCodeEnum rCode){
        super(rCode.getMsg());
        this.rCode = rCode;
    }

    public String getCode() {
        return Optional.ofNullable(rCode.getCode()).orElse(APIConstants.API_NONE);
    }

    public String getMsg(){
        return Optional.ofNullable(rCode.getMsg()).orElse(APIConstants.API_NONE);
    }

    public IotUtilsCodeEnum getRCode() {
        return rCode;
    }
}
