package com.lglearn.test;

import com.lglearn.mapper.OrderMapper;
import com.lglearn.pojo.OrderUser;
import com.lglearn.pojo.UserOrder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisMultyTest {

    @Test
    public void testOneReflectOne() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        List<OrderUser> orderUsers = mapper.findOrderByUser();
        orderUsers.forEach(orderUser -> System.out.println(orderUser));
    }

    @Test
    public void testOneToMulties() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);

        List<UserOrder> all = mapper.findAllUser();
        all.forEach(orderUser -> System.out.println(orderUser));
    }

}
