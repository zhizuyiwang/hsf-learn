package com.hsf.dubbo.spi.service;

import org.apache.dubbo.common.extension.SPI;

@SPI
public interface EatFood {
    String eat();
}
