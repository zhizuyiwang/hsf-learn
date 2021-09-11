package com.hsf.spring.lagou;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工厂类，生产对象（使用反射技术）
 */
public class BeanFactory {
    //1、读取解析xml配置文件，使用反射技术实例化对象并存储在map中待用
    private static Map<String,Object> mapCache = new HashMap<>();

    static {
        // 任务一：读取解析xml，通过反射技术实例化对象并且存储待用（map集合）
        //加载xml
        InputStream resourceAsStream = BeanFactory.class.getClassLoader().getResourceAsStream("Beans.xml");
        //解析xml
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(resourceAsStream);
            Element rootElement = document.getRootElement();
            List<Element> beanList = rootElement.selectNodes("//bean");
            for (int i = 0; i < beanList.size(); i ++){
                Element element = beanList.get(i);
                //处理每一个bean元素，获取该元素的id和class属性
                String id = element.attributeValue("id");
                String clazz = element.attributeValue("class");
                //使用反射技术实例化对象
                Class<?> aClass = Class.forName(clazz);
                Object object = aClass.newInstance();
                mapCache.put(id, object);
            }
            // 实例化完成之后维护对象的依赖关系，检查哪些对象需要传值进入，根据它的配置，我们传入相应的值
            // 有property子元素的bean就有传值需求
            List<Element> propertyList = rootElement.selectNodes("//property");
            // 解析property，获取父元素
            for (int i = 0; i < propertyList.size(); i++) {
                Element element = propertyList.get(i);
                String name = element.attributeValue("name");
                String ref = element.attributeValue("ref");

                // 找到当前需要被处理依赖关系的bean
                String parentId = element.getParent().attributeValue("id");
                Object parentObject = mapCache.get(parentId);
                Method[] methods = parentObject.getClass().getMethods();
                // 遍历父对象中的所有方法，找到"set" + name
                for (int j = 0; j < methods.length; j++) {
                    Method method = methods[j];
                    if(method.getName().equalsIgnoreCase("set"+name)){
                        method.invoke(parentObject, mapCache.get(ref));
                    }
                }
                mapCache.put(parentId,parentObject);

            }
        }catch (DocumentException e){
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    //2、对外提供获取实例对象的接口,根据xml中的id获取
    public static Object getBean(String id){
        return mapCache.get(id);
    }

}
