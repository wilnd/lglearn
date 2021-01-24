package com.lglearn.persistent.lesson2.configure;

import com.lglearn.persistent.lesson2.io.Resource;
import com.lglearn.persistent.lesson2.pojo.Configuration;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import lombok.NoArgsConstructor;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

@NoArgsConstructor
public class XMLConfigBuilder {

    private Configuration configuration;

    /**
     * 该方法就是将SqlMapConfig配置文件解析
     */
    public Configuration parseConfig(InputStream in) throws DocumentException, PropertyVetoException {
        if (configuration != null){
            return configuration;
        }
        Configuration configuration = new Configuration();
        Document document = new SAXReader().read(in);
        //<configuration>
        Element rootElement = document.getRootElement();

        List<Element> elements = rootElement.selectNodes("//property");
        Properties properties = new Properties();
        elements.forEach(element -> {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            properties.setProperty(name, value);
        });

        //连接池
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(properties.getProperty("driverClass"));
        comboPooledDataSource.setJdbcUrl(properties.getProperty("jdbcUrl"));
        comboPooledDataSource.setUser(properties.getProperty("user"));
        comboPooledDataSource.setPassword(properties.getProperty("password"));

        configuration.setDataSource(comboPooledDataSource);

        //解析mapper.xml
        List<Element> mapperElements = rootElement.selectNodes("//mapper");
        mapperElements
                .stream()
                .map(mapperElement -> mapperElement.attributeValue(
                        "resource"))
                .map(Resource::getResourceAsStream)
                .forEach(inputStream -> parse(inputStream, configuration));
        this.configuration = configuration;
        return this.configuration;
    }

    private void parse(InputStream resourceAsStream, Configuration configuration) {
        XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(configuration);
        try {
            xmlMapperBuilder.parse(resourceAsStream);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
