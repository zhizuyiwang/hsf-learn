package com.hsf.learn.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hsf.learn.common.redis.config.RedisComponent;
import com.hsf.learn.common.redis.config.RedisUtil;
import com.hsf.learn.core.mapper.gmall.manage.PmsBaseAttrInfoMapper;
import com.hsf.learn.core.mapper.gmall.manage.PmsBaseAttrValueMapper;
import com.hsf.learn.core.mapper.gmall.manage.entity.PmsBaseAttrInfo;
import com.hsf.learn.core.mapper.gmall.manage.entity.PmsBaseAttrValue;
import com.hsf.learn.core.model.gmall.manage.dto.PmsBaseAttrInfoDTO;
import com.hsf.learn.core.model.gmall.manage.dto.PmsBaseAttrValueDTO;
import com.hsf.learn.core.model.gmall.manage.service.AttrService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AttrServiceImpl implements AttrService {

    @Resource
    PmsBaseAttrInfoMapper pmsBaseAttrInfoMapper;

    @Resource
    PmsBaseAttrValueMapper pmsBaseAttrValueMapper;

    @Override
    public List<PmsBaseAttrInfoDTO> getAttrInfoList(String catalog3Id) {
        PmsBaseAttrInfo pmsBaseAttrInfo = new PmsBaseAttrInfo();
        pmsBaseAttrInfo.setCatalog3Id(catalog3Id);
        List<PmsBaseAttrInfo> data = pmsBaseAttrInfoMapper.select(pmsBaseAttrInfo);

        if(data != null && data.size() > 0){
            ArrayList<PmsBaseAttrInfoDTO> result = new ArrayList<>();
            for(PmsBaseAttrInfo item : data){
                PmsBaseAttrInfoDTO pmsBaseAttrInfoDTO = new PmsBaseAttrInfoDTO();
                BeanUtils.copyProperties(item, pmsBaseAttrInfoDTO);
                List<PmsBaseAttrValue> attrValueList = item.getAttrValueList();
                if(attrValueList != null && attrValueList.size() > 0){
                    ArrayList<PmsBaseAttrValueDTO> attrValueDTOList = new ArrayList<>();
                    for (PmsBaseAttrValue attrValue : attrValueList){
                        PmsBaseAttrValueDTO pmsBaseAttrValueDTO = new PmsBaseAttrValueDTO();
                        BeanUtils.copyProperties(attrValue, pmsBaseAttrValueDTO);
                        attrValueDTOList.add(pmsBaseAttrValueDTO);
                    }
                    pmsBaseAttrInfoDTO.setAttrValueList(attrValueDTOList);
                }
                result.add(pmsBaseAttrInfoDTO);
            }
            return result;
        }
        return null;
    }

}
