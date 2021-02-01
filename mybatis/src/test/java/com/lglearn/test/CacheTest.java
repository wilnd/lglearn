package com.lglearn.test;

import com.lglearn.mapper.OrderMapper;
import com.lglearn.mapper.OrderUserMapper;
import com.lglearn.mapper.UserMapper;
import com.lglearn.pojo.Order;
import com.lglearn.pojo.User;
import com.lglearn.pojo.UserOrder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CacheTest {

    private OrderUserMapper orderUserMapper;

    private SqlSessionFactory sqlSessionFactory;

    @BeforeEach
    public void before() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
         sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        orderUserMapper = sqlSession.getMapper(OrderUserMapper.class);
    }


    @Test
    public void firstCache() {
        //第一次查询id为1的用户
        List<Order> user1 = orderUserMapper.findOrderByUid(1);

        Order order = new Order();
        order.setId(1);
        order.setTotal(3001.1);
        orderUserMapper.updateOrder(order);

        //第二次查询id为1的用户
        List<Order> user2 = orderUserMapper.findOrderByUid(1);

        //判断两次是不是拿到的同一个对象
        System.out.println(user1 == user2);
    }

    @Test
    public void findSecondCache(){
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        SqlSession sqlSession3 = sqlSessionFactory.openSession();

//        sqlSession1.select();

//        UserMapper mapper1 = sqlSession1.getMapper(UserMapper.class);
//        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);
//        UserMapper mapper3 = sqlSession3.getMapper(UserMapper.class);
//
//        User user1 = mapper1.findUserById(1);
//        sqlSession1.close();//清空一级缓存
//        User user2 = mapper2.findUserById(1);
//        user2.setUsername("ch");
//        mapper3.updateMyUser(user2);
//        User user3 = mapper3.findUserById(1);
    }

}
