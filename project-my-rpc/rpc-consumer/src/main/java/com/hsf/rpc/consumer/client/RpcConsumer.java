package com.hsf.rpc.consumer.client;

import com.hsf.rpc.consumer.handler.UserClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RpcConsumer {

    //创建线程池对象，用来处理我们自定义的事件处理器，这里是在线程中执行客户端给服务端的写操作
    private static ExecutorService executorService =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private static UserClientHandler userClientHandler = null;

    /**
     * 初始化客户端
     */
    public static void initClient() throws InterruptedException {
        userClientHandler = new UserClientHandler();
        //1、创建连接池对象
        NioEventLoopGroup group = new NioEventLoopGroup();
        //2、创建连接请求启动引导对象
        Bootstrap bootstrap = new Bootstrap();
        //3、配置启动引导对象
        bootstrap.group(group)
                //设置通道类型
                .channel(NioSocketChannel.class)
                //设置请求协议为TCP
                .option(ChannelOption.TCP_NODELAY, true)
                //监听channel并初始化
                .handler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        //获取通道处理管道
                        ChannelPipeline pipeline = socketChannel.pipeline();

                        //设置解码
                        pipeline.addLast(new StringDecoder());
                        //设置编码
                        pipeline.addLast(new StringEncoder());
                        //添加自定义的处理器
                        pipeline.addLast(userClientHandler);
                    }
                });
        //4、连接服务器，这里调用同步的连接方法
        bootstrap.connect("127.0.0.1",8999).sync();
    }

    /**
     * 使用JDk动态代理创建接口服务的子类实现对象，当客户端使用服务接口的代理对象调用方法时，
     * 执行的方法就会走invoke逻辑，在里面可以发送Socket消息到服务端，从而获取服务端发回来的响应
     * @param userService
     * @param params 客户端调用远程服务传递的参数
     */
    public static Object createProxy(Class<?> userService, final String params){
        Object object = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class<?>[]{userService}, new InvocationHandler() {
                    /**
                     * 当代理对象调用远程方法时，走此方法的逻辑
                     * @param proxy
                     * @param method
                     * @param args
                     * @return
                     * @throws Throwable
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //1、第一次调用远程服务，客户端的服务还没有初始化，先初始化客户端
                        if(userClientHandler == null){
                            initClient();
                        }
                        String providerParams = params + args[0];
                        //2、设置请求服务端参数
                        userClientHandler.setParams(providerParams);
                        //3、使用线程池，开启一个线程执行call()方法，执行写操作，并返回结果
                        Object result = executorService.submit(userClientHandler).get();

                        return result;
                    }
                });
        return object;
    }

}
