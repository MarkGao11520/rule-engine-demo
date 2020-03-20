package com.example.ruleenginedemo.core.value;

import com.example.ruleenginedemo.core.action.ActionMethodRepository;
import com.example.ruleenginedemo.core.enums.DataType;
import com.example.ruleenginedemo.core.exception.DefaultException;
import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author gaowenfeng02
 * @desc
 * @date 2020-03-20
 */
@Data
public class ActionValue implements Value {
    private String actionKey;
    private DataType returnDataType;
    private List<Value> parameterList;

    private Method method;
    private Function<Map<String, Object>, Object> function;

    public ActionValue(String actionKey, DataType returnDataType, List<Value> parameterList) {
        this.actionKey = actionKey;
        this.returnDataType = returnDataType;
        this.parameterList = parameterList;

        this.method = ActionMethodRepository
                .getMethodKey(actionKey)
                .orElseThrow(()->new DefaultException(String.format("[%s]对应的method不存在",actionKey)));
        if(this.method.getParameterCount() != parameterList.size()){
            throw new DefaultException(String.format("[%s]对应的method参数长度不匹配,需要[%s],实际[%s]",actionKey,method.getParameterCount(),parameterList.size()));
        }
        Class[] parameterType =  method.getParameterTypes();
        for (int i = 0; i < parameterList.size(); i++) {
            Class theType = parameterList.get(i).getDataType().getJavaClass();
            if(theType != parameterType[i]){
                throw new DefaultException(String.format("[%s]对应的method的第[i]个参数类型不匹配,需要[%s],实际[%s]",actionKey,i,parameterType[i],theType));
            }
        }
    }

    @Override
    public DataType getDataType() {
        return returnDataType;
    }

    @Override
    public Function<Map<String, Object>, Object> buildFunction() {
        if(function == null){
            function = argMap->{
                assert method.getParameterCount() == parameterList.size();

                Class[] parameterType =  method.getParameterTypes();
                Object[] args = new Object[method.getParameters().length];
                for (int i = 0; i < parameterList.size(); i++) {
                    Object value = parameterList.get(i).buildFunction().apply(argMap);
                    assert value == null || value.getClass() == parameterType[i];
                    args[i] = value;
                }
                try {
                    return method.invoke(args);
                } catch (IllegalAccessException e) {
                    throw new DefaultException(String.format("[%s]对应的method访问权限错误",actionKey));
                } catch (InvocationTargetException e) {
                    throw new DefaultException(String.format("[%s]对应的method执行错误,参数列表[%s]",actionKey,argMap.toString()));
                }
            };
        }
        return function;
    }
}
