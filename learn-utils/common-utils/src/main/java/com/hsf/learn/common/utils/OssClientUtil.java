package com.hsf.learn.common.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.hsf.learn.common.utils.constants.APIConstants;
import com.hsf.learn.common.utils.exception.IotUtilsCodeEnum;
import com.hsf.learn.common.utils.exception.IotUtilsCommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Random;

/**
 * oss 服务工具类
 * @author ogz 19.1.9
 */
public class OssClientUtil {

    private static final Logger log = LoggerFactory.getLogger(OssClientUtil.class);

    /**
     * 服务节点
     */
    private static final String END_POINT     = "oss-cn-shanghai.aliyuncs.com";
    private static final String BUCKET_NAME   = "beacon-media";
    /**
     * 授权
     */
    private static final String ACCESS_KEY    = "LTAIxuJzCN49WyOx";

    /**
     * 密钥
     */
    private static final String ACCESS_SECRET = "YzU2GrECDsYFlePmGeqHAhFYejW5Q8";

    /**
     * oss 对象
     */
    private OSSClient ossClient;

    public OssClientUtil(){
        this.ossClient = new OSSClient(END_POINT, ACCESS_KEY, ACCESS_SECRET);
    }

    /**
     * 销毁
     */
    public void destroy(){
        ossClient.shutdown();
    }

    public String uploadFile2Oss(MultipartFile file){
        //是否是1M
        if(file.getSize() > APIConstants.API_M_1){
            throw new IotUtilsCommonException(IotUtilsCodeEnum.ImageOverSize);
        }

        String fileName = file.getOriginalFilename();

        String subStr = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        Random random = new Random();
        StringBuilder name = new StringBuilder(APIConstants.API_INT_50);
        name.append(suffix).append(random.nextInt(10000)).append(System.currentTimeMillis()).append(subStr);
        try {
            InputStream inputStream = file.getInputStream();
            this.uploadFile2OSS(inputStream, name.toString());
            return name.toString();
        } catch (IOException e) {
            throw new IotUtilsCommonException(IotUtilsCodeEnum.UploadFailure);
        }
    }
    public void ss(){
        String fileName = "13191553676979245.jpg";
        OSSObject ss = ossClient.getObject(BUCKET_NAME,fileName);

    }

    /**
     * 上传视频文件
     * @param is 文件流
     * @param fileName  文件名
     * @return Key
     */
    public String uploadMp42Oss(InputStream is, String fileName){
        String ret = "";
        try {
            //创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(is.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            //如果兼容视频，内容类型需要拓展
            objectMetadata.setContentType("video/mpeg4");
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            objectMetadata.setExpirationTime(new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10));
            //上传文件
            PutObjectResult putResult = ossClient.putObject(BUCKET_NAME, fileName, is, objectMetadata);
            //数据两年之后改为归档存储类型
           /* Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 2);
            PutObjectResult putResult2 = ossClient.putObject(BUCKET_BACKUP, fileName, instream, objectMetadata);*/
            ret = putResult.getETag();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    /**
     * oss 图片压缩处理 上传的时候 不会压缩
     * @param is 流
     * @param fileName 文件名称
     * @return fileName
     */
    public String uploadFile2OSSScala(InputStream is, String fileName){
        String ret = "";
        InputStream instream = null;
        try {
            instream = ImageUtils.metaScala(is);
            //创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(instream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            //如果兼容视频，内容类型需要拓展
            objectMetadata.setContentType("image/jpeg");
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            objectMetadata.setExpirationTime(new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10));
            //上传文件
            PutObjectResult putResult = ossClient.putObject(BUCKET_NAME, fileName, instream, objectMetadata);
            //数据两年之后改为归档存储类型
           /* Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 2);
            PutObjectResult putResult2 = ossClient.putObject(BUCKET_BACKUP, fileName, instream, objectMetadata);*/

            ret = putResult.getETag();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if(instream != null){
                    instream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    public String uploadFile2OSS(InputStream instream, String fileName){
        String ret = "";
        try {
            //创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(instream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            //如果兼容视频，内容类型需要拓展
            objectMetadata.setContentType("image/jpeg");
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            objectMetadata.setExpirationTime(new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10));
            //上传文件
            PutObjectResult putResult = ossClient.putObject(BUCKET_NAME, fileName, instream, objectMetadata);
            //数据两年之后改为归档存储类型
           /* Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 2);
            PutObjectResult putResult2 = ossClient.putObject(BUCKET_BACKUP, fileName, instream, objectMetadata);*/

            ret = putResult.getETag();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                if (instream != null) {
                    instream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    /**
     * 流式下载
     * @param fileName 文件名称
     * @return InputStream
     */
    public InputStream downloadForStream(String fileName){
        // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
        OSSObject ossObject = ossClient.getObject(BUCKET_NAME, fileName);
        if(ValidateUtils.empty(ossObject)){
            throw new IllegalArgumentException("argument is null");
        }
        return ossObject.getObjectContent();
    }

    /**
     * 获得url链接
     *
     * @param key
     * @return
     */
    public String getUrl(String key) {
        // 设置URL过期时间为10年  3600L*1000*24*365*10
        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
        // 设置URL过期时间为一小时
       /* Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000);*/
        // 生成URL
        URL url = ossClient.generatePresignedUrl(BUCKET_NAME, key, expiration);
        if (url != null) {
            return url.toString();
        }
        return null;
    }

    public String openUrl(String fileName){
        return String.format("https://%s.%s/%s",BUCKET_NAME, END_POINT, fileName);
    }

    public static final InputStream getIs(String fileName) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        return fis;
    }

    public static void main(String[] args) throws FileNotFoundException {
        OssClientUtil ss = new OssClientUtil();
        ss.ss();
    }

}
