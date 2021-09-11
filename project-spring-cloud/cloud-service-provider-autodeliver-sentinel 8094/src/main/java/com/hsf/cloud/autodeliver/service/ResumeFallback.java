package com.hsf.cloud.autodeliver.service;

import org.springframework.stereotype.Component;

/**
 * 降级回退逻辑需要定义一个类，实现FeignClient接口，实现接口中的方法
 *
 */
@Component
public class ResumeFallback implements ResumeFeignClient{
    @Override
    public Integer findResumeOpenStateByFeign(Integer userId) {
        return -6;
    }
}
