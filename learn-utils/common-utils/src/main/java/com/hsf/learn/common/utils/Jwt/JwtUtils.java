package com.hsf.learn.common.utils.Jwt;

import org.apache.commons.lang3.StringUtils;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.Key;

/**
 * JWT的工具类Utils
 */
public class JwtUtils {
	private static Logger logger = LoggerFactory.getLogger(JwtUtils.class);
	/**
	 * <p> 获取jwt的token
	 * @param mappingValue 需要转换的值
	 * @return
	 */
	public static String getTokenStr(Key key, String mappingValue){
		 JsonWebEncryption jwe = new JsonWebEncryption();
		 jwe.setPayload(mappingValue);
		 jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128KW);
		 jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
		 jwe.setKey(key);
		 String serializedJwe;
		try {
			serializedJwe = jwe.getCompactSerialization();
			return serializedJwe;
		} catch (JoseException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	public static String transToken(Key key, String token){
		JsonWebEncryption jwe = new JsonWebEncryption();
		jwe.setKey(key);
		try {
			jwe.setCompactSerialization(token);
			return jwe.getPayload();
		} catch (JoseException e) {
			logger.error(e.getMessage());
		}
		 return null;
	}
	/**
	 * <p> 根据相应的key获取value值
	 * @param secretKey 密钥串
	 * @param value
	 * @return
	 * @throws JoseException 
	 * @throws UnsupportedEncodingException
	 */
	public static String getTokenHS256(String secretKey, String value) throws JoseException, UnsupportedEncodingException {
		if(StringUtils.isBlank(secretKey) || StringUtils.isBlank(value)) return null;
		Key key = new HmacKey(secretKey.getBytes("UTF-8"));

		JsonWebSignature jws = new JsonWebSignature();
		jws.setPayload(value);
		jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);
		jws.setKey(key);
		String jwt = jws.getCompactSerialization();
		return jwt;
	}
	/**
	 * 
	 * @param secretKey
	 * @param token
	 * @return 加密后的json字符串
	 * @throws UnsupportedEncodingException
	 * @throws InvalidJwtException 
	 */
	public static String parserTokenHS256(String secretKey, String token) throws UnsupportedEncodingException, InvalidJwtException{
		if(StringUtils.isBlank(secretKey) || StringUtils.isBlank(token)) return null;
		Key key = new HmacKey(secretKey.getBytes("UTF-8"));
		
		JwtConsumer jwtConsumer = new JwtConsumerBuilder()
	        .setVerificationKey(key)
	        .setRelaxVerificationKeyValidation()
	        .build();

		JwtClaims processedClaims = jwtConsumer.processToClaims(token);
		if(processedClaims == null) return null;
		return processedClaims.getRawJson();
	}
	/**
	 * test
	 * @param args
	 * @throws UnsupportedEncodingException
	 * @throws InvalidJwtException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException, InvalidJwtException {
		
//		byte[] feeds = {-107, -90, -85, 127, 32, 39, 73, 127, -109, 93, 25, 10, -91, 14, -18, -34};
//		Key key = new AesKey(feeds);
		//String value="{\"id\":12,\"name\":\"张晓锋\"}";
//		String value="64560";
//		String tokenStr = getTokenStr(key, value);
//		System.out.println(String.format("----获取的token为:%s-----", tokenStr));
//		
//		String payload = transToken(key, tokenStr);
//		System.out.println(String.format("----解析的token为:%s-----", payload));
//		 {"user_id":5,"exp":1796004741}
//		String result = parserTokenHS256("b46823df6b3b43eab8ee91eea18d72d5",
//				"eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiJiNDJiNjdkNDg0M2M0NzFlYjZlMGFjMjMzOWRjYTZlNiIsIm5vbmNlIjoxNDkwNjgxNjgwNzY2LCJ1c2VyX2lkIjo2NDc1fQ.CvnYoGE2BVJS8Q84TpYDWksAYaf0HaR4xf3U2DU5myo");
//		System.out.println(result);
	}

}
