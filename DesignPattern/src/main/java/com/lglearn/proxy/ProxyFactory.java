package com.lglearn.proxy;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProxyFactory {

    private static volatile ProxyFactory factory;

    public static synchronized ProxyFactory getInstance() {
        if (factory == null) {
            factory = new ProxyFactory();
        }
        return factory;
    }

    /**
     * jdk动态代理
     * 要求委托对象必须实现接口
     * @param obj 委托对象
     * @return 代理对象
     */
    public Object getJdkProxy(Object obj) {
        //类加载器, 增强的接口, 增强的逻辑
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandler() {
            /**
             * @param proxy 代理对象本身
             * @param method 代理的方法
             * @param args 入参
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("中介1");
                Object result = method.invoke(obj, args);
                System.out.println("增强功能1");
                return result;
            }
        });
    }

    public Object getCglibProxy(Object proxyedObj) {
        return Enhancer.create(RentingHouseImpl.class, (MethodInterceptor) (obj, method, args, proxy) -> {
            System.out.println("中介2");
            Object result = method.invoke(proxyedObj, args);
            System.out.println("增强功能2");
            return result;
        });
    }
}
