package com.lg.test;

import com.lglearn.persistent.lesson2.dao.IUserDao;
import com.lglearn.persistent.lesson2.io.Resource;
import com.lglearn.persistent.lesson2.pojo.User;
import com.lglearn.persistent.lesson2.sqlsession.SqlSession;
import com.lglearn.persistent.lesson2.sqlsession.SqlSessionFactory;
import com.lglearn.persistent.lesson2.sqlsession.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;


public class PersistenceTest {

    @Test
    public void test() throws Exception {
        InputStream resourceAsStream = Resource.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //调用
        User user = new User();
        user.setId(1);
        user.setUsername("lucy");
//        User userDb = sqlSession.selectOne("user.selectById", user);
//        System.out.println(userDb.toString());
//        List<User> users = sqlSession.selectList("user.selectAll");
//        users.forEach(user1 -> System.out.println(user1));
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        List<User> all = userDao.selectList();
        User userCondition = userDao.selectOne(user);
        System.out.println();
    }
}
