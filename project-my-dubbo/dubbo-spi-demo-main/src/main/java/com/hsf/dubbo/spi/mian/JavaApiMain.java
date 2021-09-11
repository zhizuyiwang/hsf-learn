package com.hsf.dubbo.spi.mian;

import com.hsf.dubbo.spi.service.HelloService;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;


public class JavaApiMain {
    public static void main(String[] args) {
        URL url = URL.valueOf("test://localhost/hello?hello.service=dogHello");
        final HelloService adaptiveExtension = ExtensionLoader.getExtensionLoader(HelloService.class).getAdaptiveExtension();
        System.out.println(adaptiveExtension.sayHello(url));
    }
}
