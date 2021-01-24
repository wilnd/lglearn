package com.lglearn.persistent.lesson2.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParameterMapping {

    //#{content} content是参数名称,跟paramType的参数类型相对应
    private String content;
}
