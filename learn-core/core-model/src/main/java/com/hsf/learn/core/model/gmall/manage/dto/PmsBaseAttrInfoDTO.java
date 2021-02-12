package com.hsf.learn.core.model.gmall.manage.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PmsBaseAttrInfoDTO implements Serializable {
    private String id;
    private String attrName;
    private String catalog3Id;
    private String isEnabled;
    List<PmsBaseAttrValueDTO> attrValueList;
}
