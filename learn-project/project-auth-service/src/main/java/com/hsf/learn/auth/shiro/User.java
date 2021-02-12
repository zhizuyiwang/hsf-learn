package com.hsf.learn.auth.shiro;

import lombok.Data;

@Data
public class User {
	private String username;
	private String password;
	private String loginType;
	//登录；来源，app
	private String loginSource;
}
