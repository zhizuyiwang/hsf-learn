package com.hsf.learn.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.Objects;

/**
 * 16进制byte[] 与 hex String 相互转换工具类
 * @author ogz 19.1.31
 */
public class HexByteUtils {

    private static final Logger log = LoggerFactory.getLogger(HexByteUtils.class);
    private static final String API_HEX_RANGE = "0123456789ABCDEF";

    /**
     * 有符号处理
     * @param res 初始
     * @return Integer
     */
    public static Integer signedToInteger(byte[] res){
        BigInteger bi = new BigInteger(Objects.requireNonNull(bytesToHexString(res)), 16);
        return bi.intValue();
    }


    /**
     * Convert byte[] to hex string.这里我们可以将byte转换成int，然后利用Integer.toHexString(int)来转换成16进制字符串。
     * @param src byte[] data
     * @return hex string
     */
    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static String bytesToHexFormatStr(byte[] src){
        String temp = bytesToHexString(src);
        return temp.length() % 2 == 0 ? temp : String.format("0%s", temp);
    }

    public static String bytesToHexStringFormat(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv).append(" ");
            if((i + 1) % 8 == 0){
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Convert hex string to byte[]
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

    private static byte charToByte(char c){
        return (byte)API_HEX_RANGE.indexOf(c);
    }

    /**
     * 将byte[] 以16进制字符串进行打印
     */
    public static void hexStringPrint(byte[] b){

        if(ValidateUtils.empty(b)){return;}

        int len = b.length;

        StringBuilder sb = new StringBuilder(50);

        for(int i = 0 ; i < len ; i ++){
            String hex = Integer.toHexString(b[i] & 0xFF);
            //对8取余
            if(i % 8 == 0){
                sb.append("\n");
            }
            if(hex.length() == 1){
                sb.append("0");
            }
            sb.append(hex).append(" ");
        }

        System.out.println(sb.toString());
    }

    /**
     * 将int转换成16进制的字符串
     * @param b 数值
     * @return hexString
     */
    public static String toHexString(int b) {
        return hexStringFormat(Integer.toHexString(b));
    }

    private static String hexStringFormat(String s){
        if(s.length() %2 == 1){
            StringBuilder sb = new StringBuilder(2);
            sb.append("0").append(s);
            return sb.toString().toUpperCase();
        }
        return s.toUpperCase();
    }

    /**
     * 10进制转换成16进制字符串
      * @param n 十进制值
     * @return String
     */
    public static String toHexString(long n){
        return hexStringFormat(Long.toHexString(n));
    }


    public static byte[] toHex(int val) { return hexStringToBytes(toHexString(val));}

    public static byte[] toHexFormat(int val, int len) {
        byte[] res = toHex(val);
        int last = len - res.length;
        if(last == 0){
            return res;
        }
        if (last > 0) {
            byte[] temp = new byte[len];
            for( int i = 0; i < last; i ++){
                temp[i] = 0x00;
            }
            System.arraycopy(res, 0, temp, last, res.length);
            return temp;
        }
        throw new ArrayIndexOutOfBoundsException("超过限定长度");
    }

    /**
     * 16进制字符串转换成10进制
     * @param n hexString
     * @return long
     */
    public static long hexStringToLong(String n){
        return Long.parseLong(n, 16);
    }

    /**
     * 16进制字符串转换成10进制
     * @param n hexString
     * @return int
     */
    public static int hexStringToInteger(String n){
        return Integer.parseInt(n, 16);
    }

    /**
     * 高低位保留最后1个字节
     * @param sum 和
     * @return byte[]
     */
    public static byte[] hl(long sum){
        //获取16进制的累加和字符串
        String sumHexString = toHexString(sum);
        //截取最后2位
        int sl = sumHexString.length();
        String c = null;
        if(sl <= 2){
            c = sumHexString;
        }else{
            c = sumHexString.substring(sl-2);
        }
        return hexStringToBytes(c);
    }

    /**
     * 大端模式
     * @param vl 数值
     * @return byte[]
     */
    public static byte[] highLowAll(int vl){
        return hexStringToBytes(toHexString(vl));
    }

    public static byte sumNegative(byte[] src){
        int len = src.length;
        int sum = 0;
        for(int i = 1; i < len; i ++ ){
            int s = src[i] & 0xFF;
            sum = sum + s;
        }
        //累加和截取最后一个字节
        byte[] b = hl(sum);
        //若数组为null
        if(ValidateUtils.empty(b)){
            throw new IllegalArgumentException("args null");
        }
        //byte 取反
        return (byte)~ b[0];
    }

    public static byte sumNegative(byte[] src, int sub){
        int len = src.length - sub;
        int sum = 0;
        for(int i = 0; i < len; i ++ ){
            int s = src[i] & 0xFF;
            sum = sum + s;
        }
        //累加和截取最后一个字节
        byte[] b = hl(sum);
        //若数组为null
        if(ValidateUtils.empty(b)){
            throw new IllegalArgumentException("args null");
        }
        //byte 取反
        return (byte)~ b[0];
    }

    /**
     * 校验码
     * @param src hex字节组
     * @return boolean
     */
    public static boolean validateCode(byte[] src){
            if(ValidateUtils.empty(src) || src.length <= 3){
                return false;
            }
            int len   = src.length;
            int last  = len - 3;
            byte code = src[len - 2];
            int sum = 0;
            for(int i = 1; i <= last; i ++ ){
                int s = src[i] & 0xFF;
                sum = sum + s;
            }
            //获取16进制的累加和字符串
            String sumHexString = toHexString(sum);
            //截取最后2位
            int sl = sumHexString.length();
            String c = sumHexString.substring(sl-2);
            //取反
            byte[] b = hexStringToBytes(c);
            //若数组为null
            if(ValidateUtils.empty(b)){return false;}
            //byte 取反
            byte reverse = (byte)~ b[0];

            return toHexString(reverse & 0xFF).equals(toHexString(code & 0xFF));
    }



    public static void main(String[] args) {
        byte[] t = new byte[22];
        t[0] = 0x40;
        t[1] = 0x00;
        byte[] t1 = hexStringToBytes("40");
        System.out.println(bytesToHexString(t1));
    }
}
