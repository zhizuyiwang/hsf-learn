package com.hsf.my.batis.config;

import com.hsf.my.batis.io.Resources;
import com.hsf.my.batis.pojo.Configuration;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class XmlConfigureBuilder {
    private Configuration configuration;

    public XmlConfigureBuilder(Configuration configuration){
        this.configuration = configuration;
    }

    public Configuration parseConfiguration(InputStream inputStream) throws DocumentException, PropertyVetoException, ClassNotFoundException {
        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();//<configuration>标签
        List<Element> propertyElements = rootElement.selectNodes("//property");
        Properties properties = new Properties();
        for (Element propertyElement : propertyElements){
            String name = propertyElement.attributeValue("name");
            String value = propertyElement.attributeValue("value");
            properties.setProperty(name, value);
        }

        //连接池
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(properties.getProperty("driverClass"));
        comboPooledDataSource.setJdbcUrl(properties.getProperty("jdbcUrl"));
        comboPooledDataSource.setUser(properties.getProperty("user"));
        comboPooledDataSource.setPassword(properties.getProperty("password"));

        //填充 configuration
        configuration.setDataSource(comboPooledDataSource);

        //mapper部分
        List<Element> mapperElements = rootElement.selectNodes("//mapper");
        XmlMapperBuilder xmlMapperBuilder = new XmlMapperBuilder(configuration);

        for (Element mapperElement : mapperElements){
            String mapperPath = mapperElement.attributeValue("resource");
            InputStream resourceAsSteam = Resources.getResourceAsStream(mapperPath);
            xmlMapperBuilder.parse(resourceAsSteam);
        }

        return configuration;
    }
}
