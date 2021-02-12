package com.hsf.learn.common.redis;


/**
 * RedisKey前缀
 * @author inspire
 * @date 2019/6/6.
 */
public interface RedisKeyConstants {

    /**
     * 用户登录后KEY的前缀
     */
    public static final String USER_LOGIN_MESSAGE_KEY = "LOGIN:MESSAGE:";

    /**
     * 登录时输入手机号重试Key的前缀
     */
    public static final String USER_LOGIN_RETRY_KEY = "LOGIN:RETRY:";

    /**
     * 重试次数
     */
    public static final Integer USER_LOGIN_RETRY_LIMIT = 2;

    /**
     * 数据同步：数据记录ID前缀
     */
    public static final String REPORT_DATA_SYN_KEY = "REPORT:SYN:ID";

}
