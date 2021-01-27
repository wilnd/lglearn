package com.lglearn.mapper;

import com.lglearn.pojo.User;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.cache.impl.PerpetualCache;

import java.io.IOException;
import java.util.List;

//具体指定实现二级缓存的实现类
@CacheNamespace(implementation = org.mybatis.caches.redis.RedisCache.class)
public interface UserMapper {

    List<User> findAll() throws IOException;

    //多条件组合查询
    List<User> findByCondition(User user);

    List<User> findByIds(Integer[] ids);

    //根据id查用户
    @Select("select * from user where id = #{id}")
    User findUserById(Integer id);

    @Update("update user set username = #{username} where id = #{id}")
    void updateMyUser(User user);
}
