package com.hsf.learn.common.utils.http;

import java.io.Serializable;

/**
 * http 客户端工具
 * @author ogz 19.1.16
 */
public class HttpClientResult implements Serializable {

    private static final long serialVersionUID = -386297042713034527L;

    /**
     * 返回码
     */
    private int code;
    /**
     * 返回数据
     */
    private String content;

    public HttpClientResult(){}

    public HttpClientResult(int code, String content) {
        this.code = code;
        this.content = content;
    }

    public HttpClientResult(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "HttpClientResult{" +
                "code=" + code +
                ", content='" + content + '\'' +
                '}';
    }
}
