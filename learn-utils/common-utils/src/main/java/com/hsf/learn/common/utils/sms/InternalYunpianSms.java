package com.hsf.learn.common.utils.sms;

import com.hsf.learn.common.utils.http.HttpClientResult;
import com.hsf.learn.common.utils.http.HttpClientUtils;
import com.hsf.learn.common.utils.http.HttpMethodType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;


/**
 * 云片短信
 * 仅针对与短信
 * @author ogz 19.1.9
 */
public class InternalYunpianSms {

    private static final Logger log = LoggerFactory.getLogger(InternalYunpianSms.class);

    /**
     * 授权
     */
    private static final String API_KEY = "257b42a0e9a6d2f58fc9dcb176fa24da";

    /**
     * 单个短信发送
     */
    private static final String SINGLE_SMS_URL = "https://sms.yunpian.com/v2/sms/single_send.json";


    /**
     * 智能匹配模板
     * @param text 短信内容
     * @param mobile 电话
     * @return HttpClientResult
     * @throws IOException network server error
     * @throws URISyntaxException url error
     */
    public static HttpClientResult sendSms(String text, String mobile) throws IOException {
        try {
            Map<String, String> params = new HashMap<>(4);
            params.put("apikey", API_KEY);
            params.put("text", text);
            params.put("mobile", mobile);
            log.info("倍肯向 receiver={} 发送短信 mes={}", mobile, text);
            return HttpClientUtils.handler(SINGLE_SMS_URL, null, params, HttpMethodType.POST);
        } catch (Exception e) {
            log.error("服务器 向用户={} 发送={} 网络异常 msg={}", mobile, text, e.getMessage());
            throw new IOException("io server error");
        }
    }
    
    public static void main(String[] args) throws IOException {
    	HttpClientResult jsob = InternalYunpianSms.sendSms("123456", "13918042264");
    	System.out.println(jsob.toString());
	}
}
