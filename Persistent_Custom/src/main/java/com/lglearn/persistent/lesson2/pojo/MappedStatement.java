package com.lglearn.persistent.lesson2.pojo;

import lombok.Data;

//每个查询语句需要一个MappedStatement来包装
@Data
public class MappedStatement {

    private String id;

    private String resultType;

    private String parameterType;

    private String sql;

}
