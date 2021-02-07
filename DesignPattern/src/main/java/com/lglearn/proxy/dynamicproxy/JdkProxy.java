package com.lglearn.proxy.dynamicproxy;

import com.lglearn.proxy.IRentingHouse;
import com.lglearn.proxy.RentingHouseImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy {

    public static void main(String[] args) {
        RentingHouseImpl rentingHouse = new RentingHouseImpl();
        //获取代理对象
        //类加载器, 增强的接口, 增强的逻辑
        IRentingHouse proxyInstance =(IRentingHouse)Proxy.newProxyInstance(rentingHouse.getClass().getClassLoader(), rentingHouse.getClass().getInterfaces(), new InvocationHandler() {
            /**
             *
             * @param proxy 代理对象本身
             * @param method 代理的方法
             * @param args 入参
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("中介");
                Object result = method.invoke(rentingHouse, args);
                System.out.println("增强功能2");
                return result;
            }
        });
        proxyInstance.rentHouse();
    }

}
