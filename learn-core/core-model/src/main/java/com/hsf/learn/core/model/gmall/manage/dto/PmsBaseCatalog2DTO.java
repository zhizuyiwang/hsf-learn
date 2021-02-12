package com.hsf.learn.core.model.gmall.manage.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PmsBaseCatalog2DTO implements Serializable {
    private String id;
    private String name;
    private String catalog1Id;

    private List<PmsBaseCatalog3DTO> catalog3List;
}
