package com.hsf.learn.core.model.common.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfoDTO implements Serializable {
    private String code;

    private String orgCode;

    private String loginName;

    private String loginPwd;

    private String mobile;

    private String name;

    private String token;


}
