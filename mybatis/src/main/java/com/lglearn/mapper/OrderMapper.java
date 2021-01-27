package com.lglearn.mapper;

import com.lglearn.pojo.OrderUser;
import com.lglearn.pojo.User;
import com.lglearn.pojo.UserOrder;
import org.apache.ibatis.annotations.*;

import java.util.List;

@CacheNamespace
public interface OrderMapper {

    //插叙订单的同时,查询该订单所属的用户
    List<OrderUser> findOrderByUser();

    //查询所有用户信息,同时查询每个用户关联的订单信息
    List<UserOrder> findAll();

    List<UserOrder> findAllUser();

    @Insert("insert into user values (#{id}, #{username}, #{password}, #{birthday})")
    void addUser(User user);

    @Update("update user set username = #{username} where id = #{id}")
    void updateUser(User user);

    @Select("select * from user")
    List<User> selectUser();

    @Delete("delete from user where id = #{id}")
    void deleteUser(Integer id);

}
