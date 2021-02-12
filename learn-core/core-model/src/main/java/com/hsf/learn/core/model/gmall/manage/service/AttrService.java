package com.hsf.learn.core.model.gmall.manage.service;



import com.hsf.learn.core.model.gmall.manage.dto.PmsBaseAttrInfoDTO;

import java.util.List;

public interface AttrService {

    List<PmsBaseAttrInfoDTO> getAttrInfoList(String catalog3Id);
}
