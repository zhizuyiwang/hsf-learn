package com.hsf.learn.demo.mytomcat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class Bootstrap {

    private int port = 8070;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    /**
     * 1、相应文字输出
     * @throws IOException
     */
    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("服务器启动====端口号" + port);
        while (!serverSocket.isClosed()){
            Socket accept = serverSocket.accept();
            System.out.println("服务器收到连接====");

            OutputStream outputStream = accept.getOutputStream();
            String data = "hello cat";
            String responseText = HttpProtocolUtil.getHttpHeader200(data.getBytes().length) + data;
            outputStream.write(responseText.getBytes());
            outputStream.close();
            accept.close();
        }
    }

    /**
     * 2、响应客户端请求的静态资源
     * @throws IOException
     */
    public void start2() throws IOException {

        ServerSocket serverSocket = new ServerSocket(port);
        while (true){
            Socket socket = serverSocket.accept();

            //请求输入流
            InputStream inputStream = socket.getInputStream();
            //请求输出流
            OutputStream outputStream = socket.getOutputStream();

            //将输入流封装成request对象
            Request request = new Request(inputStream);
            //将输出流封装成response对象
            Response response = new Response(outputStream);
            //将客户端请求的静态资源输出出去
            response.outputHtml(request.getUrl());

            socket.close();
        }
    }

    /**
     * 3、响应客户端请求的动态资源（servlet）
     * @throws IOException
     */
    public void start3() throws Exception {

        //加载配置文件，获取配置的servlet
        loadServlet();

        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("服务器启动====端口号" + port);

        while (true){
            Socket socket = serverSocket.accept();
            System.out.println("服务器收到连接====");

            //请求输入流
            InputStream inputStream = socket.getInputStream();
            //请求输出流
            OutputStream outputStream = socket.getOutputStream();

            //将输入流封装成request对象
            Request request = new Request(inputStream);
            //将输出流封装成response对象
            Response response = new Response(outputStream);

            //说明客户端请求的不是servlet，而是静态资源
            if(servletMap.get(request.getUrl()) == null){
                response.outputHtml(request.getUrl());
            }else{
                HttpServlet httpServlet = servletMap.get(request.getUrl());
                httpServlet.service(request, response);
            }
            socket.close();
        }
    }

    /**
     * 4、响应客户端请求的动态资源，使用线程处理
     * @throws IOException
     */
    public void start4() throws IOException {
        //加载配置文件，获取配置的servlet
        loadServlet();

        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("服务器启动====端口号" + port);

        while (true){
            Socket socket = serverSocket.accept();
            System.out.println("服务器收到连接====");
            RequestProcessor requestProcessor = new RequestProcessor(socket, servletMap);
            requestProcessor.start();
        }
    }

    /**
     * 4、最终兼容静态，动态资源请求，应用线程池的完整代码
     * @throws IOException
     */
    public void startResult() throws IOException {
        // 1、加载解析相关的配置，web.xml
        loadServlet();

        //2、定义线程池
        int corePoolSize = 10;
        int maximumPoolSize =50;
        long keepAliveTime = 100L;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(50);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                threadFactory,
                handler
        );

        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("服务器启动====端口号" + port);

        while (true){
            Socket socket = serverSocket.accept();
            System.out.println("服务器收到连接====");
            RequestProcessor requestProcessor = new RequestProcessor(socket, servletMap);
            threadPoolExecutor.execute(requestProcessor);
        }
    }

    private Map<String,HttpServlet> servletMap = new HashMap<>();
    private void loadServlet() {


    }
    public static void main(String[] args){
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.startResult();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
