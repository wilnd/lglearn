package com.lglearn.test;

import com.lglearn.mapper.UserMapper;
import com.lglearn.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {

    @Test
    public void testForEach() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        Integer[] arr = {1, 2};
        List<User> users = mapper.findByIds(arr);

        users.forEach(user -> System.out.println(user));
    }

    @Test
    public void testDao() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> all = mapper.findAll();
        all.forEach(user -> System.out.println(user));
    }

    @Test
    public void testIf() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user = new User();
        user.setId(1);
        List<User> users = mapper.findByCondition(user);

        users.forEach(userDo -> System.out.println(userDo));
    }

    @Test
    public void test() throws IOException {
        //Resource 就是一个工具类, 配置文件的加载
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //解析配置文件,并创建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //生产sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();//true 自动提交
        //sqlSession封装了与数据的相关方法
        List<User> users = sqlSession.selectList("user.findAll");
        users.forEach(System.out::println);
    }

    @Test
    public void save() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User(111, "123", "123", "123");
        sqlSession.insert("user.saveUser", user);
        sqlSession.commit();
    }

    @Test
    public void update() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User(111, "ch", "1234", "1234");
        sqlSession.update("user.updateUser", user);
        sqlSession.commit();
    }

    @Test
    public void delete() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        sqlSession.delete("user.deleteUser", 111);
        sqlSession.commit();
    }

}
