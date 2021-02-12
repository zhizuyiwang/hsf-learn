package com.hsf.learn.common.utils.json;

import java.util.Arrays;

public class BaseListBean<T> {
    private int size;
    private T[] records;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public T[] getRecords() {
        return records;
    }

    public void setRecords(T[] records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "BaseListBean{" +
                "size=" + size +
                ", records=" + Arrays.toString(records) +
                '}';
    }
}
