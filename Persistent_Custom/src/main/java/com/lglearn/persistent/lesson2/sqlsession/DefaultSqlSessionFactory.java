package com.lglearn.persistent.lesson2.sqlsession;

import com.lglearn.persistent.lesson2.pojo.Configuration;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
