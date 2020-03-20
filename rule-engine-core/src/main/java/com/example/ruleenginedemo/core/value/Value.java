package com.example.ruleenginedemo.core.value;



import com.example.ruleenginedemo.core.enums.DataType;

import java.util.Map;
import java.util.function.Function;

/**
 * @author gaowenfeng02
 * @desc
 * @date 2020-03-20
 */
public interface Value {
    /**
     * 获取value数据类型
     * @return
     */
    DataType getDataType();

    Function<Map<String,Object>,Object> buildFunction();
}
