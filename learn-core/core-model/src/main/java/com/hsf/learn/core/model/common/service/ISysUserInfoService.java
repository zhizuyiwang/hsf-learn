package com.hsf.learn.core.model.common.service;

import com.hsf.learn.core.model.common.entity.UserInfoModel;

public interface ISysUserInfoService {
    UserInfoModel selectUserByPhone(String mobile);

    UserInfoModel selectUserByLoginName(String username);

    UserInfoModel selectUserById(Long userId);
}
