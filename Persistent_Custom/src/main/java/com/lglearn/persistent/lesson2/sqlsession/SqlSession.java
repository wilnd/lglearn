package com.lglearn.persistent.lesson2.sqlsession;

import java.sql.SQLException;
import java.util.List;

public interface SqlSession {

    //查询所有
    <E> List<E> selectList(String statementId, Object... params) throws Exception;

    //查单个
    <T> T selectOne(String statementId, Object... params) throws Exception;

    <T> T  getMapper(Class<?> mapperClass) throws SQLException;
}
