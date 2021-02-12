package com.hsf.learn.common.auth;

import com.hsf.learn.common.utils.constants.RequestConstants;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class BaseFilter {
	protected static List<String> excludeUri = new ArrayList<String>();
	
	protected static List<String> excludeOtherUri = new ArrayList<String>();

	/**
	 * 初始化免验签uri列表
	 */
	static {
		excludeUri.add(RequestConstants.CATALOG + "/auth");
		excludeOtherUri.add(RequestConstants.CATALOG + "/logout");
	}

	/**
	 * 判断该uri是否在免验签中
	 * 
	 * @param contextPath
	 * @param uri
	 * @return
	 */
	protected boolean isFreeAccess(String contextPath, String uri) {
		uri = uri.substring(contextPath.length());
		log.info(String.format("the uri is %s after contextpath removed ", uri));
		for (String item : excludeUri) {
			if (uri.matches(item))
				return true;
		}
		return false;
	}
	
	/**
	 * 判断其他不需要任何header的判断
	 * 
	 * @param contextPath
	 * @param uri
	 * @return
	 */
	protected boolean isOtherFreeAccess(String contextPath, String uri) {
		uri = uri.substring(contextPath.length(), uri.length());
		log.info(String.format("the uri is %s after contextpath removed ", uri));
		for (String item : excludeOtherUri) {
			if (uri.matches(item))
				return true;
		}
		return false;
	}
}
