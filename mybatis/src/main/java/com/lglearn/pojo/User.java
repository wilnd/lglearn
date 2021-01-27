package com.lglearn.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User implements Serializable {

    @Id //对应注解ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) //设置主键生成策略
    private Integer id;

    //如果跟数据库名字不一致,需要配置 @Column
    private String username;

    private String password;

    private String birthday;

}
