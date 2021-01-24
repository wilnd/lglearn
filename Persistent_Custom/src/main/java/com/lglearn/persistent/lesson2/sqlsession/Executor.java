package com.lglearn.persistent.lesson2.sqlsession;

import com.lglearn.persistent.lesson2.pojo.Configuration;
import com.lglearn.persistent.lesson2.pojo.MappedStatement;

import java.util.List;

public interface Executor {

    <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception;

}
