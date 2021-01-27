package com.lglearn.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lglearn.mapper.IUserMapper;
import com.lglearn.mapper.OrderMapper;
import com.lglearn.mapper.OrderUserMapper;
import com.lglearn.pojo.OrderUser;
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

public class OrderTest {

    private OrderMapper orderMapper;

    private OrderUserMapper orderUserMapper;

    private IUserMapper iUserMapper;

    @BeforeEach
    public void before() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        orderMapper = sqlSession.getMapper(OrderMapper.class);
        orderUserMapper = sqlSession.getMapper(OrderUserMapper.class);
        iUserMapper = sqlSession.getMapper(IUserMapper.class);
    }

    @Test
    public void mapperTest(){
        User user = new User();
        user.setId(1);

        User userDb = iUserMapper.selectOne(user);
        System.out.println(userDb);
    }

    @Test
    public void pageHelperTest(){
        PageHelper.startPage(1,1);
        List<User> users = orderMapper.selectUser();
        PageInfo<User> userPageInfo = new PageInfo<>(users);
        System.out.println(userPageInfo);
    }

    @Test
    public void manyToMany(){
        List<UserOrder> allUserAndRole = orderUserMapper.findAllUserAndRole();
        for (UserOrder userOrder : allUserAndRole) {
            System.out.println(userOrder.toString());
        }
    }

    @Test
    public void oneToMany(){
        List<UserOrder> userAndOrder = orderUserMapper.findUserAndOrder();
        for (UserOrder userOrder : userAndOrder) {
            System.out.println(userOrder.toString());
        }
    }

    @Test
    public void oneToOne(){
        List<OrderUser> orderAndUser = orderUserMapper.findOrderAndUser();
        for (OrderUser orderUser : orderAndUser) {
            System.out.println(orderUser.toString());
        }
    }

    @Test
    public void addUser(){
        User user = new User();
        user.setId(10);
        user.setUsername("10");
        user.setPassword("10");
        user.setBirthday("10");

        orderMapper.addUser(user);
    }

    @Test
    public void updateUser(){
        User user = new User();
        user.setId(10);
        user.setUsername("123");
        orderMapper.updateUser(user);
    }

    @Test
    public void findUser(){
        List<User> users = orderMapper.selectUser();
        for (User user : users) {
            System.out.println(user.toString());
        }
    }

    @Test
    public void deleteUser(){
        orderMapper.deleteUser(10);
    }


}
