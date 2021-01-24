package com.lglearn.persistent.lesson2.sqlsession;

import com.lglearn.persistent.lesson2.configure.XMLConfigBuilder;
import com.lglearn.persistent.lesson2.pojo.Configuration;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream in) throws PropertyVetoException, DocumentException {
        //1. 利用dom4j解析配置文件
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parseConfig(in);

        //创建sqlSessionFactory
        return new DefaultSqlSessionFactory(configuration);
    }

}
