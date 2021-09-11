package com.hsf.learn.demo.upload;

import org.apache.http.util.TextUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import javax.net.ssl.*;
import java.io.*;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;

public class JsoupHttpRequest implements X509TrustManager{
    public static String USER_AGENT = "Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 5.0)";
    public static void main(String[] args) throws Exception {
       /* String url = "https://10.10.11.98:1443/iotcenter/file-manager-service/v1/file/manager/upload";
        File file = new File("D:\\tmp\\tmp\\1234567890.jpg");
        String fileRquestParam = "file";
        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("uploadType", "1");
        dataMap.put("fileFolder", "adf6c538606f4275bf74332493eca5a9");
        Response response = doPostFileRequest(url, dataMap, file, fileRquestParam);
        System.out.println(response.statusMessage());*/
        /*String url = "https://10.10.11.98:1443/iotcenter/file-manager-service/v1/file/manager/download?fileFolder=adf6c538606f4275bf74332493eca5a9&amp;fileName=1234567890.jpg";
        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("fileFolder", "adf6c538606f4275bf74332493eca5a9");
        dataMap.put("fileName","1234567890.jpg");
        getFileRequest(url,  dataMap);*/
       /* String fileName = "1234567890.jpg";
        String savePath = "D:\\tmp";
        downLoadFromUrlHttps(url, fileName,
                savePath);*/
     //uploadFile();

       // -X GET --header 'X-HW-ID: iotcenter.key' --header 'X-HW-APPKEY: 4gm5DBho73p4lfBcRaHKVQ====' --insecure -o test.txt --progress


      /*  String[] cmds = {"curl", "-X", "GET",
                "https://10.10.11.98:1443/iotcenter/file-manager-service/v1/file/manager/download?fileFolder=adf6c538606f4275bf74332493eca5a9&fileName=1234567890.jpg",
                "-H", "X-HW-ID: iotcenter.key", "-H", "X-HW-APPKEY: 4gm5DBho73p4lfBcRaHKVQ====", "-d"
                , "{ \"fileFolder\": \"adf6c538606f4275bf74332493eca5a9\", \"fileName\": \"1234567890.jpg\" }","--insecure","-o","1234567890.jpg","--progress"};
        String command = " curl https://10.10.11.98:1443/iotcenter/file-manager-service/v1/file/manager/download?fileFolder=adf6c538606f4275bf74332493eca5a9\\&fileName=1234567890.jpg ";
        StringBuilder sbstr = new StringBuilder(command);
        sbstr.append(" -X GET --header 'X-HW-ID: iotcenter.key' --header 'X-HW-APPKEY: 4gm5DBho73p4lfBcRaHKVQ====' --insecure -o 1234567890.jpg --progress");
        String[] cmd = new String[]{"cmd", "/c", sbstr.toString()};
        ProcessBuilder builder = new ProcessBuilder(cmd);
        //both read inputstream and errstream
        builder.redirectErrorStream(true);
        Process process = builder.start();
        InputStream inputStream = process.getInputStream();
        byte[] getData = readInputStream(inputStream);
        String savePath = "D:\\tmp\\82\\test.txt";
        System.out.println("size2:"+getData.length);
        // 文件保存位置
        File saveDir = new File(savePath);
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }
        //输出流
        File file = new File(savePath);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if (fos != null) {
            fos.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }*/

        // System.out.println(execCurl(cmds));
        /*String url = "https://10.10.11.98:1443/iotcenter/file-manager-service/v1/file/manager/download?fileFolder=adf6c538606f4275bf74332493eca5a9&amp;fileName=1234567890.jpg";
        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("fileFolder", "adf6c538606f4275bf74332493eca5a9");
        dataMap.put("fileName","1234567890.jpg");
        getFileRequest(url,  dataMap);*/
       // downloadFile();
     //   uploadFile();
        uploadFileBySeaweedFS();
    }

    public static String execCurl(String[] cmds) throws Exception {
        ProcessBuilder process = new ProcessBuilder(cmds);
        Process p;
        String savePath = "D:\\tmp\\82\\1234567890.jpg";
        try {
            p = process.start();
            // 得到输入流
            InputStream inputStream = p.getInputStream();
            byte[] getData = readInputStream(inputStream);
            System.out.println("size:"+getData.length);
            // 文件保存位置
            File saveDir = new File(savePath);
            if (!saveDir.exists()) {
                saveDir.mkdirs();
            }
            //输出流
            File file = new File(savePath);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(getData);
            if (fos != null) {
                fos.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }

            /*BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
            }
            return builder.toString();

        } catch (IOException e) {
            System.out.print("error");
            e.printStackTrace();
        }*/
        } catch (IOException e) {
                System.out.print("error");
                e.printStackTrace();
            }
            return null;
        }


    public static boolean uploadFile() {
        boolean result = false;
        String url = "https://10.10.11.98:1443/iotcenter/file-manager-service/v1/file/manager/upload";
        File file = new File("D:\\tmp\\tmp\\1234567.jpg");
        String fileRquestParam = "file";
        try {
            Map<String, String> dataMap = new HashMap<String, String>();
            dataMap.put("uploadType", "1");
            dataMap.put("fileFolder", "adf6c538606f4275bf74332493eca5a9");
            Response response = doPostFileRequest(url, dataMap, file, fileRquestParam);
            result = true;
            System.out.println(response.statusMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
          return result;
    }

    public static boolean uploadFileBySeaweedFS() {


        boolean result = false;
        File file = new File("F:\\123.txt");
        System.out.println(file.length());
        String url = "http://192.168.0.130:18100/beacon/api/mqtt/cmd/v1/upload/file";
        String imei = "123456";
        String proType = "bc3661";
        String timeStamp = Long.toString(new Date().getTime());
        String serverHttpsUrl = url + "/" + imei + "/" + proType + "/1/" + timeStamp;
        System.out.println(serverHttpsUrl);
        String fileRquestParam = "file";
        try {
            Map<String, String> dataMap = new HashMap<String, String>();
            Response response = doPostFileRequest(serverHttpsUrl, null, file, fileRquestParam);
            result = true;
           System.out.println(response.body().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean downloadFile() {
        boolean result = false;
        String url = "https://10.10.11.98:1443/file-manager-service/v1/file/manager/download";
        try {
            Map<String, String> dataMap = new HashMap<String, String>();
            dataMap.put("fileFolder", "adf6c538606f4275bf74332493eca5a9");
            dataMap.put("fileName","1234567.jpg");
            Response response = getFileRequest(url, dataMap);
            result = true;
            System.out.println(response.statusMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    /**
     * @param url              请求的Url
     * @param paramMap         参数
     * @param file             文件
     * @param fileRequestParam form表单对应的文件name属性名
     * @return
     * @throws Exception
     */
    public static Response doPostFileRequest(String url, Map<String, String> paramMap, File file, String fileRequestParam) throws Exception {

        // Https请求
        if (url.contains("htts")) {
            System.out.println("With https start.");
            trustEveryone();
        }
        Connection connection = Jsoup.connect(url);
        connection.method(Connection.Method.POST);
        connection.timeout(1200);
        connection.header("Content-Type", "multipart/form-data");
        //connection.header("X-HW-ID","iotcenter.key");
        //connection.header("X-HW-APPKEY","838Aw6hrI192ePBDsSBNJA==");

        connection.ignoreHttpErrors(true);
        connection.ignoreContentType(true);
        if (paramMap != null && !paramMap.isEmpty()) {
            connection.data(paramMap);
        }
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            connection.data(fileRequestParam, file.getName(), fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Response response = connection.execute();
            System.out.println("返回状态："+response.statusCode());


            return response;
        } catch (IOException e) {
            System.out.println("Run error.");
            e.printStackTrace();
        } finally {
            if(null != fis) {
                fis.close();
                fis = null;
            }
        }
        return null;
    }

    public static Response getFileRequest(String url, Map<String, String> paramMap) throws Exception {
        if (TextUtils.isEmpty(url)) {
            throw new Exception("The request URL is blank.");
        }
        // Https请求
        if (url.contains("https")) {
          trustEveryone();
        }
        Connection connection = Jsoup.connect(url);
        connection.header("X-HW-ID","iotcenter.key");
        connection.header("X-HW-APPKEY","838Aw6hrI192ePBDsSBNJA==");
        connection.userAgent("Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.15)");
        connection.header("Content-Type", "application/octet-stream");
        connection.ignoreHttpErrors(true);
        connection.ignoreContentType(true);
        connection.method(Connection.Method.GET);
        connection.timeout(12000).get();
        if (paramMap != null && !paramMap.isEmpty()) {
            connection.data(paramMap);
        }
        Response response = connection.execute();
        System.out.println("文件类型为：" + response.contentType());
        System.out.println("返回状态：" + response.statusCode());

        byte[] bytes = response.bodyAsBytes();
        File saveFile = new File("D:\\tmp\\1234567890.jpg");
        if(!saveFile.exists())
        {
            try {
                saveFile.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        Thread.sleep(100);
        FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
        fileOutputStream.write(bytes);
        fileOutputStream.flush();
        fileOutputStream.close();
        System.out.println("----Download file successfully.---");
        return response;
    }

    /**
     * 解决Https请求,返回404错误
     */
    public static void trustEveryone() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {

                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 从输入流中获取字节数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] readInputStream(InputStream inputStream)
            throws IOException {
        byte[] b = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = null;
        try {
             bos = new ByteArrayOutputStream();
            while ((len = inputStream.read(b)) != -1) {
                bos.write(b, 0, len);
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(null != bos) {
                bos.close();
            }
        }
        return bos.toByteArray();
    }

    /***
     * 校验https网址是否安全
     *
     * @author solexit06
     *
     */
    public class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            // 直接返回true:默认所有https请求都是安全的
            return true;
        }
    }

    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

    }

    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

    }

    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }
}
