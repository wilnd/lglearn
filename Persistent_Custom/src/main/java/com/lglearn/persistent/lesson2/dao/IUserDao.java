package com.lglearn.persistent.lesson2.dao;

import com.lglearn.persistent.lesson2.pojo.User;

import java.util.List;

public interface IUserDao {

    List<User> selectList() throws Exception;

    User selectOne(User user) throws Exception;

}
