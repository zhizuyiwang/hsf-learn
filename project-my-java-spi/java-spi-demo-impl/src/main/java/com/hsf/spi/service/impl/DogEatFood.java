package com.hsf.spi.service.impl;

import com.hsf.spi.service.EatFood;

public class DogEatFood implements EatFood {
    @Override
    public String eat() {
        return "小狗吃骨头";
    }
}
