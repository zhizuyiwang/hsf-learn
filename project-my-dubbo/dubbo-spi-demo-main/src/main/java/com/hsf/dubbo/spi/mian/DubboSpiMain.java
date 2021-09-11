package com.hsf.dubbo.spi.mian;

import com.hsf.dubbo.spi.service.EatFood;
import com.hsf.dubbo.spi.service.HelloService;
import org.apache.dubbo.common.extension.ExtensionLoader;

import java.util.Set;

public class DubboSpiMain {
    public static void main(String[] args) {
        // 获取扩展加载器
        ExtensionLoader<HelloService> extensionLoader = ExtensionLoader.getExtensionLoader(HelloService.class);
        // 遍历所有的支持的扩展点 META-INF.dubbo
        Set<String> extensions = extensionLoader.getSupportedExtensions();
        for (String string : extensions){
            String msg = extensionLoader.getExtension(string).sayHello();
            System.out.println(msg);
        }

        // 获取扩展加载器
        ExtensionLoader<EatFood> extensionLoader1 = ExtensionLoader.getExtensionLoader(EatFood.class);
        // 遍历所有的支持的扩展点 META-INF.dubbo
        Set<String> extensions1 = extensionLoader1.getSupportedExtensions();
        for (String string : extensions1){
            String msg = extensionLoader1.getExtension(string).eat();
            System.out.println(msg);
        }

    }
}
