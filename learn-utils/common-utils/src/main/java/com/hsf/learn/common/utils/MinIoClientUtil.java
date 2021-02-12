package com.hsf.learn.common.utils;

import io.minio.MinioClient;
import org.springframework.stereotype.Component;
/**
 * minIo系统工具类
 * 2020-01-09
 */
@Component
public class MinIoClientUtil {
    /**
     * 服务节点
     */
    private static final String HOST_URL  = "http://192.168.95.100:9000";
    private static final String BUCKET_NAME   = "img";

    /**
     * 授权
     */
    private static final String ACCESS_KEY  = "hsfhsf";

    /**
     * 密钥
     */
    private static final String ACCESS_SECRET = "12345678";

    /**
     * 外文链接永不过期
     */
    private static final int  EXPIRES_TIME = 6666;

    /**
     * 往minio中上传文件
     * @param bucketFileName 上传的文件在minio中的路径
     * @param fileNamePath 要上传的文件路径
     * @return 下载的文件路径
     */
    public static String upload(String bucketFileName, String fileNamePath) {
        String upLoadUrl = "";
        try {
            MinioClient minioClient = new MinioClient(HOST_URL,
                    ACCESS_KEY, ACCESS_SECRET);
            // 检查存储桶是否已经存在
            boolean isExist = minioClient.bucketExists(BUCKET_NAME);
            if (!isExist) {
                minioClient.makeBucket(BUCKET_NAME);
            }
            minioClient.putObject(BUCKET_NAME,
                    bucketFileName, fileNamePath);
            String upLoadUrl1 = minioClient.presignedGetObject(BUCKET_NAME, bucketFileName, EXPIRES_TIME);
            String upLoadUrl11 = minioClient.getObjectUrl(BUCKET_NAME, bucketFileName);
            System.out.println("下载路径===" + upLoadUrl1);
            System.out.println("下载路径===" + upLoadUrl11);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return upLoadUrl;
    }


    public static void main(String[] args) {
        String bucketFileName ="test.jpg";
        String fileNamePath = "C:\\Users\\Think\\Desktop\\test.jpg";
        upload(bucketFileName, fileNamePath);


    }
}
