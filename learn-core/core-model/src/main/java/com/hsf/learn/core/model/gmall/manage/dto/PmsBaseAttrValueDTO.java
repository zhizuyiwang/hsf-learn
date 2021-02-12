package com.hsf.learn.core.model.gmall.manage.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PmsBaseAttrValueDTO implements Serializable {
    private String id;
    private String valueName;
    private String attrId;
    private String isEnabled;
    private String urlParam;
}
