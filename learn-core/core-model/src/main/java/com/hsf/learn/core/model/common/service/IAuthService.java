package com.hsf.learn.core.model.common.service;

import com.hsf.learn.core.model.common.entity.UserInfoModel;

/**
 * 登录，登出，获取验证码服务
 */
public interface IAuthService {

    UserInfoModel userLogin(String userName, String password, String loginType);

    void userLogout(Long userId);

    void sendSmsCode(String mobile);
}
