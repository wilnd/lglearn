package com.lglearn.factory;

import com.lglearn.base.StringUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * xpath
 * nodename 选取次节点的所有子节点
 * /        从根节点选取
 * //       从匹配选择的当前节点选择文档中的节点,不考虑他们的文职
 * .        选取当前节点
 * ..       选取当前节点的父节点
 * @        选取属性
 */
public class BeanFactory {

    private static final Map<String, Object> MAP = new HashMap<>();

    static {
        InputStream resourceAsStream = BeanFactory.class.getClassLoader().getResourceAsStream("beans.xml");
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(resourceAsStream);
            Element rootElement = document.getRootElement();

            //1. 处理bean元素
            List<Element> beans = rootElement.selectNodes("//bean");
            for (Element element : beans) {
                String id = element.attributeValue("id");
                String clazz = element.attributeValue("class");

                Class<?> aClass = Class.forName(clazz);
                Object o = aClass.newInstance();
                MAP.put(id, o);
            }

            //2. 处理property元素
            List<Element> properties = rootElement.selectNodes("//property");
            for (Element property : properties) {
                String name = property.attributeValue("name");
                String ref = property.attributeValue("ref");

                //2.1 获取父对象
                Element parent = property.getParent();
                String id = parent.attributeValue("id");
                Object parentObj = MAP.get(id);
                Object refObj = MAP.get(ref);

                if (parentObj == null || refObj == null) {
                    System.out.println("parent is null or ref is null id =" + id + "ref + " + ref);
                    continue;
                }

                //2.2 获取福对象的所有方法
                Method[] methods = parentObj.getClass().getMethods();
                for (Method method : methods) {
                    if (method.getName().equalsIgnoreCase("set" + name)) {
                        method.invoke(parentObj, refObj);
                    }
                }

                //把处理后的parentObj重新放入到MAP
                MAP.put(id, parentObj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object getBean(String id) {
        if (StringUtil.isBlank(id)) {
            return null;
        }
        return MAP.get(id);
    }

}
