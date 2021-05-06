package com.hsf.learn.demo.mytomcat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 静态资源处理工具类
 */
public class StaticResourceUtil {
    /**
     * 获取静态资源⽂件的绝对路径
     * @param path
     * @return
     */
    public static String getAbsolutePath(String path) {
        String absolutePath =
                StaticResourceUtil.class.getResource("/").getPath();
        return absolutePath.replaceAll("\\\\","/") + path;
    }


    public static void outputStaticResource(InputStream inputStream, OutputStream outputStream) throws IOException {
        int count = 0;
        while (count == 0){
            count = inputStream.available();
        }
        int resourceSize = count;
        //先把响应头输出出去
        outputStream.write(HttpProtocolUtil.getHttpHeader200(resourceSize).getBytes());

        //再把响应体输出
        long written = 0;//已经读取的内容长度
        int bufferSize = 1024;//每次读取缓冲的数据长度
        byte[] bytes = new byte[bufferSize];

        while (written < resourceSize){

            if(written + bufferSize > resourceSize){//说明剩余未读取的大小不足一个1024长度，那就按照真实大小读取
                bufferSize = (int) (resourceSize - written);//剩余的文件内容长度
                bytes = new byte[bufferSize];
            }

            inputStream.read(bytes);
            outputStream.write(bytes);
            outputStream.flush();

            written+=bufferSize;
        }
    }
}
