package com.hsf.learn.core.model.gmall.manage.service;


import com.hsf.learn.core.model.gmall.manage.dto.PmsBaseCatalog1DTO;
import com.hsf.learn.core.model.gmall.manage.dto.PmsBaseCatalog2DTO;
import com.hsf.learn.core.model.gmall.manage.dto.PmsBaseCatalog3DTO;

import java.util.List;

public interface CatalogService {
    List<PmsBaseCatalog1DTO> getCatalog1();

    List<PmsBaseCatalog2DTO> getCatalog2(String catalogId);

    List<PmsBaseCatalog3DTO> getCatalog3(String catalogId);

}
