package com.lglearn.test;

import com.lglearn.mapper.IUserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisSourceTest {

    public void test() throws IOException {
        // 1. 读取配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        //2. 1)解析配置文件,封装Configuration对象 2)创建DefaultSqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //3. 1)生成DefaultSqlSession对象 2)设置了事物 3)创建了executor
        SqlSession sqlSession = sqlSessionFactory.openSession();

        sqlSession.commit();

        //4. 1) 根据statementId从configuration中的mapper集合中获取到指定MappedStatement对象
        //   2) 将查询操作委派给executor执行器
        List<Object> objects = sqlSession.selectList("namespace.id");
    }

    public void test2() throws IOException {
        // 1. 读取配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        //2. 1)解析配置文件,封装Configuration对象 2)创建DefaultSqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //3. 1)生成DefaultSqlSession对象 2)设置了事物 3)创建了executor
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //4. 使用jdk动态代理对mapper接口产生代理对象
        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);

        //代理对象调用接口中的任意方法, 执行的都是动态代理中的invoke方法
        //invoke 最终调用的是sqlSession中的增删改方法
//        mapper.selectOne()

    }

}
