package com.hsf.cloud.autodeliver.config;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class SentinelHandleClass {
    public static Integer findResumeOpenStateByRibbonTimeoutHystrix(Integer userId, BlockException blockException){
        return -6;
    }
    public static Integer sentinelHandle(Integer userId){
        return -7;
    }
}
