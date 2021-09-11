package com.hsf.dubbo.spi.service.impl;

import com.hsf.dubbo.spi.service.EatFood;

public class CatEatFood implements EatFood {
    @Override
    public String eat() {
        return "小猫吃鱼";
    }
}
