package com.hsf.learn.demo.mytomcat;

/**
 * http协议⼯具类，主要是提供响应头信息，这⾥我们只提供200和404的情况
 */
public class HttpProtocolUtil {

    /**
     * 为响应码200提供报文首部信息
     * @param contentLength
     * @return
     */
    public static String getHttpHeader200(long contentLength) {
        return "HTTP/1.1 200 OK \n" +
                "Content-Type: text/html \n" +
                "Content-Length: " + contentLength + " \n" +
                "\r\n";
    }
    /**
     * 为响应码404提供报文首部信息(此处也包含了数据内容)
     * @return
     */
    public static String getHttpHeader404() {
        String str404 = "<h1>404 not found</h1>";
        return "HTTP/1.1 404 NOT Found \n" +
                "Content-Type: text/html \n" +
                "Content-Length: " + str404.getBytes().length + " \n" +
                "\r\n" + str404;
    }

    public static void main(String[] args) {
        String s ="abcd\\123\\\\dcba";
        //把s中的反斜杠\ 替换为\\
        System.out.println(s);
        //结果是abcd\\123\\\\dcba，记住\\\\表示\
        System.out.println(s.replaceAll("\\\\", "\\\\\\\\"));
        //结果是abcd\\\\123\\\\\\\\dcba
        System.out.println(s);
        System.out.println(s.replace("\\", "\\\\\\\\"));

    }
}
