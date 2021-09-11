package com.hsf.dubbo.filter;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

@Activate(group = {CommonConstants.CONSUMER,CommonConstants.PROVIDER})
public class DubboTimerFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        try {
            System.out.println("服务方法执行前");
            return invoker.invoke(invocation);
        }finally {
            System.out.println("服务方法执行后");
        }

    }
}
