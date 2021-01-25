package com.lglearn.pojo;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserOrder {

    private Integer id;

    private String username;

    private String password;

    private String birthday;

    //该用户具有的订单信息
    private List<Order> orders;

    private List<Role> roles;

    @Override
    public String toString() {
        return "UserOrder{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birthday='" + birthday + '\'' +
//                ", orders=" + orders.stream().map(Order::toString).collect(Collectors.joining()) +
                ", roles=" + roles.stream().map(Role::toString).collect(Collectors.joining()) +
                '}';
    }
}
