package com.hsf.learn.common.utils.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * http 通信工具类
 * @author ogz 19.1.16
 */
@Slf4j
public class HttpClientUtils {

    /**
     * 默认的头部配置
     * @return Header[]
     */
    private static Header[] defHeaders(){

        return new Header[]{
                new BasicHeader("Connection", "keep-alive"),
                new BasicHeader("Accept", "application/json"),
                new BasicHeader("Accept-Language", "zh-CN,zh;q=0.9"),
                new BasicHeader("Content-type", "application/x-www-form-urlencoded"),
                new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
        };

    }


    /**
     * http 请求统一入口
     *      现在没有https证书,先采用绕过证书的方式;等https私钥,在更改实现
     * @param url url
     * @param headers http header
     * @param params 参数
     * @param type 类型
     * @return HttpClientResult
     * @throws IOException
     * @throws URISyntaxException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public static HttpClientResult handler(String url, Map<String, String> headers, Map<String, String> params, HttpMethodType type) throws URISyntaxException, KeyManagementException, NoSuchAlgorithmException, IOException {
            //校验参数
            if(StringUtils.isEmpty(url) || ObjectUtils.isEmpty(type)){
                throw new IllegalArgumentException("illegal arguments");
            }

            HttpClientResult res = null;

            //http get
            if(type.equals(HttpMethodType.GET)){

                res = doGet(url, headers, params);
            //http post
            }else if(type.equals(HttpMethodType.POST)){

                res = doPost(url, headers, params, "params");
            }

            if(ObjectUtils.isEmpty(res) || res.getCode() != HttpStatus.SC_OK){
                throw new IllegalArgumentException("res not received");
            }
            return res;
    }

    /**
     * 请求字符串
     * @param url
     * @param headers
     * @param content
     * @return
     */
    public static HttpClientResult postStringEntity(String url, Map<String, String> headers, String content) throws URISyntaxException, KeyManagementException, NoSuchAlgorithmException, IOException {
        Map<String, String> data = new HashMap<>(4);
        data.put("data", content);
        return doPost(url, headers, data, "stringEntity");
    }

    public static HttpClientResult postStringEntity(String url, Map<String, String> headers, String content, String contentType) throws URISyntaxException, KeyManagementException, NoSuchAlgorithmException, IOException {
        Map<String, String> data = new HashMap<>(4);
        data.put("data", content);
        return doPost(url, headers, data, contentType);
    }

    /**
     * get http
     * @param url api
     * @param headers header
     * @param params 参数
     * @return HttpClientResult
     */
    private static HttpClientResult doGet(String url,
                                          Map<String, String> headers, Map<String, String> params) throws URISyntaxException, IOException, KeyManagementException, NoSuchAlgorithmException {

        //采用绕过验证的方式处理https请求
        SSLContext sslcontext = verifySSLIgnore();

        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sslcontext))
                .build();

        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);

        //创建httpClient
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connManager).build();

        //访问地址
        URIBuilder uriBuilder = new URIBuilder(url);

        if(null != params ){
            for(Map.Entry<String, String> entry : params.entrySet()){
                uriBuilder.setParameter(entry.getKey(), entry.getValue());
            }
        }

        HttpGet get = new HttpGet(uriBuilder.build());

        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(6000)
                .setSocketTimeout(6000).build();
        get.setConfig(config);

        //自定义的头部设置
        packageHeader(headers, get);

        CloseableHttpResponse httpResponse = null;
        try {
            return getHttpClientResutl(httpResponse, httpClient, get);
        } finally {
            release(httpResponse, httpClient);
        }

    }


    /**
     * http post
     * @param url api
     * @param headers header
     * @param params 参数
     * @return HttpClientResult
     * @throws IOException
     */
    private static HttpClientResult doPost(String url,
                                           Map<String, String> headers, Map<String, String> params, String contentType) throws IOException, KeyManagementException, NoSuchAlgorithmException {

        //采用绕过验证的方式处理https请求
        SSLContext sslcontext = verifySSLIgnore();

        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sslcontext))
                .build();

        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);

        //创建httpClient
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connManager).build();


        //创建http对象
        HttpPost httpPost = new HttpPost(url);

        /**
         * 设置请求超时的时间， 连接超时的时间
         */
        RequestConfig requestConfig = RequestConfig
                .custom()
                .setConnectTimeout(6000)
                .setSocketTimeout(6000)
                .build();

        httpPost.setConfig(requestConfig);
        if(contentType.equals("params")){
            List<NameValuePair> nvps = new ArrayList<>(4);
            if(params != null){
                for (Map.Entry<String, String> entry : params.entrySet()){
                    nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
            }

            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
        }else {
            StringEntity se = new StringEntity(params.get("data"), "UTF-8");
            if(StringUtils.isEmpty(contentType)){
                se.setContentType(contentType);
            }else {
                se.setContentType("application/json");
            }
            httpPost.setEntity(se);
        }

        packageHeader(headers, httpPost);

        CloseableHttpResponse httpResponse = null;

        try {

            return getHttpClientResutl(httpResponse, httpClient, httpPost);

        } finally {
            release(httpResponse, httpClient);
        }
    }

    /**
     * header 打包
     * @param params header param
     * @param httpMethod 调用的接口
     */
    private static void packageHeader(Map<String, String> params, HttpRequestBase httpMethod){
        if(null != params){
            for(Map.Entry<String, String> entry : params.entrySet()){
                httpMethod.setHeader(entry.getKey(), entry.getValue());
            }
        }else {
            for(Header header : defHeaders()){
                httpMethod.setHeader(header);
            }
        }
    }


    /**
     * 获取客户端响应的结果
     * @param httpResponse 响应类
     * @param httpClient 客户端
     * @param httpMethod
     * @return
     */
    private static HttpClientResult getHttpClientResutl(CloseableHttpResponse httpResponse,
                                                        CloseableHttpClient httpClient, HttpRequestBase httpMethod) throws IOException {
        httpResponse = httpClient.execute(httpMethod);
        //获取响应结果
        if(httpResponse != null && httpResponse.getStatusLine() != null){
            String content = "";
            if(httpResponse.getEntity() != null){
                content = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            }
            return new HttpClientResult(httpResponse.getStatusLine().getStatusCode(), content);
        }

        return new HttpClientResult(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }


    /**
     * 资源释放
     * @param httpResponse 响应
     * @param httpClient 客户端
     * @throws IOException
     */
    private static void release(CloseableHttpResponse httpResponse, CloseableHttpClient httpClient) throws IOException {

        if(httpResponse != null){
            httpResponse.close();
        }

        if(httpClient != null){
            httpClient.close();
        }
    }

    /**
     * https 绕过SSL验证
     * @return SSLContext
     */
    private static SSLContext verifySSLIgnore() throws NoSuchAlgorithmException, KeyManagementException {

        SSLContext sc = SSLContext.getInstance("SSLv3");
        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };

        sc.init(null, new TrustManager[] { trustManager }, null);
        return sc;
    }

    public static void main(String[] args) throws URISyntaxException, KeyManagementException, NoSuchAlgorithmException, IOException {
        Map<String, String> params = new HashMap<>(4);
        params.put("data", "[1,1,100,100,255,255]");
        Map<String, String> headers = new HashMap<>(2);
        headers.put("Content-type", "application/json;charset=utf-8");
        HttpClientResult res = HttpClientUtils.doPost("http://106.15.57.66:8099/record/facade/tnl/event/01/861480032997621/1/bc3118/1/1/1565659658258", headers, params, "data");
        System.out.println(res.toString());
    }

}
