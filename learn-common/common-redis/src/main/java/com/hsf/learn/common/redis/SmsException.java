package com.hsf.learn.common.redis;

/**
 *
 * @author inspire
 * @date 2019/6/6
 * @apiNote SMS异常
 */
public class SmsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    protected final String message;

    public SmsException(String message)
    {
        this.message = message;
    }

    public SmsException(String message, Throwable e)
    {
        super(message, e);
        this.message = message;
    }

    @Override
    public String getMessage()
    {
        return message;
    }
}
