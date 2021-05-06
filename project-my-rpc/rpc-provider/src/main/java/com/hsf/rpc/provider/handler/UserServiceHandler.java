package com.hsf.rpc.provider.handler;

import com.hsf.rpc.provider.service.UserServiceImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 自定义业务处理器
 */
public class UserServiceHandler extends ChannelInboundHandlerAdapter {

    /**
     * 此方法是服务端的socketChannel读取数据，也就是说，当客户端的socketChannel
     * 在发送数据，也就就是写入数据后，服务端的socketChannel会回调此方法
     * 一个连接有两个socket，服务端的socket和客户端的socket Io操作是相反的
     * @param ctx
     * @param msg，是客户端写入的数据
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //注意:  客户端将来发送请求的时候会传递一个参数:  UserService#sayHello#are you ok
        //1.判断当前的请求是否符合规则
        if(msg.toString().startsWith("UserService")){
            //2、如果符合规则，则调用实现类的相应方法，并返回处理结果（也就是将处理结果写回到客户端）
            UserServiceImpl userService = new UserServiceImpl();
            String result = userService.sayHello(msg.toString().
                    substring(msg.toString().lastIndexOf("#")+1));
            //3.把调用实现类的方法获得的结果写到客户端
            ctx.channel().writeAndFlush(result);
        }

    }
}
