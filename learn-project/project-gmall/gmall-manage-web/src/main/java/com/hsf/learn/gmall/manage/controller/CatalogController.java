package com.hsf.learn.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hsf.learn.common.utils.constants.RequestConstants;
import com.hsf.learn.core.model.gmall.manage.dto.PmsBaseCatalog1DTO;
import com.hsf.learn.core.model.gmall.manage.dto.PmsBaseCatalog2DTO;
import com.hsf.learn.core.model.gmall.manage.dto.PmsBaseCatalog3DTO;
import com.hsf.learn.core.model.gmall.manage.service.CatalogService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping(value = RequestConstants.CATALOG)
@CrossOrigin
public class CatalogController {
    @Reference
    CatalogService catalogService;

    @PostMapping("/getCatalog1")
    public List<PmsBaseCatalog1DTO> getCatalog1(){
        return catalogService.getCatalog1();
    }
    @PostMapping("/getCatalog2")
    public List<PmsBaseCatalog2DTO> getCatalog2(String catalog1Id){
        return catalogService.getCatalog2(catalog1Id);
    }

    @PostMapping("/getCatalog3")
    public List<PmsBaseCatalog3DTO> getCatalog3(String catalog2Id){
        return catalogService.getCatalog3(catalog2Id);
    }
}
