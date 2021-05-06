package com.hsf.rpc.consumer.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

public class UserClientHandler extends ChannelInboundHandlerAdapter implements Callable {
    //1、定义成员变量
    private ChannelHandlerContext context;//事件处理器上下文对象，存储handler信息，写操作
    private String result;//记录服务端返回的结果
    private String params;//记录发送给服务器的参数

    /**
     * 客户端与服务端连接时，回调此方法，保存处理器上下文对象
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        context = ctx;
    }
    /**
     * 当服务端写入数据到通道后，客户端的通道回调此方法，从通道中读取数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //把服务器返回的数据赋值到成员变量result中等待输出
        result = msg.toString();
        //唤醒线程，返回数据
        notify();
    }

    /**
     * 此方法用来给服务端发送数据，也就是调用远程服务方法走的逻辑
     * @return
     * @throws Exception
     */
    @Override
    public synchronized Object call() throws Exception {
        //发送数据到服务端，在服务端返回数据之前阻塞此方法
        context.writeAndFlush(params);
        wait();
        return result;
    }
    public void setParams(String params){
        this.params = params;
    }
}
