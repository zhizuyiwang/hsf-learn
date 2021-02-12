package com.hsf.learn.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hsf.learn.core.mapper.gmall.manage.PmsBaseCatalog1Mapper;
import com.hsf.learn.core.mapper.gmall.manage.PmsBaseCatalog2Mapper;
import com.hsf.learn.core.mapper.gmall.manage.PmsBaseCatalog3Mapper;
import com.hsf.learn.core.mapper.gmall.manage.entity.*;
import com.hsf.learn.core.model.gmall.manage.dto.*;
import com.hsf.learn.core.model.gmall.manage.service.CatalogService;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {
    @Resource
    PmsBaseCatalog1Mapper catalogMapper;

    @Resource
    PmsBaseCatalog2Mapper catalog2Mapper;

    @Resource
    PmsBaseCatalog3Mapper catalog3Mapper;

    @Override
    public List<PmsBaseCatalog1DTO> getCatalog1() {
        List<PmsBaseCatalog1> select = catalogMapper.selectAll();

        if(select != null && select.size() > 0){
            ArrayList<PmsBaseCatalog1DTO> result = new ArrayList<>();
            for(PmsBaseCatalog1 item : select){
                PmsBaseCatalog1DTO pmsBaseCatalog1DTO = new PmsBaseCatalog1DTO();
                BeanUtils.copyProperties(item, pmsBaseCatalog1DTO);
                List<PmsBaseCatalog2> catalog2List = item.getCatalog2s();
                if(catalog2List != null && catalog2List.size() > 0){
                    ArrayList<PmsBaseCatalog2DTO> catalog2DTOList = new ArrayList<>();
                    for (PmsBaseCatalog2 catalog2 : catalog2List){
                        PmsBaseCatalog2DTO pmsBaseCatalog2DTO = new PmsBaseCatalog2DTO();
                        BeanUtils.copyProperties(catalog2, pmsBaseCatalog2DTO);
                        catalog2DTOList.add(pmsBaseCatalog2DTO);
                    }
                    pmsBaseCatalog1DTO.setCatalog2s(catalog2DTOList);
                }
                result.add(pmsBaseCatalog1DTO);
            }
            return result;
        }
        return null;
    }

    @Override
    public List<PmsBaseCatalog2DTO> getCatalog2(String catalogId) {
        PmsBaseCatalog2 pmsBaseCatalog2 = new PmsBaseCatalog2();
        pmsBaseCatalog2.setCatalog1Id(catalogId);
        List<PmsBaseCatalog2> select = catalog2Mapper.select(pmsBaseCatalog2);

        if(select != null && select.size() > 0){
            ArrayList<PmsBaseCatalog2DTO> result = new ArrayList<>();
            for(PmsBaseCatalog2 item : select){
                PmsBaseCatalog2DTO pmsBaseCatalog2DTO = new PmsBaseCatalog2DTO();
                BeanUtils.copyProperties(item, pmsBaseCatalog2DTO);
                List<PmsBaseCatalog3> catalog3List = item.getCatalog3List();
                if(catalog3List != null && catalog3List.size() > 0){
                    ArrayList<PmsBaseCatalog3DTO> catalog3DTOList = new ArrayList<>();
                    for (PmsBaseCatalog3 catalog3 : catalog3List){
                        PmsBaseCatalog3DTO pmsBaseCatalog3DTO = new PmsBaseCatalog3DTO();
                        BeanUtils.copyProperties(catalog3, pmsBaseCatalog3DTO);
                        catalog3DTOList.add(pmsBaseCatalog3DTO);
                    }
                    pmsBaseCatalog2DTO.setCatalog3List(catalog3DTOList);
                }
                result.add(pmsBaseCatalog2DTO);
            }
            return result;
        }
        return null;
    }

    @Override
    public List<PmsBaseCatalog3DTO> getCatalog3(String catalogId) {
        PmsBaseCatalog3 pmsBaseCatalog3 = new PmsBaseCatalog3();
        pmsBaseCatalog3.setCatalog2Id(catalogId);
        List<PmsBaseCatalog3> select = catalog3Mapper.select(pmsBaseCatalog3);

        if(select != null && select.size() > 0){
            ArrayList<PmsBaseCatalog3DTO> result = new ArrayList<>();
            for(PmsBaseCatalog3 item : select){
                PmsBaseCatalog3DTO pmsBaseCatalog3DTO = new PmsBaseCatalog3DTO();
                BeanUtils.copyProperties(item, pmsBaseCatalog3DTO);
                result.add(pmsBaseCatalog3DTO);
            }
            return result;
        }
        return null;
    }
}
