package com.lglearn.persistent.lesson2.configure;

import com.lglearn.persistent.lesson2.pojo.Configuration;
import com.lglearn.persistent.lesson2.pojo.MappedStatement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

@Data
@AllArgsConstructor
public class XMLMapperBuilder {

    private Configuration configuration;

    public void parse(InputStream inputStream) throws DocumentException {
        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();

        String namespace = rootElement.attributeValue("namespace");
        List<Element> selectElements = rootElement.selectNodes("//select");

        selectElements.forEach(selectElement -> {
            MappedStatement mappedStatement = new MappedStatement();
            String id = selectElement.attributeValue("id");
            mappedStatement.setId(id);
            mappedStatement.setResultType(selectElement.attributeValue("resultType"));
            mappedStatement.setParameterType(selectElement.attributeValue("parameterType"));
            mappedStatement.setSql(selectElement.getTextTrim());

            String key = namespace + "." + id;

            configuration.getStringMappedStatementMap().put(key, mappedStatement);
        });
    }

}
