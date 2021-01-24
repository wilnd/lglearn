package com.lglearn.persistent.lesson2.io;

import java.io.InputStream;

public class Resource {

    //根据路径获取配置文件放到内存中
    public static InputStream getResourceAsStream(String path) {
        return Resource.class.getClassLoader().getResourceAsStream(path);
    }

}
