package com.lglearn.persistent.lesson2.sqlsession;

import com.google.common.collect.Lists;
import com.lglearn.persistent.lesson2.configure.BoundSql;
import com.lglearn.persistent.lesson2.pojo.Configuration;
import com.lglearn.persistent.lesson2.pojo.MappedStatement;
import com.lglearn.persistent.lesson2.utils.GenericTokenParser;
import com.lglearn.persistent.lesson2.utils.ParameterMapping;
import com.lglearn.persistent.lesson2.utils.ParameterMappingTokenHandler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleExecutor implements Executor {

    /**
     * @param configuration   配置信息
     * @param mappedStatement 配置文件sql信息
     * @param params          查询入参
     * @param <E>
     * @return
     * @throws SQLException
     */
    @Override
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception {
        //1. 注册驱动, 获取连接
        Connection connection = configuration.getDataSource().getConnection();
        //2. 获取sql语句,并进行转换
        String sql = mappedStatement.getSql();
        BoundSql boundSql = getBoundSql(sql);
        //3. 获取与处理对象 preparedStatement
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSql());
        //4. 设置参数
        for (int i = 0; i < boundSql.getParameterMappingList().size(); i++) {
            ParameterMapping parameterMapping = boundSql.getParameterMappingList().get(i);
            //获取sql的入参类型
            String parameterType = mappedStatement.getParameterType();
            Class<?> parameterTypeClass = getClassType(parameterType);
            //反射
            String content = parameterMapping.getContent();
            Field declaredField = parameterTypeClass.getDeclaredField(content);
            declaredField.setAccessible(true);
            Object o = declaredField.get(params[0]);
            preparedStatement.setObject(i + 1, o);
        }
        //5. 执行sql
        ResultSet resultSet = preparedStatement.executeQuery();
        String resultType = mappedStatement.getResultType();
        Class<?> resultTypeClass = getClassType(resultType);
        ArrayList<Object> objects = Lists.newArrayList();
        //6. 封装返回结果集
        while (resultSet.next()) {
            Object o = resultTypeClass.newInstance();
            //元数据
            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                //字段名
                String columnName = metaData.getColumnName(i);
                //字段值
                Object value = resultSet.getObject(columnName);
                //使用反射 根据数据库表和实体的对应关系完成封装
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultTypeClass);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(o, value);
            }
            objects.add(o);
        }
        return (List<E>) objects;
    }

    private Class<?> getClassType(String parameterType) throws ClassNotFoundException {
        if (parameterType != null) {
            Class<?> parameterClass = Class.forName(parameterType);
            return parameterClass;
        }
        return null;
    }

    /**
     * 1. 将sql中的#{}, 解析成 ? 的形式
     * 2. 解析出#{} 里的值进行存储
     */
    private BoundSql getBoundSql(String sql) {
        //标记处理类: 配置标记解析器完成对占位符的解析处理工作
        ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", tokenHandler);
        //处理成已完成解析的sql
        String parsedSql = genericTokenParser.parse(sql);
        //#{}里面解析的参数名称
        List<ParameterMapping> parameterMappings = tokenHandler.getParameterMappings();

        return new BoundSql(parsedSql, parameterMappings);
    }
}
