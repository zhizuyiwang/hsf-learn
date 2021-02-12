package com.hsf.learn.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hsf.learn.common.utils.constants.RequestConstants;
import com.hsf.learn.core.model.gmall.manage.dto.PmsBaseAttrInfoDTO;
import com.hsf.learn.core.model.gmall.manage.service.AttrService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping(value = RequestConstants.ATTR)
@CrossOrigin
public class AttrController {
    @Reference
    AttrService attrService;

    @GetMapping("/attrInfoList")
    public List<PmsBaseAttrInfoDTO> getAttrInfoList(String catalog3Id){
        return attrService.getAttrInfoList(catalog3Id);
    }
}
