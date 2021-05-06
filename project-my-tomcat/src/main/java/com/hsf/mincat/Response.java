package com.hsf.mincat;

import lombok.Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@Data
public class Response {

    private OutputStream outputStream;

    public Response(){
    }

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    // 使⽤输出流输出指定字符串
    public  void output(String content) throws IOException {
        outputStream.write(content.getBytes());

    }

    /**
     * 根据url来获取到静态资源的绝对路径，进⼀步根据绝对路径
     * 读取该静态资源⽂件，最终通过输出流输出
     * @param path
     */
    public void outputHtml(String path) throws IOException {
        //获取静态资源的绝对路径
        String absolutePath = StaticResourceUtil.getAbsolutePath(path);

        //输入静态资源文件
        File file = new File(absolutePath);
        if(file.exists() && file.isFile()){
        // 读取静态资源⽂件，输出静态资源
            StaticResourceUtil.outputStaticResource(new FileInputStream(file), outputStream);
        }else{
            //输出404
            output(HttpProtocolUtil.getHttpHeader404());
        }
    }
}
