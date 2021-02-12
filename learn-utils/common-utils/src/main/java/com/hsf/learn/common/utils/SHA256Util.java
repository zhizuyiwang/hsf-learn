/**   
* @Title: SHA256Util.java 
* @Package com.beaconem.common.util
* @Description: TODO(用一句话描述该文件做什么) 
* @author hb  
* @date 2017年11月1日 上午10:16:39 
* @version V1.0   
*/
package com.hsf.learn.common.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.HmacUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @ClassName: SHA256Util
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author hb
 * @date 2017年11月1日 上午10:16:39
 */

public class SHA256Util {
	private static final String salt_key1 = "dns";
	// private static final String salt_key2 = "lookup";
	// private static final String salt_key3 = "beaconem_request";
	public final static SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyyMMdd");

	public static String hmac(String secret, String message, String saltKey) throws IOException {
		return hmac(System.currentTimeMillis(), secret, message, saltKey);
	}

	public static String hmac(long time, String secret, String message, String saltKey) throws IOException {
		String yymmdd = getUTCTimeStr(time);
		// 原始参数基本sha
		byte[] messageByte = DigestUtils.sha256(message);

		// 第1次用时间与str做HMAC签名
		byte[] kDate = HmacUtils.hmacSha256(secret, yymmdd);

		// 第2次拿签名后的2进制与salt_key1 再做一次 hmac签名
		byte[] kRegion = HmacUtils.hmacSha256(kDate, saltKey.getBytes(StandardCharsets.UTF_8));

		// 第3次拿签名后的2进制kRegion与salt_key2 再做一次 hmac签名
		// byte[] kService = HmacUtils.hmacSha256(kRegion, salt_key2.getBytes("utf-8"));

		// 第3次拿签名后的2进制kService与salt_key3 再做一次 hmac签名
		// byte[] sigkey = HmacUtils.hmacSha256(kService, salt_key3.getBytes("utf-8"));

		// 计算最后签名，sigkey 和 原始参数基本sha的值计算
		String sig = HmacUtils.hmacSha256Hex(kRegion, messageByte);
		return sig;
	}

	private static String getUTCTimeStr(long time) {
		// 1、取得本地时间：
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		// 2、取得时间偏移量：
		int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
		// 3、取得夏令时差：
		int dstOffset = cal.get(Calendar.DST_OFFSET);
		// 4、从本地时间里扣除这些差量，即可以取得UTC时间：
		cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));

		return DATE_FORMATTER.format(cal.getTime());
	}

	public static void main(String[] args) {
		try {
			System.out.println(hmac("ZTaBVtwj8tpSOhExEi3D", "deviceId=xxxxS_54c9df03346c&productKey=80beb199-dcf&unifiedId=18681452232@beaconem.phone.cn1467410034550", salt_key1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
