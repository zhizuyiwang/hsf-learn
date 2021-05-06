package com.hsf.learn.demo.mytomcat;

import java.io.IOException;

public class LoginServlet extends HttpServlet {


    @Override
    public void doGet(Request request, Response response){
        String getContent = "<h1>loginServlet get</h1>";
        try {
            response.output(HttpProtocolUtil.getHttpHeader200(getContent.getBytes().length)+getContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(Request request, Response response) {
        String postContent = "<h1>loginServlet post</h1>";
        try {
            response.output(HttpProtocolUtil.getHttpHeader200(postContent.getBytes().length)+postContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() throws Exception {

    }

    @Override
    public void destroy() throws Exception {

    }
}
