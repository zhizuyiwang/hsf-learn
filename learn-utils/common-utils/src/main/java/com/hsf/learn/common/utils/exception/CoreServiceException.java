package com.hsf.learn.common.utils.exception;

import com.hsf.learn.common.utils.response.RespCode;
import lombok.Data;

@Data
public class CoreServiceException extends RuntimeException {
    private static final long serialVersionUID = 1907172618840260982L;

    private String code;

    public CoreServiceException() {
		super();
	}
	
	public CoreServiceException(String message, Throwable cause) {
		super(message);
		super.initCause(cause);
		this.code = RespCode.FAIL.getCode();
	}
	
	public CoreServiceException(String code, String message, Throwable cause) {
		super(message);
		super.initCause(cause);
		this.code = code;
	}
	
	public CoreServiceException(String message) {
		super(message);
		this.code = RespCode.FAIL.getCode();
	}
	
	public CoreServiceException(String code, String message) {
		super(message);
		this.code = code;
	}
	
	public CoreServiceException(Throwable cause) {
		super(RespCode.FAIL.getMsg());
		super.initCause(cause);
		this.code = RespCode.FAIL.getCode();
		
	}

	public String getCode() {
		return code;
	}

}
