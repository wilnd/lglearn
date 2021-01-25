package com.lglearn.mapper;

import com.lglearn.pojo.User;

import java.io.IOException;
import java.util.List;

public interface UserMapper {

    List<User> findAll() throws IOException;

    //多条件组合查询
    List<User> findByCondition(User user);

    List<User> findByIds(Integer[] ids);

}
