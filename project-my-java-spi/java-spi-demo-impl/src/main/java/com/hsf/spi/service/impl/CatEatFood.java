package com.hsf.spi.service.impl;

import com.hsf.spi.service.EatFood;

public class CatEatFood implements EatFood {
    @Override
    public String eat() {
        return "小猫吃鱼";
    }
}
