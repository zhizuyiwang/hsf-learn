package com.hsf.learn.demo.mytomcat;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;

public class RequestProcessor extends Thread{

    private Socket socket;
    private Map<String, HttpServlet> servletMap;
    public RequestProcessor(Socket socket, Map<String, HttpServlet> servletMap) {
        this.socket = socket;
        this.servletMap = servletMap;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            //分别把请求的输入流和输出流封装成Request和Response对象
            Request request = new Request(inputStream);
            Response response = new Response(outputStream);
            //说明请求的url不是servlet是静态资源
            if(servletMap.get(request.getUrl()) == null){
                response.outputHtml(request.getUrl());
            }else{
                // 动态资源servlet请求
                HttpServlet httpServlet = servletMap.get(request.getUrl());
                httpServlet.service(request, response);
            }
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
