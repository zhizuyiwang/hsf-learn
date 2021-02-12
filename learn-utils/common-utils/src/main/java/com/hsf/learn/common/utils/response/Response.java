package com.hsf.learn.common.utils.response;


import com.hsf.learn.common.utils.exception.CoreServiceException;

/**
 * <p>
 * 基础返回类
 *
 */
public class Response<T> extends Status {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setResp(RespCode respCode) {
		this.setResp(respCode, null);
	}

	public void setResp(RespCode respCode, T data) {
		this.setCode(respCode.getCode());
		this.setMsg(respCode.getMsg());
		this.data = data;
	}
	
	public void setResp(String respCode, String respMsg, T data) {
		this.setCode(respCode);
		this.setMsg(respMsg);
		this.data = data;
	}

	public void setResp(CoreServiceException e) {
		this.setCode(e.getCode());
		this.setMsg(e.getMessage());
	}
	
}
