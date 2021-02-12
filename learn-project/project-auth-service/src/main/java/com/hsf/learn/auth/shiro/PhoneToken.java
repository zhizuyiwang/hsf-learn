package com.hsf.learn.auth.shiro;

import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;

import java.io.Serializable;

public class PhoneToken implements HostAuthenticationToken, RememberMeAuthenticationToken, Serializable {

	// 手机号码
    private String phone;
    private String smsCode;
    private boolean rememberMe;
    private String host;
	
	@Override
	public Object getPrincipal() {
		return this.phone;
	}

	@Override
	public Object getCredentials() {
		return this.smsCode;
	}
	
	public PhoneToken() {
		this.rememberMe = false; 
	}
	
	public PhoneToken(String phone, String smsCode) {
		this(phone, smsCode, false, null);
	}
	
	public PhoneToken(String phone, String smsCode, boolean rememberMe) {
		this(phone, smsCode, rememberMe, null);
	}

	public PhoneToken(String phone, String smsCode, boolean rememberMe, String host) {
		this.phone = phone;
		this.smsCode = smsCode;
		this.rememberMe = rememberMe;
		this.host = host;
	}

	@Override
	public boolean isRememberMe() {
		return this.rememberMe;
	}

	@Override
	public String getHost() {
		return this.host;
	}

	public String getPhone() {
		return phone;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

}
