package com.example.ruleenginedemo.core.value;

import com.example.ruleenginedemo.core.enums.DataType;
import lombok.Data;

import java.util.Map;
import java.util.function.Function;

/**
 * @author gaowenfeng02
 * @desc
 * @date 2020-03-20
 */
@Data
public class ArgValue implements Value {

    private String valueKey;
    private DataType dataType;

    private Function<Map<String, Object>, Object> function;

    @Override
    public DataType getDataType() {
        return dataType;
    }

    @Override
    public Function<Map<String, Object>, Object> buildFunction() {
        if(function == null){
            function = argMap->argMap.get(valueKey);
        }
        return function;
    }
}
