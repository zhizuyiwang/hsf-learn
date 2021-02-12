package com.hsf.learn.core.model.gmall.manage.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PmsBaseCatalog1DTO implements Serializable {

    private String id;
    private String name;

    private List<PmsBaseCatalog2DTO> catalog2s;
}
