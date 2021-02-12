package com.hsf.learn.auth.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 登录，登出，获取验证码服务
 */
public interface ILoginService {

    JSONObject userLogin(String userName, String password, String loginType);

    void userLogout(Long userId);

    void sendSmsCode(String mobile);
}
