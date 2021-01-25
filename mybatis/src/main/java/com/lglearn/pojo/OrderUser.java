package com.lglearn.pojo;

import lombok.Data;

@Data
public class OrderUser {

    private Integer id;

    private String ordertime;

    private Double total;

    //表明该订单属于哪个用户
    private User user;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderTime='" + ordertime + '\'' +
                ", total=" + total +
                ", user=" + user.toString() +
                '}';
    }
}
