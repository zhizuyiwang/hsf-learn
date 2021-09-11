package com.hsf.my.batis.config;

import com.hsf.my.batis.pojo.Configuration;
import com.hsf.my.batis.pojo.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

public class XmlMapperBuilder {

    private Configuration configuration;
    public XmlMapperBuilder(Configuration configuration){
        this.configuration = configuration;
    }

    public void parse(InputStream inputStream) throws DocumentException, ClassNotFoundException {
        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();//<mapper>标签

        String namespace = rootElement.attributeValue("namespace");
        List<Element> selectElements = rootElement.selectNodes("select");

        for (Element selectElement : selectElements){
            String id = selectElement.attributeValue("id");
            String parameterType = selectElement.attributeValue("parameterType");
            String resultType = selectElement.attributeValue("resultType");

            //输入参数class
            Class<?> parameterClassType = getClassType(parameterType);
            //输出结果class
            Class<?> resultClassType = getClassType(resultType);
            //sql语句
            String textTrim = selectElement.getTextTrim();
            //statementId
            String key = namespace + "." + id;

            //封装MappedStatement
            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setId(id);
            mappedStatement.setParameterType(parameterClassType);
            mappedStatement.setResultType(resultClassType);
            mappedStatement.setSql(textTrim);

            //填充 configuration
            configuration.getMappedStatementMap().put(key,mappedStatement);
        }


    }
    private Class<?> getClassType(String classPath) throws ClassNotFoundException {
        Class<?> aClass = Class.forName(classPath);
        return aClass;
    }
}
