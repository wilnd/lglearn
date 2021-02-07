package com.lglearn.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProxyFactory {

    private static volatile ProxyFactory factory;

    public static synchronized ProxyFactory getInstance() {
        if (factory == null) {
            factory = new ProxyFactory();
        }
        return factory;
    }

    public Object getServiceTransactionProxy(Object paramObj) {
        return Enhancer.create(paramObj.getClass(), new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                Object invokeResult = null;
                try {
                    TransactionManager.getInstance().beginTransaction();
                    invokeResult = method.invoke(paramObj, args);
                    TransactionManager.getInstance().commit();
                } catch (Exception e) {
                    TransactionManager.getInstance().rollBack();
                    throw e;
                }
                return invokeResult;
            }
        });
    }

}
