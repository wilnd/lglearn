package com.lglearn.pojo;

import lombok.Data;

@Data
public class Order {

    private Integer id;

    private String ordertime;

    private Double total;

    private Integer uid;
}
