package com.hsf.learn.common.utils.auth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthUtils {

	public static final String SECURITY_KEY = "3ecda987fe1042388eb614ed32eedd21";
	
	public static final String PWD_SECURITY_KEY = "07knj3tb4wafmkgfknfe93s3h64jymgg";

	public static final String REDIS_KEY_MGR_SYS_USER_LOGIN_NAME = "beacon:mgr:sys:user:loginname:token:%s";
	public static final String REDIS_KEY_MGR_SYS_USER_ID = "beacon:mgr:sys:user:userid:token:%s";
	public static final String REDIS_KEY_MGR_SYS_USER_ORG_CODE = "beacon:mgr:sys:user:userid:orgcode:%s";
	public static final String REDIS_KEY_MGR_SYS_ORG_INFO = "beacon:mgr:sys:org:orginfo:%s";
	public static final String REDIS_KEY_MGR_SYS_ORG_INFO_ADMIN = "beacon:mgr:sys:org:orginfo:%s:%s:%s";
	
	public static final String REDIS_KEY_MGR_SYS_USER_MOBILE_SMSCODE = "beacon:mgr:sys:user:mobile:smscode:%s";
	
	/** 7天  */
	public static final Integer TOKEN_EXPTIME = 60 * 60 * 24 * 7;
	
	/** 5分钟  */
	public static final Integer SMS_CODE_EXPTIME = 60 * 5;
	
	public static final String MOBILE_REGEX = "0?(13|14|15|17|18|19)[0-9]{9}";
	
	public static boolean valiMobile(String mobile) {
		if (mobile.length() != 11) {
			return false;
		}
		Pattern p = Pattern.compile(AuthUtils.MOBILE_REGEX);
		Matcher m = p.matcher(mobile);
		boolean isMatch = m.matches();
		if(isMatch){
			return true;
		}
		return false;
	}

}
