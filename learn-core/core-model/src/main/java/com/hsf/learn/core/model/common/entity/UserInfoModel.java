package com.hsf.learn.core.model.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 */
@Data
public class UserInfoModel implements Serializable {
    private Long id;

    private String code;

    private String orgCode;

    private String loginName;

    private String loginPwd;

    private String mobile;

    private String name;

    private String sex;

    private Date birthday;

    private String type;

    private String status;

    private String employeeCode;

    private Date createDate;

    private Date updateDate;

    private String createBy;

    private String updateBy;

    private String text1;

    private String text2;

    private String text3;

    private String headImg;
}
