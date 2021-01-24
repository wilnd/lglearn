//package com.lglearn.persistent.lesson2.dao;
//
//import com.lglearn.persistent.lesson2.io.Resource;
//import com.lglearn.persistent.lesson2.pojo.User;
//import com.lglearn.persistent.lesson2.sqlsession.SqlSession;
//import com.lglearn.persistent.lesson2.sqlsession.SqlSessionFactory;
//import com.lglearn.persistent.lesson2.sqlsession.SqlSessionFactoryBuilder;
//
//import java.io.InputStream;
//import java.util.List;
//
//public class UserDaoImpl implements IUserDao {
//    @Override
//    public List<User> findAll() throws Exception {
//        InputStream resourceAsStream = Resource.getResourceAsStream("SqlMapConfig.xml");
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        List<User> users = sqlSession.selectList("user.selectList");
//        users.forEach(user -> System.out.println(user.toString()));
//        return users;
//    }
//
//    @Override
//    public User findByCondition(User user) throws Exception {
//        InputStream resourceAsStream = Resource.getResourceAsStream("SqlMapConfig.xml");
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        //调用
//        User userDb = sqlSession.selectOne("user.selectById", user);
//        System.out.println(userDb.toString());
//        return userDb;
//    }
//}
