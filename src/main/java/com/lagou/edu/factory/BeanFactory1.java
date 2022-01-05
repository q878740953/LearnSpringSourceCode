package com.lagou.edu.factory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public class BeanFactory1 {

    public static HashMap<String, Object> map = new HashMap<>();

    /**
     * 任务一：读取xml文件，通过反射技术去实例化xml中的对象，并通过id和对象存储到map中
     * 任务二：通过xml文件中的id，去获取对应的对象  对外提供获取实例对象的接口（根据id获取）
     */
    static {
        // 任务一：读取解析xml，通过反射技术实例化对象并且存储待用（map集合）
        // 通过类加载器的getResourceAsStream方法加载xml文件得到流对象
        InputStream inputStream = BeanFactory1.class.getClassLoader().getResourceAsStream("beans.xml");
        // 通过demo4j去解析xml
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(inputStream);
            // 获取根节点
            Element rootElement = document.getRootElement();
            // 通过xpath表达式，查找所有bean节点
            List<Element> nodes = rootElement.selectNodes("//bean");
            for (Element node : nodes) {
                // 获取子节点上的值
                String id = node.attributeValue("id");
                // 获取需要实例化对象的全限定类名
                String clazz = node.attributeValue("class");
                // 通过反射技术实例化对象
                Class<?> aClass = Class.forName(clazz);
                // 实例化对象
                Object o = aClass.newInstance();
                // 使用id  对象的方式存入到map中
                map.put(id, o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * dui外提供接口获取指定的对象
     */
    public static Object getBean(String id){
        return map.get(id);
    }

    public static void main(String[] args) {
        Object accountDao = map.get("accountDao");
        System.out.println(accountDao);
    }
}
