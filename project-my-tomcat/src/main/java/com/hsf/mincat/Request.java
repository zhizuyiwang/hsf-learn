package com.hsf.mincat;

import lombok.Data;

import java.io.IOException;
import java.io.InputStream;

/**
 * 把请求信息封装成Request对象
 */
@Data
public class Request {

    /**
     * 请求的方法,GET or POST
     */
    private String method;

    /**
     * 请求的url,index.html
     */
    private String url;

    /**
     * 请求的输入流.其他属性从输⼊流中解析出来
     */
    private InputStream inputStream;

    public Request() {

    }
    public Request(InputStream inputStream) throws IOException {
        this.inputStream = inputStream;

        int count = 0;
        while (count == 0){
            count = inputStream.available();
        }

        byte[] bytes = new byte[count];
        inputStream.read(bytes);

        String inputStr = new String(bytes);
        System.out.println("接受的请求参数===="+inputStr);

        // 获取第⼀⾏请求头信息
        String firstLineStr = inputStr.split("\\n")[0];
        method = firstLineStr.split(" ")[0];
        url = firstLineStr.split(" ")[1];

        System.out.println("方法=====" + method);
        System.out.println("url====" + url);

    }
}
