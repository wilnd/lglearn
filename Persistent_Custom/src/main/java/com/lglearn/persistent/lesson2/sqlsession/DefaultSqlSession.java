package com.lglearn.persistent.lesson2.sqlsession;

import com.lglearn.persistent.lesson2.pojo.Configuration;
import com.lglearn.persistent.lesson2.pojo.MappedStatement;
import lombok.AllArgsConstructor;

import java.lang.reflect.*;
import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    @Override
    public <E> List<E> selectList(String statementId, Object... params) throws Exception {
        //完成simpleExecutor的query方法调用
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getStringMappedStatementMap().get(statementId);
        List<Object> queryDbs = simpleExecutor.query(configuration, mappedStatement, params);
        return (List<E>) queryDbs;
    }

    @Override
    public <T> T selectOne(String statementId, Object... params) throws Exception {
        List<Object> objects = selectList(statementId, params);
        if (objects.size() == 1) {
            return (T) objects.get(0);
        } else {
            throw new RuntimeException("查询结果为空");
        }
    }

    @Override
    public <T> T getMapper(Class<?> mapperClass) throws SQLException {
        //使用jdk动态代理来为Dao接口生成代理对象并返回
        Object proxyInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            /**
             * 生成代理对象
             * @param proxy 当前代理对象的应用
             * @param method 当前被调用方法的引用
             * @param args 传递的参数
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //底层执行jdbc 根据不同情况调用selectList selectOne
                //准备参数 1: statementId sql唯一表示: namespace.id=接口全限定名.方法名
                String methodName = method.getName();
                String className = method.getDeclaringClass().getName();

                String statementId = className + "." + methodName;

                //准备参数2: params:args
                //获取被调用参数的返回值类型
                Type genericReturnType = method.getGenericReturnType();
                if (genericReturnType instanceof ParameterizedType) {
                    return selectList(statementId, args);
                }
                return selectOne(statementId, args);
            }
        });

        return (T) proxyInstance;
    }

}
