package com.hsf.learn.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.NumberFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUitl {
	public static Logger logger = LoggerFactory.getLogger(CommonUitl.class);
	public static final String LOCAL_IP_ADDRESS = (CommonUitl.getLocalIP() == null ? getHostIp() : CommonUitl.getLocalIP().getHostAddress());

	/**
	 * 
	 * @Title: getHostIp;
	 * @Description: 获取当前主机IP或用户;
	 * @param @param netAddress
	 * @param @return;
	 * @return String; @throws;
	 */
	public static String getHostIp() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * instanceof Inet4Address 新的系统，已经没有eth0这种命名方式
	 */
	public static InetAddress getLocalIP() {
		InetAddress address = null;
		try {
			NetworkInterface ni = NetworkInterface.getByName("eth0");
			if (ni != null) {
				Enumeration<InetAddress> ias = ni.getInetAddresses();
				for (; ias.hasMoreElements();) {
					InetAddress ia = ias.nextElement();
					if (!ia.isLoopbackAddress() && ia.isSiteLocalAddress())
						address = ia;
				}
			}
		} catch (Exception e1) {
			logger.warn("getLocalIP eth0 not found, get first one ", e1);
		}

		if (address != null) {
			logger.warn("getLocalIP eth0 : " + address);
			return address;
		}

		Enumeration<NetworkInterface> interfaces = null;
		try {
			interfaces = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			logger.error("getLocalIP", e);
		}

		if (interfaces != null) {
			while (interfaces.hasMoreElements()) {
				NetworkInterface i = interfaces.nextElement();
				Enumeration<InetAddress> addresses = i.getInetAddresses();
				while (addresses.hasMoreElements()) {
					InetAddress ia = addresses.nextElement();
					if (!ia.isLoopbackAddress() && ia.isSiteLocalAddress()) {
						address = ia;
					}
				}
			}
		}
		return address;
	}

	/**
	 * 
	 * @Title: numberFormat;
	 * @Description: 获取固定长度代码
	 * @param @param number
	 * @param @param min
	 * @param @param max
	 * @param @return;
	 * @return String; @throws;
	 */
	public static String numberFormat(long number, int min, int max) {
		// 得到一个NumberFormat的实例
		NumberFormat nf = NumberFormat.getInstance();
		// 设置是否使用分组
		nf.setGroupingUsed(false);
		// 设置最大整数位数
		nf.setMaximumIntegerDigits(max);
		// 设置最小整数位数
		nf.setMinimumIntegerDigits(min);
		return nf.format(number);
	}

	/**
	 * 
	 * @Title: numberFormat;
	 * @Description: 获取固定长度代码
	 * @param @param number
	 * @param @param min
	 * @param @param max
	 * @param @return;
	 * @return String; @throws;
	 */
	public static String numberFormat(String number, int min, int max) {
		// 得到一个NumberFormat的实例
		NumberFormat nf = NumberFormat.getInstance();
		// 设置是否使用分组
		nf.setGroupingUsed(false);
		// 设置最大整数位数
		nf.setMaximumIntegerDigits(max);
		// 设置最小整数位数
		nf.setMinimumIntegerDigits(min);
		return nf.format(number);
	}

	/**
	 * 
	 * @Title: sortMapByKey;
	 * @Description: 按照MAP的KEY排序
	 * @param @param oriMap
	 * @param @return;
	 * @return Map<String,String>; @throws;
	 */
	public static Map<String, String> sortMapByKey(Map<String, String> oriMap) {
		if (oriMap == null || oriMap.isEmpty()) {
			return null;
		}
		Map<String, String> sortedMap = new TreeMap<String, String>(new Comparator<String>() {
			public int compare(String key1, String key2) {
				return key1.compareTo(key2);
			}
		});
		sortedMap.putAll(oriMap);
		return sortedMap;
	}

	/**
	 * 
	 * @Title: sortMapByValue;
	 * @Description: Map排序按照值排序
	 * @param @param oriMap
	 * @param @return;
	 * @return Map<String,String>; @throws;
	 */
	public static Map<String, String> sortMapByValue(Map<String, String> oriMap) {
		Map<String, String> sortedMap = new LinkedHashMap<String, String>();
		if (oriMap != null && !oriMap.isEmpty()) {
			List<Entry<String, String>> entryList = new ArrayList<Entry<String, String>>(oriMap.entrySet());
			Collections.sort(entryList, new Comparator<Entry<String, String>>() {
				public int compare(Entry<String, String> entry1, Entry<String, String> entry2) {
					return entry1.getValue().compareTo(entry2.getValue());
				}
			});
			Iterator<Entry<String, String>> iter = entryList.iterator();
			Entry<String, String> tmpEntry = null;
			while (iter.hasNext()) {
				tmpEntry = iter.next();
				sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
			}
		}
		return sortedMap;
	}

	/**
	 * 获取map中第一个数据值
	 *
	 * @param map 数据源
	 * @return 返回的值
	 */
	public static Entry<String, String> getFirstOrNull(Map<String, String> map) {
		Entry<String, String> obj = null;
		for (Entry<String, String> entry : map.entrySet()) {
			obj = entry;
			if (entry.getKey() != null) {
				break;
			}
		}
		return obj;
	}

	/**
	 * 将int数值转换为占四个字节的byte数组，本方法适用于(低位在前，高位在后)的顺序。 和bytesToInt（）配套使用
	 * 
	 * @param value 要转换的int值
	 * @return byte数组
	 */
	public static byte[] intToBytes(int value) {
		byte[] src = new byte[4];
		src[3] = (byte) ((value >> 24) & 0xFF);
		src[2] = (byte) ((value >> 16) & 0xFF);
		src[1] = (byte) ((value >> 8) & 0xFF);
		src[0] = (byte) (value & 0xFF);
		return src;
	}

	/**
	 * 将int数值转换为占四个字节的byte数组，本方法适用于(高位在前，低位在后)的顺序。 和bytesToInt2（）配套使用
	 */
	public static byte[] intToBytes2(int value) {
		byte[] src = new byte[4];
		src[0] = (byte) ((value >> 24) & 0xFF);
		src[1] = (byte) ((value >> 16) & 0xFF);
		src[2] = (byte) ((value >> 8) & 0xFF);
		src[3] = (byte) (value & 0xFF);
		return src;
	}

	/**
	 * Convert hex string to byte[]
	 * 
	 * @param hexString the hex string
	 * @return byte[]
	 */
	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	/**
	 * Convert char to byte
	 * 
	 * @param c char
	 * @return byte
	 */
	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	/**
	 * 验证邮箱
	 *
	 * @param email
	 * @return
	 */

	public static boolean checkEmail(String email) {
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证手机号码，11位数字，1开通，第二位数必须是3456789这些数字之一 *
	 * 
	 * @param mobileNumber
	 * @return
	 */
	public static boolean checkMobileNumber(String mobileNumber) {
		boolean flag = false;
		try {
			// Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
			Pattern regex = Pattern.compile("^1[345789]\\d{9}$");
			Matcher matcher = regex.matcher(mobileNumber);
			flag = matcher.matches();
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;

		}
		return flag;
	}
	
	public static String getPrintSize(long size) {
		return getPrintSize(size, true);
	}

	public static String getPrintSize(long size, boolean isunit) {

		// PB
		if (size >= 1125899906842624l) {
			return String.format("%.3f", size / (1125899906842624L * 1.0)) + (isunit ? " PB" : "");
		}

		// TB
		if (size >= 10995116277760L) {
			size = size * 1000 / (1099511627776L);
			return String.format("%.3f", (size / 1000.0)) + (isunit ? " TB" : "");
		}

		// GB
		if (size >= 1073741824L) {
			size = size * 1000 / 1073741824L;
			return String.format("%.3f", (size / 1000.0)) + (isunit ? " GB" : "");
		}

		// MB
		if (size >= 1048576) {
			size = size * 1000 / 1048576;
			return String.format("%.2f", (size / 1000.0)) + (isunit ? " MB" : "");
		}

		// KB
		if (size >= 1024) {
			size = size * 1000 / 1024;
			return String.format("%.2f", (size / 1000.0)) + (isunit ? " KB" : "");
		} else {
			return size + (isunit ? " B" : "");
		}
	}

}
