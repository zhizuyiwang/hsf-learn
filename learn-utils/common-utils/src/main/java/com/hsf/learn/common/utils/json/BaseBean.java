package com.hsf.learn.common.utils.json;

import java.io.Serializable;
import java.util.HashMap;

public class BaseBean<T> implements Serializable {

    private String code;

    private String msg;

    private HashMap<String,T> data;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public HashMap<String, T> getData() {
        return data;
    }

    public void setData(HashMap<String, T> data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "BaseBean{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
