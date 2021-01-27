package com.lglearn.mapper;

import com.lglearn.pojo.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrderUserMapper {

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "ordertime", column = "ordertime"),
            @Result(property = "total", column = "total"),
            @Result(property = "user", column = "uid", javaType = User.class,
                    one = @One(select = "com.lglearn.mapper.UserMapper.findUserById"))
    })
    @Select("select * from orders")
    List<OrderUser> findOrderAndUser();

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "orders", column = "id", javaType = List.class,
                    many = @Many(select = "com.lglearn.mapper.OrderUserMapper.findOrderByUid"))
    })
    @Select("select * from user")
    List<UserOrder> findUserAndOrder();

    @Select("select * from orders where uid = #{uid}")
    List<Order> findOrderByUid(Integer uid);

    @Select("select * from user")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "roles", column = "id", javaType = List.class,
            many = @Many(select = "com.lglearn.mapper.OrderUserMapper.findRoleByUid"))
    })
    List<UserOrder> findAllUserAndRole();

    @Select("select * from sys_role r, sys_user_role ur where r.id = ur.roleid and ur.userid = #{uid}")
    List<Role> findRoleByUid(Integer uid);

    @Update("update orders set total = #{total} where id = #{id}")
    void updateOrder(Order order);
}
