package com.lglearn.persistent.lesson2.configure;

import com.lglearn.persistent.lesson2.utils.ParameterMapping;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BoundSql {

    private String sql;

    private List<ParameterMapping> parameterMappingList;
}
