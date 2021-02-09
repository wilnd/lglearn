package lglearn.utils;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component("proxyFactory")
public class ProxyFactory {

    @Autowired
    private TransactionManager transactionManager;

    public Object getServiceTransactionProxy(Object paramObj) {
        return Enhancer.create(paramObj.getClass(), new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                Object invokeResult = null;
                try {
                    transactionManager.beginTransaction();
                    invokeResult = method.invoke(paramObj, args);
                    transactionManager.commit();
                } catch (Exception e) {
                    transactionManager.rollBack();
                    throw e;
                }
                return invokeResult;
            }
        });
    }

}
