package com.lglearn.persistent.lesson2.pojo;

import com.google.common.collect.Maps;
import lombok.Data;

import javax.sql.DataSource;
import java.util.Map;

@Data
public class Configuration {

    //数据源
    private DataSource dataSource;

    //<namespace+id, MappedStatement>
    private Map<String, MappedStatement> stringMappedStatementMap = Maps.newHashMap();

}
