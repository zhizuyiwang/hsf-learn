package com.hsf.learn.auth.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hsf.learn.auth.service.ISysUserInfoService;
import com.hsf.learn.core.model.common.entity.UserInfoModel;
import org.springframework.stereotype.Component;

@Service()
@Component("sysUserInfoServiceImpl")
public class SysUserInfoServiceImpl implements ISysUserInfoService {
    @Override
    public UserInfoModel selectUserByPhone(String mobile) {
        return null;
    }

    @Override
    public UserInfoModel selectUserByLoginName(String username) {
        return null;
    }

    @Override
    public UserInfoModel selectUserById(Long userId) {
        return null;
    }
}
