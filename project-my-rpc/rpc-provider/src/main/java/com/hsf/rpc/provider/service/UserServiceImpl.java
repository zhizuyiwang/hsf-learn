package com.hsf.rpc.provider.service;

import com.hsf.rpc.common.service.IUserService;
import com.hsf.rpc.provider.handler.UserServiceHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

public class UserServiceImpl implements IUserService {

    /**
     * 客户端调用的方法，服务处理逻辑
     * @param msg
     * @return
     */
    @Override
    public String sayHello(String msg) {
        return "Are you ok？ "+msg;
    }

    /**
     * 真正启动服务
     * @param ip
     * @param port
     */
    public static void startServer(String ip, int port) throws InterruptedException {
        //1、创建两个线程池对象
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        //2、创建服务端的启动引导对象
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        //3、配置启动引导对象
        serverBootstrap.group(bossGroup,workGroup)
                //设置通道类型
                .channel(NioServerSocketChannel.class)
                //创建监听chanel
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    /**
                     * 当有新的连接时，会回调此方法，初始化通道
                     * @param SocketChannel
                     * @throws Exception
                     */
                    @Override
                    protected void initChannel(SocketChannel SocketChannel) throws Exception {
                        //获取通道处理管道
                        ChannelPipeline pipeline = SocketChannel.pipeline();
                        //给管道对象pipeLine 设置编码
                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(new StringEncoder());
                        //添加自定义的处理器到pipeLine
                        pipeline.addLast(new UserServiceHandler());
                    }
                });
        //绑定端口
        serverBootstrap.bind(port).sync();
    }
}
